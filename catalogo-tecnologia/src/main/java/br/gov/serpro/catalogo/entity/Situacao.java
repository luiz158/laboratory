package br.gov.serpro.catalogo.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public enum Situacao {

	APROVADO("Aprovado","A"), REPROVADO("Reprovado","R"), RASCUNHO("Rascunho","X");

	private final String sigla;
	private final String value;

	Situacao(String value, String sigla) {
		this.value = value;
		this.sigla = sigla;
	}

	public String toString() {
		return this.sigla;
	}
	
	@JsonValue
	public String toJsonString() {
		return this.value;
	}
	
	@JsonCreator
	public static Situacao fromValue(String value) {
		Situacao result = null;

		for (Situacao situacao : values()) {
			if (situacao.value.equalsIgnoreCase(value)) {
				result = situacao;
				break;
			}
		}

		return result;
	}
}
