/*
 * Demoiselle Framework
 * Copyright (C) 2014 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.component.audit.util;

import static java.lang.Class.forName;
import static java.util.logging.Logger.getLogger;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Id;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.weld.bean.proxy.ProxyFactory;

import br.gov.frameworkdemoiselle.component.audit.annotation.Audit;

/**
 *
 * @author SERPRO
 *
 */
public class Util {

    public List<String> className() {
        Set<String> lista = new HashSet<String>();

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTraceElements) {
            try {
                Class<?> clazz = forName(stackTraceElement.getClassName());

                if (ProxyFactory.isProxy(clazz.newInstance())) {
                    clazz = clazz.getSuperclass();
                }

                String classe = getValueOfParameterInMethodAnnotation(clazz, stackTraceElement);

                if (classe != null && !classe.isEmpty() && !classe.equalsIgnoreCase("null")) {
                    lista.add(classe);
                }

            } catch (ClassNotFoundException ex) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return new ArrayList<String>(lista);
    }

    public String idName(String className) {

        Field[] fields;
        String idName = "";;
        try {
            fields = forName(className).getDeclaredFields();

            for (Field field : fields) {

                if (!"".equals(idName)) {
                    break;
                }

                field.setAccessible(true);

                Annotation annotation = field.getAnnotation(Id.class);

                if (annotation != null && "".equals(idName)) {
                    idName = field.getName();
                }

                field.setAccessible(false);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idName;
    }

    private String getValueOfParameterInMethodAnnotation(Class<?> clazz, StackTraceElement stackTraceElement) {

        Method[] methods = clazz.getMethods();
        String methodName = stackTraceElement.getMethodName();

        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(Audit.class);
            if (annotation != null && method.getName().equals(methodName)) {
                return ((Audit) annotation).description();
            }
        }
        return null;
    }

    public static String jsonSerializer(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);

        } catch (IOException ex) {
            getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static Map<String, String> jsonToMap(String string) {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> retorno = new HashMap<String, String>();

        try {
            retorno = mapper.readValue(string, new TypeReference<HashMap<String, String>>() {
            });
        } catch (IOException e) {
            retorno = new HashMap<String, String>();
        }

        return retorno;
    }

    public static String getFolderPathDefault() {
        return System.getProperty("user.dir") + File.separatorChar + "demoiselle-audit-log" + File.separatorChar;
    }

}
