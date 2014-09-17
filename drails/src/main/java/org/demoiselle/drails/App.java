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

        Config.load();
        Validations.installation();

        reader.setBellEnabled(true);
        reader.setPrompt(ANSI.CYAN + "Drails> " + ANSI.DARK_WHITE);

        if ((Config.getNameApp() == null || Config.getVersion() == null) ||
            (Config.getNameApp().isEmpty() || Config.getVersion().isEmpty()) ||
            (Config.getPackageApp().isEmpty() || Config.getPackageApp().isEmpty())) {

            completors.add(
                    new AggregateCompleter(
                            new ArgumentCompleter(new StringsCompleter("create-app"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("package"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("application"), new StringsCompleter(Applications.listApps()), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("status"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("install"), new NullCompleter()),
                            new ArgumentCompleter(new StringsCompleter("getVersion"), new StringsCompleter(Config.listVersions()), new NullCompleter())));

            for (Completer c : completors) {
                reader.addCompleter(c);
            }

            while ((line = reader.readLine()) != null) {

                String[] param = line.split(" ");
                String command = param[0].trim();

                if (param.length > 1) {

                    if (command.equalsIgnoreCase("install")) {

                    }

                    if (command.equalsIgnoreCase("create-app")) {
                        if (Config.getVersion() == null || Config.getVersion().isEmpty()) {
                            out.println("Escolha a versão do Demoiselle: ");
                        } else {
                            if (Config.getPackageApp() == null || Config.getPackageApp().isEmpty()) {
                                out.println("Escolha o pacote da app: ");

                            } else {
                                if (param[1].trim() != null && !param[1].trim().isEmpty()) {
                                    Config.setNameApp(param[1].trim());
                                    Applications.newApp();
                                } else {
                                    out.println("Escolha o nome do sistema");
                                }

                            }
                        }
                    }

                    if (!line.isEmpty()) {
                        if (command.equalsIgnoreCase("application")) {
                            if (Applications.existApps(param[1].trim())) {
                                Config.setNameApp(param[1].trim());
                            }
                            out.println("Aplicação não existe");
                        }
                    }

                    if (!line.isEmpty()) {
                        if (command.equalsIgnoreCase("package")) {
                            Config.setPackageApp(param[1].trim());
                        }
                    }

                    if (!line.isEmpty()) {
                        if (command.equalsIgnoreCase("getVersion")) {
                            Config.setVersion(param[1].trim());
                            out.println("getVersion " + Config.getVersion());
                        }
                    }

                }

                if (!line.isEmpty()) {
                    if (command.equalsIgnoreCase("status")) {
                        out.println("Informe os valores");
                        out.println("Application: " + Config.getNameApp());
                        out.println("Version: " + Config.getVersion());
                        out.println("Package: " + Config.getPackageApp());
                    }
                }

                if (command.equalsIgnoreCase("exit")) {
                    return;
                }

                out.flush();

                if ((Config.getNameApp() != null && !Config.getNameApp().isEmpty()) &&
                    (Config.getVersion() != null && !Config.getVersion().isEmpty()) &&
                    (Config.getPackageApp() != null && !Config.getPackageApp().isEmpty())) {

                    Config.save();

                    for (Completer c : completors) {
                        reader.removeCompleter(c);
                    }

                    break;
                }
            }
        }

        reader.beep();

        completors = new LinkedList<Completer>();
        completors.add(
                new AggregateCompleter(
                        new ArgumentCompleter(new StringsCompleter("clean"), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("status"), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter("persistence"), new StringsCompleter(Config.listDomains()), new NullCompleter()),
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

                if (param.length > 1) {

                    if (command.equalsIgnoreCase("persistence")) {

                    }
                    if (command.equalsIgnoreCase("business")) {

                    }
                    if (command.equalsIgnoreCase("view")) {

                    }
                    if (command.equalsIgnoreCase("domain")) {
                        if (param[1].trim() != null && !param[1].trim().isEmpty()) {
                            Domain.create(param[1].trim());
                        }
                    }
                    if (command.equalsIgnoreCase("prime")) {

                    }
                    if (command.equalsIgnoreCase("prime-mobile")) {
                    }
                }

                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                if (command.equalsIgnoreCase("clean")) {
                    Config.clear();
                    break;
                }
                if (!line.isEmpty()) {
                    if (command.equalsIgnoreCase("status")) {
                        out.println("Application: " + Config.getNameApp());
                        out.println("Version: " + Config.getVersion());
                        out.println("Package: " + Config.getPackageApp());
                    }
                }

                out.flush();
            }
        }

    }
}
