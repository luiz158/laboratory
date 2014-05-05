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
package br.gov.frameworkdemoiselle.component.audit.implementation;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

/**
 *
 * @author SERPRO
 *
 */
@Configuration(prefix = "frameworkdemoiselle.audit")
public class AuditConfig {

    private static final Long timeDefault = 3600000L;

    @Name("folder.fail.objects")
    private String folderFailObjects;
    @Name("scheduler.start.time")
    private Long schedulerStartTime;
    @Name("scheduler.repeat.interval")
    private Long schedulerRepeatInterval;

    @Name("server.url")
    private String serverUrl;
    @Name("server.port")
    private String serverPort;
    @Name("server.param")
    private String serverParam;
    @Name("server.user")
    private String serverUser;
    @Name("server.password")
    private String serverPass;

    @Name("database.name")
    private String dataBaseName;
    @Name("database.user")
    private String databaseUser;
    @Name("database.password")
    private String databasePass;
    @Name("table.name")
    private String tableName;

    private String system;

    /**
     *
     * @return
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     *
     * @param serverUrl
     */
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     *
     * @return
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     *
     * @param serverPort
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    /**
     *
     * @return
     */
    public String getServerParam() {
        return serverParam;
    }

    /**
     *
     * @param serverParam
     */
    public void setServerParam(String serverParam) {
        this.serverParam = serverParam;
    }

    /**
     *
     * @return
     */
    public String getServerUser() {
        return serverUser;
    }

    /**
     *
     * @param serverUser
     */
    public void setServerUser(String serverUser) {
        this.serverUser = serverUser;
    }

    /**
     *
     * @return
     */
    public String getServerPass() {
        return serverPass;
    }

    /**
     *
     * @param serverPass
     */
    public void setServerPass(String serverPass) {
        this.serverPass = serverPass;
    }

    /**
     *
     * @return
     */
    public String getDataBaseName() {
        return dataBaseName;
    }

    /**
     *
     * @param dataBaseName
     */
    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    /**
     *
     * @return
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     *
     * @param databaseUser
     */
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    /**
     *
     * @return
     */
    public String getDatabasePass() {
        return databasePass;
    }

    /**
     *
     * @param databasePass
     */
    public void setDatabasePass(String databasePass) {
        this.databasePass = databasePass;
    }

    /**
     *
     * @return
     */
    public String getTableName() {
        return tableName;
    }

    /**
     *
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     *
     * @return
     */
    public String getSystem() {
        return system;
    }

    /**
     *
     * @param system
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     *
     * @return
     */
    public String getFolderFailObjects() {
        return folderFailObjects;
    }

    /**
     *
     * @param folderFailObjects
     */
    public void setFolderFailObjects(String folderFailObjects) {
        this.folderFailObjects = folderFailObjects;
    }

    /**
     *
     * @return
     */
    public Long getSchedulerStartTime() {
        return schedulerStartTime == null ? timeDefault : schedulerStartTime;
    }

    /**
     *
     * @param schedulerStartTime
     */
    public void setSchedulerStartTime(Long schedulerStartTime) {
        this.schedulerStartTime = schedulerStartTime;
    }

    /**
     *
     * @return
     */
    public Long getSchedulerRepeatInterval() {
        return schedulerRepeatInterval == null ? timeDefault : schedulerRepeatInterval;
    }

    /**
     *
     * @param schedulerRepeatInterval
     */
    public void setSchedulerRepeatInterval(Long schedulerRepeatInterval) {
        this.schedulerRepeatInterval = schedulerRepeatInterval;
    }

    @Override
    public String toString() {
        return "AuditConfig{" + "folderFailObjects=" + folderFailObjects + ", schedulerStartTime=" + schedulerStartTime + ", schedulerRepeatInterval=" + schedulerRepeatInterval + ", serverUrl=" + serverUrl + ", serverPort=" + serverPort + ", serverParam=" + serverParam + ", serverUser=" + serverUser + ", serverPass=" + serverPass + ", dataBaseName=" + dataBaseName + ", databaseUser=" + databaseUser + ", databasePass=" + databasePass + ", tableName=" + tableName + ", system=" + system + '}';
    }

}
