package br.gov.frameworkdemoiselle.prodepa.queryfilter.filter;

import javax.persistence.EntityManager;


public class EasyQueryManager<T>  {
  
  private Class<T> beanClass;
  
  private EntityManager em;
  
  public EasyQueryManager(Class<T> beanClass, EntityManager em) {
    super();
    this.beanClass = beanClass;
    this.em = em;
  }

  /* E importante que essa criacao seja o mais leve possível*/
  public EasyQuery<T> createQuery() {
    return new EasyQueryImpl<T>(beanClass, em);
  }
  
}
