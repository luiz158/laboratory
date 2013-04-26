package br.gov.frameworkdemoiselle.jmx.internal;

import java.lang.management.ManagementFactory;
import java.util.Locale;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;
import br.gov.frameworkdemoiselle.internal.producer.ResourceBundleProducer;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * Class with common tools for registering MBeans into an Managed server
 * 
 * @author SERPRO
 */
public class MBeanHelper {

	private static final Logger logger = LoggerProducer.create(MBeanHelper.class);
	
	private static ResourceBundle bundle = ResourceBundleProducer.create("demoiselle-jmx-bundle", Locale.getDefault());

	private static final MBeanServer server = ManagementFactory.getPlatformMBeanServer();

	// @Inject
	// @Name("demoiselle-monitoring-bundle")
	// private ResourceBundle bundle;

	/**
	 * Return the MBean Server instance.
	 * 
	 * @return MBeanServer
	 */
	public static final MBeanServer getMBeanServer() {
		return server;
	}

	/**
	 * Register a given managed bean (MBean) with the specified name.
	 * 
	 * @param mbean
	 *            the managed bean to register
	 * @param name
	 *            the name under which to register the bean
	 * @return the object name of the mbean, for later deregistration
	 */
	public static ObjectInstance register(final Object mbean, final String name) {

		logger.info(bundle.getString("mbean-registration",name));

		ObjectInstance instance = null;
		try {
			ObjectName objectName = new ObjectName(name);
			instance = server.registerMBean(mbean, objectName);
		} catch (Exception e) {
			logger.error(bundle.getString("mbean-registration-error",name),e);
			throw new DemoiselleException(bundle.getString("mbean-registration-error",name), e);
		}

		return instance;
	}
	
	/**
	 * Remove the registration of a mbean.
	 * 
	 * @param objectName
	 *            the name of the bean to unregister
	 */
	public static void unregister(final ObjectName objectName) {

		logger.info(bundle.getString("mbean-deregistration",objectName.getCanonicalName()));

		try {
			server.unregisterMBean(objectName);
		} catch (Exception e) {
			logger.error(bundle.getString("mbean-deregistration",objectName.getCanonicalName()),e);
			throw new DemoiselleException(bundle.getString("mbean-deregistration",objectName.getCanonicalName()), e);
		}
	}
}
