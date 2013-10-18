package br.gov.serpro.extractor_sample;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.serpro.extractor_sample.configuration.SampleConfig;

public class Sample {

	@Inject
	private Logger logger;

	@Inject
	private SampleConfig config;

	public String getURLHost() {
		String host = config.getUrl().getHost();
		logger.info("Host: " + host);
		return host;
	}

	public String getURLProtocol() {
		String protocol = config.getUrl().getProtocol();
		logger.info("Protocolo: " + protocol);
		return protocol;
	}
	
	public String getURLPath() {
		String path = config.getUrl().getPath();
		logger.info("Path: " + path);
		return path;
	}
}
