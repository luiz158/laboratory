/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Domain {

    protected static void create(String dominio) {

        try {
            /*  first, get and initialize an engine  */
            VelocityEngine ve = new VelocityEngine();
            Properties p = new Properties();
            p.setProperty("file.resource.loader.path", Config.getPathTemplates());
            ve.init(p);

            Template t = ve.getTemplate("pojo.vm");
            /*  create a context and add data */
            VelocityContext context = new VelocityContext();
            context.put("packageName", Config.getPackageApp());
            context.put("pojo", dominio.split("\\.")[0]);
            /* now render the template into a StringWriter */
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            FileWriter fw = new FileWriter(Config.getPathDomain() + dominio.split("\\.")[0]);
            fw.write(writer.toString());
            fw.close();
            /* show the World */
        } catch (Exception ex) {
            Logger.getLogger(Domain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
