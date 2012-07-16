package example;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.ConfigType;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource="system", type=ConfigType.XML)
public class XMLConfig {

	@Name("system.distribution")
	private String version;
	
	private String title;
	
	public String getVersion() {
		return version;
	}

	public String getTitle() {
		return title;
	}

}
