package br.gov.frameworkdemoiselle.jmx.internal;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.producer.LoggerProducer;

/**
 * Class with common tools for registering MBeans into an Managed server
 * 
 * @author SERPRO
 */
public class MBeanHelper {

	private static final Logger logger = LoggerProducer.create(MBeanHelper.class);

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

		logger.info("Registering MBean [" + name + "]: " + mbean);

		ObjectInstance instance = null;
		try {
			ObjectName objectName = new ObjectName(name);
			instance = server.registerMBean(mbean, objectName);
		} catch (Exception e) {
			throw new DemoiselleException("Unable to register MBean [" + name + "]", e);
		}

		return instance;
	}

	/**
	 * Remove the registration of a bean.
	 * 
	 * @param objectName
	 *            the name of the bean to unregister
	 */
	public static void unregister(final ObjectName objectName) {

		logger.info("Unregistering MBean [" + objectName + "]");

		try {
			server.unregisterMBean(objectName);
		} catch (Exception e) {
			throw new DemoiselleException("Unable to unregister MBean [" + objectName + "]", e);
		}
	}

	/**
	 * Perform a JMX query given an MBean name and the name of an attribute on that MBean.
	 * 
	 * @param name
	 *            the object name of the MBean to query
	 * @param attribute
	 *            the attribute to query for
	 * @return the value of the attribute
	 */
	public static Object query(final String name, final String attribute) {

		logger.debug("JMX query: [" + name + "][" + attribute + "]");

		// retrieve MBean instance (already registered)
		ObjectInstance bean = null;
		try {
			bean = server.getObjectInstance(new ObjectName(name));
		} catch (Exception e) {
			throw new DemoiselleException("Error retrieving MBean instance [" + name + "]", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Found MBean class " + bean.getClassName());
		}

		// query MBean simple attribute
		final int dot = attribute.indexOf('.');
		if (dot < 0) {
			Object ret = null;
			try {
				ret = server.getAttribute(new ObjectName(name), attribute);
			} catch (Exception e) {
				throw new DemoiselleException("Error querying MBean simple attribute [" + name + "][" + attribute + "]", e);
			}
			return ret;
		}

		// query MBean composite attribute
		try {
			CompositeData data = (CompositeData) server.getAttribute(new ObjectName(name), attribute.substring(0, dot));
			String field = attribute.substring(dot + 1);
			return resolveFields(data, field);
		} catch (Exception e) {
			throw new DemoiselleException("Error querying MBean composite attribute [" + name + "][" + attribute + "]", e);
		}
	}

	/**
	 * Support method intended to resolve fields when using attributes with composite data types.
	 * 
	 * @param attribute
	 *            the attribute instance
	 * @param field
	 *            the field name which value must be retrieved
	 * @return the value corresponding to the composite data type field
	 * @throws Exception
	 */
	private static Object resolveFields(final CompositeData attribute, final String field) throws Exception {

		final int dot = field.indexOf('.');
		if (dot < 0) {
			final Object ret = attribute.get(field);
			return ret;
		}

		return resolveFields((CompositeData) attribute.get(field.substring(0, dot)), field.substring(dot + 1));
	}
}
