package br.gov.frameworkdemoiselle.management.internal;

import java.util.List;

/**
 * 
 * Define an entry point for monitoring extension.
 * 
 * @author serpro
 *
 */
public interface ManagementExtension {

	void initialize(List<ManagedType> managedTypes);

	void shutdown(List<ManagedType> managedTypes);

}
