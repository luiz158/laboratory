package br.gov.frameworkdemoiselle.jmx.internal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import br.gov.frameworkdemoiselle.jmx.configuration.JMXConfig;
import br.gov.frameworkdemoiselle.management.notification.NotificationManager;
import br.gov.frameworkdemoiselle.management.notification.event.NotificationEvent;
import br.gov.frameworkdemoiselle.management.notification.qualifier.AttributeChange;
import br.gov.frameworkdemoiselle.management.notification.qualifier.Generic;

/**
 * Implementation of the {@link NotificationBroadcaster} MBean. This MBean observes the {@link NotificationEvent} event.
 * When the {@link NotificationManager} sends an event, the methods from this class captures the notification, converts it
 * to a JMX notification and sends it to any management clients connected through JMX.
 * 
 * @author serpro
 *
 */
@ApplicationScoped
public class NotificationBroadcaster extends NotificationBroadcasterSupport implements NotificationBroadcasterMBean {
	
	private int sequenceNumber = 1;

	/*public static final String NOTIFICATION_DEFAULT_MBEAN_NAME = NotificationBroadcaster.class.getPackage().getName()
															+":name="
															+NotificationBroadcaster.class.getSimpleName();*/

	private static final String NOTIFICATION_TYPE_GENERIC = "jmx.message";

	public void sendNotification( @Observes @Generic NotificationEvent event , JMXConfig config ) {
		br.gov.frameworkdemoiselle.management.notification.Notification demoiselleNotification = event.getNotification();
		Notification n = new Notification(NOTIFICATION_TYPE_GENERIC, config.getNotificationMBeanName(), sequenceNumber++, System.currentTimeMillis(), demoiselleNotification.getMessage().toString());
		sendNotification(n);
	}
	
	public void sendAttributeChangedMessage( @Observes @AttributeChange NotificationEvent event , JMXConfig config ) {
		br.gov.frameworkdemoiselle.management.notification.AttributeChangeNotification demoiselleNotification = (br.gov.frameworkdemoiselle.management.notification.AttributeChangeNotification)event.getNotification();
		
		AttributeChangeNotification n = new AttributeChangeNotification(config.getNotificationMBeanName(), sequenceNumber++
				, System.currentTimeMillis(), demoiselleNotification.getMessage().toString()
				, demoiselleNotification.getAttributeName(), demoiselleNotification.getAttributeType().getSimpleName()
				, demoiselleNotification.getOldValue(), demoiselleNotification.getNewValue());
		
		sendNotification(n);
	}

}
