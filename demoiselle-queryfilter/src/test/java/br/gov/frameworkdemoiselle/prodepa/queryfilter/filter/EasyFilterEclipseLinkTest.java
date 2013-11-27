package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import org.junit.BeforeClass;

public class EasyFilterEclipseLinkTest extends EasyFilterTest {

  @BeforeClass
  public static void createPersistenceUnit() {
    createEntityManagerFactoryForEclipseLink();
    generateData();
  }

}