package br.gov.frameworkdemoiselle.prodepa.queryfilter.annotations.metadata;

public enum AndConditionType {

  EQUAL(" = "),
  NOT_EQUALS(" <> "),
  GT(""),       //STRING_GREATER_THAN, CALENDAR_GREATER_THAN, DATE_GREATER_THAN,
  GE(""),       //STRING_GREATER_OR_EQUAL_TO, CALENDAR_GREATER_OR_EQUAL_TO, DATE_GREATER_OR_EQUAL_TO,
  LT(""),       //STRING_LESS_THAN, CALENDAR_LESS_THAN, DATE_LESS_THAN,
  LE(""),       //STRING_LESS_OR_EQUAL_TO, CALENDAR_LESS_OR_EQUAL_TO, DATE_LESS_OR_EQUAL_TO,
  BETWEEN(""), //TODO ???? //STRING_BETWEEN, DATE_BETWEEN, CALENDAR_BETWEEN,
  IS_NULL(""), IS_NOT_NULL(""),
  LIST_IS_EMPTY(""), LIST_IS_NOT_EMPTY(""), //SET_IS_EMPTY, SET_IS_NOT_EMPTY, COLLECTION_IS_EMPTY, COLLECTION_IS_NOT_EMPTY,
  LIKE(""), NOT_LIKE(""), LIKE_STARTS_WITH(""), LIKE_ENDS_WITH(""),
  IN(""), NOT_IN(""), //STRING_IN;
  ;
  
  private String operator;

  private AndConditionType(String operator) {
    this.operator = operator;
  }

  public String getOperator() {
    return operator;
  }
  
}
