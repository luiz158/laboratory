package example;

import javax.inject.Inject;


public class Example {

	@Inject
	private XMLConfig xml;
	
	@Inject
	private SystemConfig system;
	
	@Inject
	private PropertiesConfig properties;
	
	public void print() {
		System.out.println("TITLE.............: " + xml.getTitle());
		System.out.println("VERSION...........: " + xml.getVersion());
		System.out.println("TEXT ALIGN........: " + properties.getAlign());
		System.out.println("BACKGROUND COLOR..: " + properties.getBackgroundColor());
		System.out.println("FONT COLOR........: " + properties.getFontColor());
		System.out.println("FONT FAMILY.......: " + properties.getFontFamily());
		System.out.println("FONT SIZE.........: " + properties.getFontSize());
		System.out.println("OPERATING SYSTEM..: " + system.getOperatingSytem());
		System.out.println("USER NAME.........: " + system.getUserName());
	}
	
	
	
}

