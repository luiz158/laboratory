package br.gov.demoiselle.escola.config;

import java.util.List;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.ConfigType;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "escola-papeis", type = ConfigType.XML)
public class PapelConfig {

	@Name(value = "papeis.papel")
	private List<String> papeis;

	public List<String> getPapeis() {
		return papeis;
	}

}
