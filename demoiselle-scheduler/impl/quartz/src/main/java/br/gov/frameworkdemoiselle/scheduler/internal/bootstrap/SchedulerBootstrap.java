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

import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.lifecycle.AfterStartupProccess;
import br.gov.frameworkdemoiselle.scheduler.lifecycle.Schedule;
import javax.enterprise.event.Observes;
import org.slf4j.Logger;

/**
 *
 * @author 70744416353
 */
public class SchedulerBootstrap extends AbstractLifecycleScheduler<Schedule> {

    private Logger logger;

    @Override
    protected Logger getLogger() {
        if (logger == null) {
            logger = LoggerProducer.create(SchedulerBootstrap.class);
        }

        return logger;
    }

    public void startup(@Observes AfterStartupProccess event) {
        proccessEvent();
    }
}
