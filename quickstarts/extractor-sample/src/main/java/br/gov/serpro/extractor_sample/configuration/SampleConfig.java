package br.gov.serpro.extractor_sample.configuration;

import java.net.URL;

import javax.validation.constraints.NotNull;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "bookmark")
public class SampleConfig {

	@NotNull
	private URL url;

	public URL getUrl() {
		return url;
	}
}
