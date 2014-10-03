package util;
import java.io.IOException;
import java.io.PrintWriter;

import jline.console.ConsoleReader;

import org.demoiselle.drails.App;


public class InitConfigurationTest {
	
	public InitConfigurationTest(){
		try {
			App.reader = new ConsoleReader();
			 App.out = new PrintWriter(App.reader.getTerminal().wrapOutIfNeeded(System.out));
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}

}
