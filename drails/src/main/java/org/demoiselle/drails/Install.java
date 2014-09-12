/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.File;

public class Install {

    public static void version(String version) {

        File demoiselleDir = new File("/opt/demoiselle");

        if (!demoiselleDir.exists()) {
            demoiselleDir.mkdir();
            demoiselleDir.canExecute();
            demoiselleDir.canRead();
            demoiselleDir.canWrite();
        }

        File mavenDir = new File("/opt/demoiselle/tool/maven3/bin/");

        if (!mavenDir.exists()) {
            mavenDir = new File("/opt/demoiselle/tool/maven2/bin/");
            if (!mavenDir.exists()) {
                App.out.println("Instale o demoiselle-maven");
                return;
            }
        }
    }

}
