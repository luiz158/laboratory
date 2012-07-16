package example.initializer;

import org.hsqldb.Server;

import br.gov.frameworkdemoiselle.annotation.Shutdown;
import br.gov.frameworkdemoiselle.annotation.Startup;

import static br.gov.frameworkdemoiselle.annotation.Startup.MAX_PRIORITY;
import static br.gov.frameworkdemoiselle.annotation.Startup.MIN_PRIORITY;;

public class DatabaseServer {

	private final Server server;
	
	public DatabaseServer() {
		server = new Server();
		server.setDatabaseName(0, "db");
		server.setDatabasePath(0, "database/db");
		server.setPort(9001);
		server.setSilent(true);
	}
	
	@Startup(priority = MAX_PRIORITY)
	public void initServer() {
		System.out.println("INICIANDO O SERVIDOR... ");
		server.start();
	}
	
	@Startup(priority = MIN_PRIORITY)
	public void executeGrant() {
		System.out.println("HABILITANDO AS PERMISSÕES");
	}
	
	@Shutdown(priority = 1)
	public void removeGrant() {
		System.out.println("DESABILITANDO AS PERMISSÕES... ");
	}
	
	@Shutdown(priority = 2)
	public void stopServer() {
		System.out.println("FINALIZANDO O SERVIDOR... ");
		server.stop();
	}
	

}
