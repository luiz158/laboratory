package br.gov.frameworkdemoiselle.jmx.internal;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import br.gov.frameworkdemoiselle.jmx.configuration.JMXConfig;
import br.gov.frameworkdemoiselle.management.internal.notification.event.NotificationEvent;
import br.gov.frameworkdemoiselle.management.internal.notification.qualifier.AttributeChange;
import br.gov.frameworkdemoiselle.management.internal.notification.qualifier.Generic;
import br.gov.frameworkdemoiselle.management.notification.NotificationManager;

/**
 * Listens to {@link NotificationManager} notification events and proxies them
 * to a {@link NotificationBroadcaster} MBean. This MBean will send the notification to
 * any JMX clients connected and listening.
 * 
 * @author serpro
 *
 */
@ApplicationScoped
@SuppressWarnings("serial")
public class NotificationEventListener implements Serializable {
	
	private NotificationBroadcaster notificationBroadcaster;
	
	public void sendNotification( @Observes @Generic NotificationEvent event , JMXConfig config ) {
		createNotificationBroadcaster().sendNotification(event,config);
	}
	
	public void sendAttributeChangedMessage( @Observes @AttributeChange NotificationEvent event , JMXConfig config ) {
		createNotificationBroadcaster().sendAttributeChangedMessage(event, config);
	}
	
	public NotificationBroadcaster createNotificationBroadcaster(){
		if (notificationBroadcaster==null){
			notificationBroadcaster = new NotificationBroadcaster();
		}
		
		return notificationBroadcaster;
	}

}
