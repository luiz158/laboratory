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
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

/**
 *
 * @author 70744416353
 */
public class App {

    protected static PrintWriter out;

    public static void main(String[] args) throws IOException {

        String line;
        ConsoleReader reader = new ConsoleReader();
        out = new PrintWriter(reader.getTerminal().wrapOutIfNeeded(System.out));
        List<Completer> completors = new LinkedList<Completer>();

        Validations.installation();
        Config.load();

        reader.setBellEnabled(true);
        reader.setPrompt(ANSI.CYAN + "Drails> " + ANSI.DARK_WHITE);

        if ((Config.nameApp == null || Config.version == null) ||
            (Config.nameApp.isEmpty() || Config.version.isEmpty())) {
            completors.add(
                    new AggregateCompleter(
                            new ArgumentCompleter(new StringsCompleter("create-app"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("application"), new StringsCompleter(Applications.listApps()), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("status"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("install"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("version"), new StringsCompleter(Config.listVersions()), new NullCompleter())));

            for (Completer c : completors) {
                reader.addCompleter(c);
            }

            while ((line = reader.readLine()) != null) {

                String[] param = line.split(" ");
                String command = param[0].trim();

                if (command.equalsIgnoreCase("install")) {

                }

                if (!line.isEmpty()) {
                    if (command.equalsIgnoreCase("status")) {
                        out.println("Informe os valores");
                        out.println("Application: " + Config.nameApp);
                        out.println("version: " + Config.version);
                    }
                }

                if (command.equalsIgnoreCase("create-app")) {
                    if (Config.version == null || Config.version.isEmpty()) {
                        out.println("Escolha a versão do Demoiselle: ");
                    } else {
                        Applications.newApp(param[1].trim());
                        Config.nameApp = param[1].trim();
                    }
                }

                if (!line.isEmpty()) {
                    if (command.equalsIgnoreCase("application")) {
                        if (Applications.existApps(param[1].trim())) {
                            Config.nameApp = param[1].trim();
                        }
                        out.println("Aplicação não existe");
                    }
                }

                if (!line.isEmpty()) {
                    if (command.equalsIgnoreCase("version")) {
                        Config.version = param[1].trim();
                        out.println("version " + Config.version);
                    }
                }

                if ((Config.nameApp != null && !Config.nameApp.isEmpty()) &&
                    (Config.version != null && !Config.version.isEmpty())) {

                    Config.save();

                    for (Completer c : completors) {
                        reader.removeCompleter(c);
                    }

                    break;
                }

                if (command.equalsIgnoreCase("exit")) {
                    return;
                }

                out.flush();
            }
        }

        reader.beep();

        completors = new LinkedList<Completer>();
        completors.add(
                new AggregateCompleter(
                        new ArgumentCompleter(new StringsCompleter("clean"), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("status"), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("persistence"), new FileNameCompleter(), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("business"), new FileNameCompleter(), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("view"), new FileNameCompleter(), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("prime"), new FileNameCompleter(), new NullCompleter())
                )
        );

        for (Completer c : completors) {
            reader.addCompleter(c);
        }

        while ((line = reader.readLine()) != null) {

            String[] param = line.split(" ");
            String command = param[0].trim();

            if (!line.isEmpty()) {
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                if (command.equalsIgnoreCase("clean")) {
                    Config.clear();
                    break;
                }
                if (!line.isEmpty()) {
                    if (command.equalsIgnoreCase("status")) {
                        out.println("Application: " + Config.nameApp);
                        out.println("version: " + Config.version);
                    }
                }
                if (command.equalsIgnoreCase("persistence")) {
                    if (Validations.existsApp(Config.nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("business")) {
                    if (Validations.existsApp(Config.nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("view")) {
                    if (Validations.existsApp(Config.nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("domain")) {
                    if (Validations.existsApp(Config.nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("prime")) {
                    if (Validations.existsApp(Config.nameApp)) {

                    }
                }
                if (command.equalsIgnoreCase("prime-mobile")) {
                    if (Validations.existsApp(Config.nameApp)) {

                    }
                }
            }
            out.flush();
        }
    }

}
