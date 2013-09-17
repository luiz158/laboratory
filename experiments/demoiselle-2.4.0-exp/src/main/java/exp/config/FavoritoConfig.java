package exp.config;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "favoritos", prefix = "general.")
public class FavoritoConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("app.title")
	private String applicationTitle;

//	@NotNull
	private boolean loadInitialData;

	public String getApplicationTitle() {
		return applicationTitle;
	}

	public boolean isLoadInitialData() {
		return loadInitialData;
	}

}
