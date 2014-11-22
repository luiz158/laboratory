package br.gov.frameworkdemoiselle.prodepa.queryfilter.app.filter;

import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.TextSearch;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata.TextSearchStyle;
import br.gov.frameworkdemoiselle.prodepa.queryfilter.stereotype.QueryFilter;

@QueryFilter
public class CommonFilter {

  private Long id;
  
  @TextSearch(strategy = TextSearchStyle.Like)
  private String filter;
  
  //@SQL("(select :paran)")
  //private Orgao orgao;

  public CommonFilter() {
    super();
  }

  public CommonFilter(String filter) {
    super();
    this.filter = filter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }
  
  
  
}
