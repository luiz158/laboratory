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
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author 70744416353
 */
public class CreatePersistence {

    public CreatePersistence(String dominio, PrintWriter out) {

        try {
            /*  first, get and initialize an engine  */
            VelocityEngine ve = new VelocityEngine();
            ve.init();
            org.apache.velocity.Template t = ve.getTemplate("");
            /*  create a context and add data */
            VelocityContext context = new VelocityContext();
            context.put("name", "World");
            /* now render the template into a StringWriter */
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            /* show the World */
            System.out.println(writer.toString());

        } catch (Exception ex) {
            Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
