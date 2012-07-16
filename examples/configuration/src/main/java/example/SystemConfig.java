package example;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.ConfigType;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(type=ConfigType.SYSTEM)
public class SystemConfig {

	@Name("os.name")
	private String operatingSytem;

	private String userName;
	
	public String getOperatingSytem() {
		return operatingSytem;
	}
	
	public String getUserName() {
		return userName;
	}
}
