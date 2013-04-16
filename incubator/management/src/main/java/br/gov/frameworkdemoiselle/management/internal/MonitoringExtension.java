package br.gov.frameworkdemoiselle.management.internal;

import java.util.List;

/**
 * 
 * Define an entry point for monitoring extension.
 * 
 * @author serpro
 *
 */
public interface MonitoringExtension {

	void initialize(List<ManagedType> managedTypes);

}
