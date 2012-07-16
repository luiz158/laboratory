package example;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource="system")
public class PropertiesConfig {

	private String backgroundColor;
	private String fontFamily;
	private String fontColor;
	private String fontSize;
	
	@Name("text-align")
	private String align;

	
	public String getBackgroundColor() {
		return backgroundColor;
	}

	
	public String getFontFamily() {
		return fontFamily;
	}

	
	public String getFontColor() {
		return fontColor;
	}

	
	public String getFontSize() {
		return fontSize;
	}

	
	public String getAlign() {
		return align;
	}
	
	
	
}
