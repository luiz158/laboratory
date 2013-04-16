package br.gov.frameworkdemoiselle.management.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.management.ReflectionException;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.internal.context.Contexts;
import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.internal.ManagedType.MethodDetail;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * A manager that helps implementators of the management framework to obtain a
 * list of managed classes, set or obtain values from them or invoke operations
 * over them.
 * 
 * @author serpro
 */
@ApplicationScoped
public class MonitoringManager {

	@Inject
	private Logger logger;

	@Inject
	private ResourceBundle bundle;

	private final List<ManagedType> managedTypes = new ArrayList<ManagedType>();

	public void addManagedType(ManagedType managedType) {
		managedTypes.add(managedType);
	}

	/**
	 * @return A list all managed types, classes annotated with {@link Managed}.
	 *         The returned list is a shallow copy of the internal list, so you
	 *         are free to modify it.
	 *         
	 *         TODO precisamos desse clone na lista?
	 */
	public List<ManagedType> getManagedTypes() {
		ArrayList<ManagedType> cloneList = new ArrayList<ManagedType>();
		cloneList.addAll(managedTypes);
		return cloneList;
	}

	/**
	 * Invoke an operation over a managed type.
	 * 
	 * @param managedType
	 *            A type annotated with {@link Managed}. This method will create
	 *            an (or obtain an already created) instance of this type and
	 *            invoke the operation over it.
	 * @param actionName
	 *            Name of method to be invoked, the type must have this
	 *            operation on it's list
	 * @param params
	 *            List of values for the operation parameters. Can be
	 *            <code>null</code> if the operation require no parameters.
	 * @return The return value of the original invoked operation. Methods of
	 *         return type <code>void</code> will return the {@link Void} type.
	 * @throws ReflectionException
	 *             In case the operation doesn't exist or have a different
	 *             signature
	 */
	public Object invoke(Class<?> managedType, String actionName,
			Object[] params) {
		final int index = managedTypes.indexOf(managedType);

		if (index > -1) {
			activateContexts(managedType);

			ManagedType type = managedTypes.get(index);
			Object delegate = Beans.getReference(type.getType());

			MethodDetail method = type.getOperationMethods().get(actionName);

			if (method != null) {
				try {
					logger.debug(bundle
							.getString("management-debug-invoking-operation"));
					return method.getMethod().invoke(delegate, params);
				} catch (Exception e) {
					throw new DemoiselleException(bundle.getString(
							"management-invoke-error", actionName), e);
				} finally {
					deactivateContexts(managedType);
				}
			} else {
				throw new DemoiselleException(bundle.getString(
						"management-invoke-error", actionName));
			}
		} else {
			throw new DemoiselleException(
					bundle.getString("management-type-not-found"));
		}
	}

	public Object getAttribute(Class<?> managedType, String attribute) {
		final int index = managedTypes.indexOf(managedType);

		if (index > -1) {
			ManagedType type = managedTypes.get(index);

			Method getterMethod = type.getGetterMethods().get(attribute);

			if (getterMethod != null) {
				logger.debug(bundle.getString(
						"management-debug-acessing-property", getterMethod
								.getName(), type.getType().getCanonicalName()));

				activateContexts(managedType);

				try {
					Object delegate = Beans.getReference(type.getType());
					return getterMethod.invoke(delegate, (Object[]) null);
				} catch (Exception e) {
					throw new DemoiselleException(bundle.getString(
							"management-invoke-error", getterMethod.getName()),
							e);
				} finally {
					deactivateContexts(managedType);
				}
			} else {
				throw new DemoiselleException(bundle.getString(
						"management-invoke-error", attribute));
			}
		} else {
			throw new DemoiselleException(
					bundle.getString("management-type-not-found"));
		}
	}

	public void setAttribute(Class<?> managedType, String attribute,
			Object newValue) {

		final int index = managedTypes.indexOf(managedType);

		if (index > -1) {
			ManagedType type = managedTypes.get(index);

			// Procura o método get do atributo em questão
			Method method = type.getSetterMethods().get(attribute);
			if (method != null) {
				logger.debug(bundle.getString(
						"management-debug-setting-property", method.getName(),
						managedType.getCanonicalName()));

				// Obtém uma instância da classe gerenciada, lembrando que
				// classes
				// anotadas com @Managed são sempre singletons.
				activateContexts(managedType);
				try {
					Object delegate = Beans.getReference(managedType);
					method.invoke(delegate, new Object[] { newValue });
				} catch (Exception e) {
					throw new DemoiselleException(bundle.getString(
							"management-invoke-error", method.getName()), e);
				} finally {
					deactivateContexts(managedType);
				}

			} else {
				throw new DemoiselleException(bundle.getString(
						"management-invoke-error", attribute));
			}
		} else {
			throw new DemoiselleException(
					bundle.getString("management-type-not-found"));
		}

	}

	private void activateContexts(Class<?> managedType) {
		logger.debug("management-debug-starting-custom-context",
				ManagedContext.class.getCanonicalName(),
				managedType.getCanonicalName());
		Contexts.activate(ManagedContext.class);
	}

	private void deactivateContexts(Class<?> managedType) {
		logger.debug("management-debug-stoping-custom-context",
				ManagedContext.class.getCanonicalName(),
				managedType.getCanonicalName());
		Contexts.deactivate(ManagedContext.class);
	}
	
	public void shutdown() {
		
		// TODO implementar...
		
	}

	public void initialize(Collection<Class<? extends MonitoringExtension>> monitoringExtensions) {
		
		for (Class<? extends MonitoringExtension> monitoringExtensionClass : monitoringExtensions) {
			
			MonitoringExtension monitoringExtension = Beans.getReference(monitoringExtensionClass);
			
			monitoringExtension.initialize(this.getManagedTypes());
			
		}
		
	}

}
