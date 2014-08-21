/*
 * Copyright (C) 2014 SERPRO - Serviço Federal de Processamento de Dados
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.frameworkdemoiselle.scheduler.internal.bootstrap;

import br.gov.frameworkdemoiselle.context.ConversationContext;
import br.gov.frameworkdemoiselle.context.CustomContext;
import br.gov.frameworkdemoiselle.context.RequestContext;
import br.gov.frameworkdemoiselle.context.SessionContext;
import br.gov.frameworkdemoiselle.context.ViewContext;
import br.gov.frameworkdemoiselle.internal.bootstrap.CustomContextBootstrap;
import br.gov.frameworkdemoiselle.internal.context.TemporaryViewContextImpl;
import br.gov.frameworkdemoiselle.util.Beans;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

/**
 *
 * @author 70744416353
 */
public class CdiJob implements org.quartz.Job {

    private final static String DEMOISELLE_JOB = "DEMOISELLE_JOB";

    private final HashMap<String, Boolean> startedContextHere = new HashMap<String, Boolean>();

    private transient CustomContext backupContext = null;

    private boolean registered = false;

    @Override
    public void execute(JobExecutionContext context) {
        try {

            JobDataMap jobData = context.getJobDetail().getJobDataMap();
            String source = jobData.getString(DEMOISELLE_JOB);
            String className = getClassName(source);
            String methodName = getMethodName(source);

            if (!className.isEmpty() && !methodName.isEmpty()) {

                Class<Serializable> jobClass = (Class<Serializable>) Class.forName(className).asSubclass(Serializable.class);

                BeanManager bm = Beans.getBeanManager();
                Set<Bean<?>> jobBeans = bm.getBeans(jobClass);
                Bean<?> jobBean = bm.resolve(jobBeans);
                CreationalContext c = bm.createCreationalContext(jobBean);
                Serializable job = (Serializable) bm.getReference(jobBean, Serializable.class, c);
                Method method = jobClass.getMethod(methodName);
                try {
                    startContexts();
                    method.invoke(job);
                } finally {
                    stopContexts();
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CdiJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(CdiJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(CdiJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CdiJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(CdiJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CdiJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getClassName(String source) {
        String[] trash = source.split(" ");
        String[] array = trash[trash.length - 1].split("\\.");
        for (String string : array) {
            if (string.contains("()")) {
                return trash[trash.length - 1].replace("." + string, "");
            }
        }
        return "";
    }

    private String getMethodName(String source) {
        String[] trash = source.split(" ");
        String[] array = trash[trash.length - 1].split("\\.");
        for (String string : array) {
            if (string.contains("()")) {
                return string.replace("()", "");
            }
        }
        return "";
    }

    private void startContexts() {
        if (!registered) {
            RequestContext requestContext = Beans.getReference(RequestContext.class);
            SessionContext sessionContext = Beans.getReference(SessionContext.class);
            ViewContext viewContext = Beans.getReference(ViewContext.class);
            ConversationContext conversationContext = Beans.getReference(ConversationContext.class);

            if (requestContext != null) {
                startedContextHere.put("request", requestContext.activate());
            }

            if (sessionContext != null) {
                startedContextHere.put("session", sessionContext.activate());
            }

            if (conversationContext != null) {
                startedContextHere.put("conversation", conversationContext.activate());
            }

            //Contexto temporário de visão precisa de tratamento especial
            //para evitar conflito com o contexto presente na extensão demoiselle-jsf
            if (viewContext != null) {
                if (TemporaryViewContextImpl.class.isInstance(viewContext)) {
                    startedContextHere.put("view", viewContext.activate());
                } else {
                    //Precisamos desativar temporariamente o contexto
                    if (viewContext.isActive()) {
                        backupContext = viewContext;
                        viewContext.deactivate();

                        CustomContextBootstrap customContextBootstrap = Beans.getReference(CustomContextBootstrap.class);
                        for (CustomContext customContext : customContextBootstrap.getCustomContexts()) {
                            if (TemporaryViewContextImpl.class.isInstance(customContext)) {
                                startedContextHere.put("view", customContext.activate());
                                break;
                            }
                        }
                    }
                }
            }

            registered = true;
        }
    }

    private void stopContexts() {
        if (registered) {
            RequestContext requestContext = Beans.getReference(RequestContext.class);
            SessionContext sessionContext = Beans.getReference(SessionContext.class);
            ViewContext viewContext = Beans.getReference(ViewContext.class);
            ConversationContext conversationContext = Beans.getReference(ConversationContext.class);

            if (requestContext != null && Boolean.TRUE.equals(startedContextHere.get("request"))) {
                requestContext.deactivate();
            }

            if (sessionContext != null && Boolean.TRUE.equals(startedContextHere.get("session"))) {
                sessionContext.deactivate();
            }

            if (conversationContext != null && Boolean.TRUE.equals(startedContextHere.get("conversation"))) {
                conversationContext.deactivate();
            }

            //Contexto temporário de visão precisa de tratamento especial
            //para evitar conflito com o contexto presente na extensão demoiselle-jsf
            if (viewContext != null) {
                if (TemporaryViewContextImpl.class.isInstance(viewContext) && startedContextHere.get("view")) {
                    viewContext.deactivate();

                    if (backupContext != null) {
                        backupContext.activate();
                        backupContext = null;
                    }
                }
            }
        }
    }

}
