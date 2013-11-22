package br.gov.frameworkdemoiselle.prodepa.queryfilter.types;

/**
 * Classe utilitaria para a definicao de intervalos em pesquisas
 * 
 * TODO Certificar de que funciona bem no JSF 
 * 
 * @author thiago
 *
 * @param <T>
 */
public class Range<T> {

  private T start;
  private T end;

  public Range() {
    super();
  }

  public Range(T start, T end) {
    super();
    this.start = start;
    this.end = end;
  }

  public T getEnd() {
    return end;
  }

  public void setEnd(T end) {
    this.end = end;
  }

  public T getStart() {
    return start;
  }

  public void setStart(T start) {
    this.start = start;
  }

}
