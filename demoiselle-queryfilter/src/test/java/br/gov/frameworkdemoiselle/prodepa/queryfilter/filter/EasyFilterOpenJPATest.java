package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import org.junit.BeforeClass;

public class EasyFilterOpenJPATest extends EasyFilterTest {

  @BeforeClass
  public static void createPersistenceUnit() {
    createEntityManagerFactoryForOpenJPA();
    generateData();
  }
  
}