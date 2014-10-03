package org.demoiselle.drails;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

import org.demoiselle.drails.commands.CreateAppCommand;
import org.demoiselle.drails.commands.CreateBusinessCommand;
import org.demoiselle.drails.commands.CreateDomainCommand;
import org.demoiselle.drails.commands.CreatePersistenceCommand;
import org.demoiselle.drails.commands.CreateViewCommand;
import org.demoiselle.drails.completers.DomainCompleter;
import org.demoiselle.drails.constants.CommandsConstant;
import org.demoiselle.drails.exceptions.ValidationException;
import org.demoiselle.drails.validations.InitialValidations;

public class App {

    public static ConsoleReader reader;
    
    public static PrintWriter out;
    
    private List<Completer> completors = new LinkedList<>();
    private List<String> domainsList = new LinkedList<>();
    
    public static void main(String[] args) throws IOException{
        App app = new App();
        app.run(args);
    }
    
    public void run(String[] args){
    	try{
    		init();
    		
            String line;
            new InitialValidations(new File(Config.getInstance(new File("")).getDemoisellePath())).validate();
            
            try{
	            if(args != null && args.length > 0){
	                String values = "";
	                for(String arg : args){
	                    values += arg + " ";
	                }
	                
	                createInitialMenu(values);
	                args = null;
	            }
	            else{
	                while ((line = reader.readLine()) != null) {
	                    createInitialMenu(line);
	                }
	            }
            }
            catch(ValidationException e){
            	reader.setPrompt(TerminalColor.RED + "ERROR: " + TerminalColor.SANE);
            	out.println(TerminalColor.RED + "ERROR: " + TerminalColor.SANE + e.getMessage());
            	out.flush();
            	run(null);
            }
                
        }
        catch(IOException e){
        	out.println(TerminalColor.RED + "ERROR: " + TerminalColor.SANE + e.getMessage());
        	out.flush();
        	run(null);
        }

    }
    
    private void init() throws IOException {
    	reader = new ConsoleReader();
        out = new PrintWriter(reader.getTerminal().wrapOutIfNeeded(System.out));
        
    	Config.getInstance(new File("").getAbsoluteFile()).load();
		domainsList = DomainCompleter.list(new File("").getAbsoluteFile());
		
		completors = new LinkedList<>();
		
		completors.add(
                new AggregateCompleter(
                        new ArgumentCompleter(new StringsCompleter(CommandsConstant.CREATE_APP), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter(CommandsConstant.CREATE_DOMAIN), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter(CommandsConstant.CREATE_BUSINESS), new StringsCompleter(domainsList), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter(CommandsConstant.CREATE_PERSISTENCE), new StringsCompleter(domainsList), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter(CommandsConstant.CREATE_VIEW), new StringsCompleter(domainsList), new NullCompleter()),
                        new ArgumentCompleter(new StringsCompleter(CommandsConstant.EXIT), new NullCompleter())
                ));
		

        for (Completer c : completors) {
            reader.addCompleter(c);
        }
        
        reader.setBellEnabled(true);
        reader.setPrompt(TerminalColor.CYAN + "Drails> " + TerminalColor.SANE);
	}

	private void createInitialMenu(String line) throws ValidationException, IOException{
        String[] param = line.split(" ");
                    
        if (param.length >= 1) {
            
            String command = param[0].trim();

            if (CommandsConstant.CREATE_APP.equalsIgnoreCase(command)) {
                new CreateAppCommand(new File("").getAbsoluteFile()).execute(line);
                System.exit(0);
            }
            else{
	            if(CommandsConstant.CREATE_DOMAIN.equalsIgnoreCase(command)){
	            	Config.getInstance(new File("").getAbsoluteFile()).load();
	            	new CreateDomainCommand(new File("").getAbsoluteFile()).execute(line);
	            	out.flush();
	            	init();
	            }
	            else{
		            if(CommandsConstant.CREATE_BUSINESS.equalsIgnoreCase(command)){            	
		            	Config.getInstance(new File("").getAbsoluteFile()).load();
		            	new CreateBusinessCommand(new File("").getAbsoluteFile()).execute(line);
		            }
		            else{
			            if(CommandsConstant.CREATE_PERSISTENCE.equalsIgnoreCase(command)){            	
			            	Config.getInstance(new File("").getAbsoluteFile()).load();
			            	new CreatePersistenceCommand(new File("").getAbsoluteFile()).execute(line);
			            }
			            else{
				            if(CommandsConstant.CREATE_VIEW.equalsIgnoreCase(command)){            	
				            	Config.getInstance(new File("").getAbsoluteFile()).load();
				            	new CreateViewCommand(new File("").getAbsoluteFile()).execute(line);
				            }
				            else{
					            if(CommandsConstant.EXIT.equalsIgnoreCase(command)){
					            	System.exit(0);
					            }
					            else{
					            	out.println(TerminalColor.RED + "ERROR: " + TerminalColor.SANE + "Commando '" + command + "' não encontrado");
					            }
				            }
			            }
		            }
	            }
            }
            
            out.flush();
        }
    }
   
	class TerminalColor {

	    public static final String SANE = "\u001B[0m";
	    public static final String BLACK = "\u001B[0;30m";
	    public static final String RED = "\u001B[0;31m";
	    public static final String GREEN = "\u001B[0;32m";
	    public static final String YELLOW = "\u001B[0;33m";
	    public static final String BLUE = "\u001B[0;34m";
	    public static final String MAGENTA = "\u001B[0;35m";
	    public static final String CYAN = "\u001B[0;36m";
	    public static final String WHITE = "\u001B[0;37m";
	    public static final String DARK_BLACK = "\u001B[1;30m";
	    public static final String DARK_RED = "\u001B[1;31m";
	    public static final String DARK_GREEN = "\u001B[1;32m";
	    public static final String DARK_YELLOW = "\u001B[1;33m";
	    public static final String DARK_BLUE = "\u001B[1;34m";
	    public static final String DARK_MAGENTA = "\u001B[1;35m";
	    public static final String DARK_CYAN = "\u001B[1;36m";
	    public static final String DARK_WHITE = "\u001B[1;37m";
	    public static final String BACKGROUND_BLACK = "\u001B[40m";
	    public static final String BACKGROUND_RED = "\u001B[41m";
	    public static final String BACKGROUND_GREEN = "\u001B[42m";
	    public static final String BACKGROUND_YELLOW = "\u001B[43m";
	    public static final String BACKGROUND_BLUE = "\u001B[44m";
	    public static final String BACKGROUND_MAGENTA = "\u001B[45m";
	    public static final String BACKGROUND_CYAN = "\u001B[46m";
	    public static final String BACKGROUND_WHITE = "\u001B[47m";
	}
	
}
