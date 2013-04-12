package br.gov.frameworkdemoiselle.snmp.agent;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import br.gov.frameworkdemoiselle.util.Beans;

@ApplicationScoped
public class SNMPAgentManager {
	
	private SNMPAgent agent;
	
	private ArrayList<ManagedClassMonitor> monitors;
	
	@PostConstruct
	public void initManager(){
		agent = Beans.getReference(SNMPAgent.class);
		monitors = new ArrayList<ManagedClassMonitor>();
	}
	
	public void startAgent(){
		agent.startup();
	}
	
	public void stopAgent(){
		for (ManagedClassMonitor monitor : monitors){
			agent.unregisterManagedObjects(monitor);
		}
		agent.stop();
	}

	public void registerManagedClass(ManagedClassMonitor monitor){
		agent.registerManagedObjects(monitor);
	}
	
}
