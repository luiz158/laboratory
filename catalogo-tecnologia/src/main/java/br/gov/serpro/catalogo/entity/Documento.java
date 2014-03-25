package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "documento_seq")
	@SequenceGenerator(name = "documento_seq", sequenceName = "documento_id_seq", allocationSize=1)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	private Fase fase;
	
	private String rgContrato;
	
	private String documentoOficialDemanada;
	
	public Fase getFase() {
		return fase;
	}
	public void setFase(Fase internalizacao) {
		this.fase = internalizacao;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocumentoOficialDemanada() {
		return documentoOficialDemanada;
	}
	public void setDocumentoOficialDemanada(String documentoOficialDemanada) {
		this.documentoOficialDemanada = documentoOficialDemanada;
	}
	public String getRgContrato() {
		return rgContrato;
	}
	public void setRgContrato(String rgContrato) {
		this.rgContrato = rgContrato;
	}
	
	
}
