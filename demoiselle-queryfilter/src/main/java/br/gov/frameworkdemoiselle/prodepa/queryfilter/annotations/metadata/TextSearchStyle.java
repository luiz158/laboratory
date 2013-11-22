package br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata;

public enum TextSearchStyle {
  
  /**
   * Pesquisa em todos os campos String usado o Like
   */
  Like,
  
  /**
   * Pesquisa em todos os campos String usado o Not Like
   */
  NotLike, //TODO Não suportado pelo EasyCriteria
  
  /**
   * Pesquisa em todos os campos String usado o Like e Aplicando o LOWER case
   */
  LikeIgnoreCase,
  
  /**
   * Pesquisa em todos os campos String usado o Not Like e Aplicando o LOWER case
   */
  NotLikeIgnoreCase, //TODO Não suportado pelo EasyCriteria
  
  ;
}
