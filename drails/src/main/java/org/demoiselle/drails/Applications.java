/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Applications {

    protected static void newApp(String nameApp) {
        try {
            File demoiselleDir = new File("/opt/demoiselle/apps/");

            if (!demoiselleDir.exists()) {
                demoiselleDir.mkdir();
            }

            File mavenDir = new File("/opt/demoiselle/tool/demoiselle-maven3/bin/");

            if (!mavenDir.exists()) {
                mavenDir = new File("/opt/demoiselle/tool/maven2/bin/");
                if (!mavenDir.exists()) {
                    App.out.println("Instale o demoiselle-maven");
                    return;
                }
            }

            File appDir = new File("/opt/demoiselle/apps/" + nameApp);

            if (!appDir.exists()) {
                Runtime run = Runtime.getRuntime();
                String command = mavenDir + "/mvn -DarchetypeGroupId=org.demoiselle.archetypes -DarchetypeArtifactId=demoiselle-jsf-jpa -DarchetypeVersion=" + Config.version + " -DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots -DgroupId=org.demoiselle -DartifactId=" + nameApp + " -Dversion=1.0.0 -Dpackage=org.demoiselle." + nameApp + " -Dbasedir=" + demoiselleDir + " -Darchetype.interactive=false --batch-mode archetype:generate";
                run.exec(command);
                App.out.println("Criou o Projeto " + nameApp);
            } else {
                App.out.println("Já existe " + nameApp);
            }

        } catch (Exception ex) {
            Logger.getLogger(Applications.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected static String[] listApps() {
        File appsDir = new File("/opt/demoiselle/apps/");
        return appsDir.list();
    }

    protected static boolean existApps(String nameApp) {
        for (int i = 0; i < listApps().length; i++) {
            if (listApps()[i].equalsIgnoreCase(nameApp)) {
                return true;
            }
        }
        return false;
    }

}
