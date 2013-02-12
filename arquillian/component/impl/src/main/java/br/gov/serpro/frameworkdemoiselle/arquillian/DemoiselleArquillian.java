package br.gov.serpro.frameworkdemoiselle.arquillian;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DemoiselleArquillian {

	private static final String WEBAPP_SRC = "src/main/webapp";

	@Deployment
	public static WebArchive createDeployment() {
		
		return ShrinkWrap.create(WebArchive.class)
				.addAsResource(new File("target/classes/"), ArchivePaths.create(""))
				.setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
				.addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/beans.xml")), "beans.xml")
				.addAsResource(new FileAsset(new File("src/test/resources/META-INF/persistence.xml")), "META-INF/persistence.xml")
				.addAsLibraries(loadMavenDependencies());
	}

	private static File[] loadMavenDependencies() {
		
		File[] mvnLibs = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeAndTestDependencies()
                .asFile();
		
		return mvnLibs;
		
	}

}
