package br.gov.serpro.extractor_sample;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class SampleTest {

	@Inject
	private Sample sample;

	@Test
	public void readInformations() throws IOException {
		assertEquals("demoiselle.sourceforge.net", sample.getURLHost());
		assertEquals("http", sample.getURLProtocol());
		assertEquals("/docs/framework/reference/2.4.0-RC1/html/configuracao.html", sample.getURLPath());
	}
}
