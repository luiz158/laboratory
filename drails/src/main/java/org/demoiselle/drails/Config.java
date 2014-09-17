/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    private static Properties properties;
    private static String configFileName = "config.properties";
    private static String pathDrails = "/opt/demoiselle/tool/drails/";
    private static String pathApps = "/opt/demoiselle/apps";
    private static String nameApp;
    private static String version;
    private static String packageApp;

    protected static void load() {

        properties = new Properties();
        FileInputStream fis = null;
        File file = new File(pathDrails + configFileName);
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
                nameApp = properties.getProperty("nameApp");
                packageApp = properties.getProperty("packageApp");
                version = properties.getProperty("version");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    protected static void save() {
        try {
            properties.setProperty("nameApp", nameApp);
            properties.setProperty("version", version);
            properties.setProperty("packageApp", packageApp);
            properties.store(new FileOutputStream(pathDrails + configFileName), "Config");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void clear() {
        version = "";
        nameApp = "";
        packageApp = "";
        File file = new File(pathDrails + configFileName);
        file.delete();
    }

    protected static String[] listVersions() {
        File appsDir = new File("/opt/demoiselle/tool/drails/");
        return appsDir.list();
    }

    protected static String[] listDomains() {
        File appsDir = new File("/opt/demoiselle/apps/" + nameApp + "/src/main/java/" + packageApp.replace(".", "/") + "/" + nameApp.toLowerCase() + "/domain");
        if (appsDir.exists()) {
            return appsDir.list();
        } else {
            return new String[]{""};
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        Config.properties = properties;
    }

    public static String getConfigFileName() {
        return configFileName;
    }

    public static void setConfigFileName(String configFileName) {
        Config.configFileName = configFileName;
    }

    public static String getPathDrails() {
        return pathDrails;
    }

    public static void setPathDrails(String pathDrails) {
        Config.pathDrails = pathDrails;
    }

    public static String getPathApps() {
        return pathApps;
    }

    public static void setPathApps(String pathApps) {
        Config.pathApps = pathApps;
    }

    public static String getPathDomain() {
        return new StringBuilder().append(pathApps).append("/").append(nameApp).append("/src/main/java/").append(packageApp.replace(".", "/")).append("/").append(nameApp.toLowerCase()).append("/domain/").toString();
    }

    public static String getPathPersistence() {
        return new StringBuilder().append(pathApps).append("/").append(nameApp).append("/src/main/java/").append(packageApp.replace(".", "/")).append("/").append(nameApp.toLowerCase()).append("/persistence/").toString();
    }

    public static String getPathBusiness() {
        return new StringBuilder().append(pathApps).append("/").append(nameApp).append("/src/main/java/").append(packageApp.replace(".", "/")).append("/").append(nameApp.toLowerCase()).append("/business/").toString();
    }

    public static String getPathView() {
        return new StringBuilder().append(pathApps).append("/").append(nameApp).append("/src/main/java/").append(packageApp.replace(".", "/")).append("/").append(nameApp.toLowerCase()).append("/view/").toString();
    }

    public static String getPathPrime() {
        return new StringBuilder().append(pathApps).append("/").append(nameApp).append("/src/main/java/").append(packageApp.replace(".", "/")).append("/").append(nameApp.toLowerCase()).append("/prime/").toString();
    }

    public static String getPathTemplates() {
        return new StringBuilder().append(pathDrails).append(version).append("/templates/").toString();
    }

    public static String getNameApp() {
        return nameApp;
    }

    public static void setNameApp(String nameApp) {
        Config.nameApp = nameApp;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        Config.version = version;
    }

    public static String getPackageApp() {
        return packageApp;
    }

    public static void setPackageApp(String packageApp) {
        Config.packageApp = packageApp;
    }

}
