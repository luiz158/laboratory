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
import java.io.PrintWriter;
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

    protected static void load() {

        properties = new Properties();
        FileInputStream fis = null;
        File file = new File(pathConfigFile);
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
                nameApp = properties.getProperty("nameApp");
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
            properties.store(new FileOutputStream(pathConfigFile), "Config");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void clear() {
        version = "";
        nameApp = "";
        File file = new File(pathConfigFile);
        file.delete();
    }

    protected static String[] listVersions() {
        File appsDir = new File("/opt/demoiselle/tool/drails/");
        return appsDir.list();
    }

}
