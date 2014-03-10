package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;
	
	private String documentoOficialDemanada;
	private String rgContrato;
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
