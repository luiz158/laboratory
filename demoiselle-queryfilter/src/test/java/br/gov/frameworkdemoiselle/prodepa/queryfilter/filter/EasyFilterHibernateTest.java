package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import org.junit.BeforeClass;

public class EasyFilterHibernateTest extends EasyFilterTest {
  @BeforeClass
  public static void createPersistenceUnit() {
    createEntityManagerFactoryForHibernate();
    generateData();
  }
}