/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.File;

public class Validations {

    protected static void installation() {

        File demoiselleDir = new File("/opt/demoiselle");

        if (!demoiselleDir.exists()) {
            App.out.println("Você precisa instalar o demoiselle-infra: http://demoiselle.sourceforge.net/infra ou digite install versão ex: install 2.4.0");
            return;
        }

        File mavenDir = new File("/opt/demoiselle/tool/maven3/bin/");

        if (!mavenDir.exists()) {
            mavenDir = new File("/opt/demoiselle/tool/maven2/bin/");
            if (!mavenDir.exists()) {
                App.out.println("Instale o demoiselle-maven");
                return;
            }
        }

        if (Config.nameApp != null && !Config.nameApp.isEmpty()) {
            File appDir = new File("/opt/demoiselle/apps/" + Config.nameApp);
            if (!appDir.exists()) {
                Config.nameApp = "";
            }
        }

    }

}
