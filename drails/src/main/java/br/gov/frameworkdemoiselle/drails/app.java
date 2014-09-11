/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.drails;

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
public class app {

    public static void main(String[] args) throws IOException {

        ConsoleReader reader = new ConsoleReader();

        reader.setBellEnabled(true);
        reader.setPrompt(ANSI.CYAN + "Drails> " + ANSI.DARK_WHITE);

        reader.addCompleter(new StringsCompleter("Persistence", "Business", "View", "Domain", "Prime", "PrimeMobile"));
        reader.addCompleter(new StringsCompleter("Create-app"));
        reader.beep();

        String line;
        PrintWriter out = new PrintWriter(reader.getTerminal().wrapOutIfNeeded(System.out));

        while ((line = reader.readLine()) != null) {

            if (!line.isEmpty()) {
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                if (line.equalsIgnoreCase("Persistence")) {
                    //break;
                }
                if (line.equalsIgnoreCase("Business")) {
                    //break;
                }
                if (line.equalsIgnoreCase("View")) {
                    // break;
                }
                if (line.equalsIgnoreCase("Domain")) {
                    // break;
                }
                if (line.equalsIgnoreCase("Prime")) {
                    // break;
                }
                if (line.equalsIgnoreCase("PrimeMobile")) {
                    // break;
                }
            }
            out.flush();
        }
    }

}
