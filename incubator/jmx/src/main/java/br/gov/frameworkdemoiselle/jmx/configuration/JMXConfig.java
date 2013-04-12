package br.gov.frameworkdemoiselle.jmx.configuration;

import javax.management.NotificationBroadcaster;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;
import br.gov.frameworkdemoiselle.management.annotation.Managed;

@Configuration(prefix = "frameworkdemoiselle.management.jmx.")
public class JMXConfig {
	
	@Name("mbean.domain")
	private String mbeanDomain;
	
	@Name("notification.domain")
	private String notificationDomain;
	
	@Name("notification.name")
	private String notificationMBeanName = "NotificationBroadcaster";
	
	/**
	 * </p>The domain to register all {@link Managed} classes found during boot.</p>
	 * 
	 * <p>The full name of a MBean has the format of <code>domain:name=MBeanName</code> (ex: <code>br.gov.frameworkdemoiselle.jmx:name=NotificationBroadcaster</code>), this
	 * parameter is the "domain" portion of the full name.</p>
	 * 
	 * <p>The default is <code>null</code> and when is set to <code>null</code>, all {@link Managed} classes will use it's own package as the domain.</p>
	 * 
	 */
	public String getMbeanDomain() {
		return mbeanDomain;
	}
	
	/**
	 * @see #getMbeanDomain()
	 */
	public void setMbeanDomain(String mbeanDomain) {
		this.mbeanDomain = mbeanDomain;
	}

	/**
	 * <p>The name the {@link NotificationBroadcaster} MBean will be registered to. The full name
	 * of a MBean has the format of <code>domain:name=MBeanName</code> (ex: <code>br.gov.frameworkdemoiselle.jmx:name=NotificationBroadcaster</code>), this
	 * parameter is the ":name=MBeanName" portion without the ":name=".</p>
	 * 
	 * <p>The default is the value returned by {@link Class#getSimpleName()} when called from the {@link NotificationBroadcaster} class.</p>
	 * 
	 * @see #getMbeanDomain()
	 */
	public String getNotificationMBeanName() {
		return notificationMBeanName;
	}

	/**
	 * @see #getNotificationMBeanName()
	 */
	public void setNotificationMBeanName(String notificationMBeanName) {
		this.notificationMBeanName = notificationMBeanName;
	}

	/**
	 * </p>The domain to register the {@link NotificationBroadcaster} MBean.</p>
	 * 
	 * <p>The full name of a MBean has the format of <code>domain:name=MBeanName</code> (ex: <code>br.gov.frameworkdemoiselle.jmx:name=NotificationBroadcaster</code>), this
	 * parameter is the "domain" portion of the full name.</p>
	 * 
	 * <p>The default is <code>br.gov.frameworkdemoiselle.jmx</code>.</p>
	 * 
	 */
	public String getNotificationDomain() {
		return notificationDomain;
	}

	/**
	 * @see #getNotificationDomain()
	 */
	public void setNotificationDomain(String notificationDomain) {
		this.notificationDomain = notificationDomain;
	}
	
	

}
