package br.gov.frameworkdemoiselle.jmx.tests.internal;

import java.lang.management.ManagementFactory;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.jmx.configuration.JMXConfig;
import br.gov.frameworkdemoiselle.jmx.internal.MBeanManager;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.frameworkdemoiselle.management.notification.AttributeChangeNotification;
import br.gov.frameworkdemoiselle.management.notification.Notification;
import br.gov.frameworkdemoiselle.management.notification.NotificationManager;
import br.gov.frameworkdemoiselle.util.Beans;

@RunWith(DemoiselleRunner.class)
public class NotificationBroadcasterTest {
	
	/**
	 * Testa o envio de uma mensagem para clientes conectados
	 */
	@Test
	public void sendMessageTest(){
		JMXConfig config = Beans.getReference(JMXConfig.class);
		
		//Obtém o servidor MBean onde anexaremos um listener para a notificação
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		
		NotificationManager notificationManager = Beans.getReference(NotificationManager.class);
		MBeanManager mBeanManager = Beans.getReference(MBeanManager.class);
		
		//Aqui obtemos o MBean de notificações já registrado pelo bootstrap
		StringBuffer notificationMBeanName = new StringBuffer()
			.append( config.getNotificationDomain()!=null ? config.getNotificationDomain() : "br.gov.frameworkdemoiselle.jmx" )
			.append(":name=")
			.append( config.getNotificationMBeanName());
		ObjectInstance instance = mBeanManager.findMBeanInstance(notificationMBeanName.toString());
		
		//StringBuffer vazio
		StringBuffer notificationBuffer = new StringBuffer();
		
		//Este notification listener será chamado quando o Demoiselle enviar a notificação e vai colocar
		//a mensagem enviada em "notificationBuffer"
		NotificationListener listener = new TestNotificationListener(notificationBuffer);
		
		try {
			//Anexa o listener no servidor MBean
			server.addNotificationListener(instance.getObjectName(),listener,null,null);
		} catch (InstanceNotFoundException e) {
			Assert.fail();
		}
		
		//Manda a notificação pelo Demoiselle
		Notification n = new Notification("Notification test successful");
		notificationManager.sendNotification(n);
		
		//Se o componente funcionou, o Demoiselle propagou a notificação para o servidor MBean e o listener preencheu
		//o StringBuffer com nossa mensagem.
		Assert.assertEquals("Notification test successful", notificationBuffer.toString());
		
	}
	
	/**
	 * Testa o envio de uma mensagem de mudança de atributo para clientes conectados
	 */
	@Test
	public void sendAttributeChangedMessageTest(){
		JMXConfig config = Beans.getReference(JMXConfig.class);
		
		//Obtém o servidor MBean onde anexaremos um listener para a notificação
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		
		NotificationManager notificationManager = Beans.getReference(NotificationManager.class);
		MBeanManager mBeanManager = Beans.getReference(MBeanManager.class);
		
		//Aqui obtemos o MBean de notificações já registrado pelo bootstrap
		StringBuffer notificationMBeanName = new StringBuffer()
			.append( config.getNotificationDomain()!=null ? config.getNotificationDomain() : "br.gov.frameworkdemoiselle.jmx" )
			.append(":name=")
			.append( config.getNotificationMBeanName());
		ObjectInstance instance = mBeanManager.findMBeanInstance(notificationMBeanName.toString());
		
		//StringBuffer vazio
		StringBuffer notificationBuffer = new StringBuffer();
		
		//Este notification listener será chamado quando o Demoiselle enviar a notificação e vai colocar
		//a mensagem enviada em "notificationBuffer"
		NotificationListener listener = new TestNotificationListener(notificationBuffer);
		
		try {
			//Anexa o listener no servidor MBean
			server.addNotificationListener(instance.getObjectName(),listener,null,null);
		} catch (InstanceNotFoundException e) {
			Assert.fail();
		}
		
		//Manda a notificação pelo Demoiselle
		AttributeChangeNotification notification = new AttributeChangeNotification("Attribute Changed","name",String.class,"Demoiselle 1","Demoiselle 2");
		notificationManager.sendAttributeChangedMessage(notification);
		
		//Se o componente funcionou, o Demoiselle propagou a notificação para o servidor MBean e o listener preencheu
		//o StringBuffer com nossa mensagem.
		Assert.assertEquals("Attribute Changed: name = Demoiselle 2", notificationBuffer.toString());
		
	}
	
	/**
	 * Implementação do {@link NotificationListener} do Java que vai por qualquer notificação recebida em um StringBuffer.
	 * 
	 * @author serpro
	 *
	 */
	class TestNotificationListener implements NotificationListener {
		
		StringBuffer message;
		
		public TestNotificationListener(StringBuffer testMessage){
			this.message = testMessage;
		}

		@Override
		public void handleNotification(javax.management.Notification notification, Object handback) {
			if (message!=null){
				message.append(notification.getMessage());
				
				if (notification instanceof javax.management.AttributeChangeNotification){
					message.append(": ")
						.append( ((javax.management.AttributeChangeNotification)notification).getAttributeName() )
						.append(" = ")
						.append(((javax.management.AttributeChangeNotification)notification).getNewValue());
				}
			}
		}
		
	}

}
