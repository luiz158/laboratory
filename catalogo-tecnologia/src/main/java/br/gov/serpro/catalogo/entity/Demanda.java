package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Demanda {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;

	@NotEmpty
	private String demandante;
	
	@NotEmpty
	private String detalhamento;
	
	private String codigoReferencia;
	
	private String origemReferencia;
	
	//@NotNull
	private Date dataCriacao;
	
	@Enumerated(EnumType.ORDINAL)
	private Fase faseAtual;
	
	@Null
	@Enumerated(EnumType.ORDINAL)
	private Fase faseAnterior;
}
