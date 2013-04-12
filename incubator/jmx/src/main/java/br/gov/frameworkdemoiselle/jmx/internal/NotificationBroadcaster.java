package br.gov.frameworkdemoiselle.jmx.internal;

import java.io.Serializable;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import br.gov.frameworkdemoiselle.jmx.configuration.JMXConfig;
import br.gov.frameworkdemoiselle.management.notification.NotificationManager;
import br.gov.frameworkdemoiselle.management.notification.event.NotificationEvent;

/**
 * Implementation of the {@link NotificationBroadcaster} MBean.
 * When the {@link NotificationManager} sends an event, a {@link NotificationEventListener} captures the notification and uses
 * this MBean to send it as a JMX notification.
 * 
 * @author serpro
 *
 */
final class NotificationBroadcaster extends NotificationBroadcasterSupport implements NotificationBroadcasterMBean,Serializable {
	
	private static final long serialVersionUID = 1L;

	private int sequenceNumber = 1;

	/*public static final String NOTIFICATION_DEFAULT_MBEAN_NAME = NotificationBroadcaster.class.getPackage().getName()
															+":name="
															+NotificationBroadcaster.class.getSimpleName();*/

	private static final String NOTIFICATION_TYPE_GENERIC = "jmx.message";

	protected void sendNotification( NotificationEvent event , JMXConfig config ) {
		br.gov.frameworkdemoiselle.management.notification.Notification demoiselleNotification = event.getNotification();
		Notification n = new Notification(NOTIFICATION_TYPE_GENERIC, config.getNotificationMBeanName(), sequenceNumber++, System.currentTimeMillis(), demoiselleNotification.getMessage().toString());
		sendNotification(n);
	}
	
	protected void sendAttributeChangedMessage( NotificationEvent event , JMXConfig config ) {
		br.gov.frameworkdemoiselle.management.notification.AttributeChangeNotification demoiselleNotification = (br.gov.frameworkdemoiselle.management.notification.AttributeChangeNotification)event.getNotification();
		
		AttributeChangeNotification n = new AttributeChangeNotification(config.getNotificationMBeanName(), sequenceNumber++
				, System.currentTimeMillis(), demoiselleNotification.getMessage().toString()
				, demoiselleNotification.getAttributeName(), demoiselleNotification.getAttributeType().getSimpleName()
				, demoiselleNotification.getOldValue(), demoiselleNotification.getNewValue());
		
		sendNotification(n);
	}

}
