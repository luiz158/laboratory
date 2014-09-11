/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import jline.console.ConsoleReader;
import jline.console.completer.Completer;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;

/**
 *
 * @author 70744416353
 */
public class App {

    protected static String nameApp;
    protected static String version;
    protected static PrintWriter out;

    public static void main(String[] args) throws IOException {

        ConsoleReader reader = new ConsoleReader();

        reader.setBellEnabled(true);
        reader.setPrompt(ANSI.CYAN + "Drails> " + ANSI.DARK_WHITE);

        reader.addCompleter(new StringsCompleter("persistence", "business", "view", "domain", "prime", "prime-mobile"));
        reader.addCompleter(new StringsCompleter("create-app", "use", "clean"));
        reader.beep();

        String line;

        out = new PrintWriter(reader.getTerminal().wrapOutIfNeeded(System.out));

        while ((line = reader.readLine()) != null) {

            String[] param = line.split(" ");
            String command = param[0].trim();

            if (!line.isEmpty()) {
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                if (command.equalsIgnoreCase("create-app")) {
                    new CreateApp(param[1].trim());
                    nameApp = param[1].trim();
                }
                if (command.equalsIgnoreCase("use")) {
                    nameApp = param[1].trim();
                }

                if (command.equalsIgnoreCase("clean")) {
                    nameApp = "";
                }
                if (command.equalsIgnoreCase("persistence")) {
                    if (existsApp(nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("business")) {
                    if (existsApp(nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("view")) {
                    if (existsApp(nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("domain")) {
                    if (existsApp(nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("prime")) {
                    if (existsApp(nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("prime-mobile")) {
                    if (existsApp(nameApp)) {

                    }
                }
            }
            out.flush();
        }
    }

    private static boolean existsApp(String nameApp) {
        if (nameApp.isEmpty()) {
            out.print("Escolha uma app digitando: Use nomeApp");
            return false;
        }
        out.print("App " + nameApp);
        return true;
    }

}
