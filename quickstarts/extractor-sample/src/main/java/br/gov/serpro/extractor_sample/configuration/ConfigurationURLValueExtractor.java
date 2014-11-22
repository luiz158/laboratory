package br.gov.serpro.extractor_sample.configuration;

import java.lang.reflect.Field;
import java.net.URL;

import org.apache.commons.configuration.Configuration;

import br.gov.frameworkdemoiselle.configuration.ConfigurationValueExtractor;

public class ConfigurationURLValueExtractor implements ConfigurationValueExtractor {

	@Override
	public Object getValue(String prefix, String key, Field field, Configuration configuration) throws Exception {
		String value = configuration.getString(prefix + key);

		if (value != null) {
			return new URL(value);
		} else {
			return null;
		}
	}

	@Override
	public boolean isSupported(Field field) {
		return field.getType() == URL.class;
	}

}
