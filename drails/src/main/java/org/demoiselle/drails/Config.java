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

/**
 *
 * @author 70744416353
 */
public class Config {

    private static Properties properties;
    protected static String pathConfigFile = "/opt/demoiselle/tool/drails/config.properties";
    protected static String nameApp;
    protected static String version;
    protected static String packageApp;

    protected static void load() {

        properties = new Properties();
        FileInputStream fis = null;
        File file = new File(pathConfigFile);
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
            properties.store(new FileOutputStream(pathConfigFile), "Config");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void clear() {
        version = "";
        nameApp = "";
        packageApp = "";
        File file = new File(pathConfigFile);
        file.delete();
    }

    protected static String[] listVersions() {
        File appsDir = new File("/opt/demoiselle/tool/drails/");
        return appsDir.list();
    }

    protected static String[] listDomains() {
        File appsDir = new File("/opt/demoiselle/apps/" + nameApp + "/src/main/java" + packageApp + "domain");
        if (appsDir.exists()) {
            return appsDir.list();
        } else {
            return new String[]{""};
        }
    }

//    private static List<String> listFiles(File dir, List<String> filesName) {
//        File[] files = dir.listFiles();
//        for (File file : files) {
//            if (file.isDirectory()) {
//                if (file.getName().equalsIgnoreCase("domain")) {
//                    filesName.addAll(Arrays.asList(file.list()));
//                    return filesName;
//                }
//                listFiles(file, filesName);
//            }
//        }
//        return filesName;
//    }
}
