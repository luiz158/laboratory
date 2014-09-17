/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.PrintWriter;
import java.io.StringWriter;
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

            Template t = ve.getTemplate("/opt/demoiselle/tool/drails/240/domain/pojo.java.vm");
            /*  create a context and add data */
            VelocityContext context = new VelocityContext();
            context.put("packageName", Config.packageApp + "/domain");
            context.put("pojo", dominio);
            /* now render the template into a StringWriter */
            StringWriter writer = new StringWriter();
            ve.init();
            t.merge(context, writer);
            /* show the World */
            System.out.println(writer.toString());
        } catch (Exception ex) {
            Logger.getLogger(Domain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
