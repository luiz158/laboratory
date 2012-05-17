package br.org.frameworkdemoiselle.contas.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Compromisso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1852686119515013719L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String nomeCompromisso;
	
	@Column(nullable=false)
    @Temporal(value=TemporalType.DATE)
	private Date dataVencimento;
	
	@Temporal(value=TemporalType.DATE)
	private Date dataPagamento;
	
	@Column(nullable=false)
	private Long valorCompromisso;
	
	private Long valorPago;
	
	private boolean atrasado;
	
	
	public Compromisso(String nomeCompromisso, Date dataVencimento,
			Date dataPagamento, Long valorCompromisso, Long valorPago) {
		super();
		this.nomeCompromisso = nomeCompromisso;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valorCompromisso = valorCompromisso;
		this.valorPago = valorPago;
	}
	
	public Compromisso(){
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompromisso() {
		return nomeCompromisso;
	}

	public void setNomeCompromisso(String nomeCompromisso) {
		this.nomeCompromisso = nomeCompromisso;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Long getValorCompromisso() {
		return valorCompromisso;
	}

	public void setValorCompromisso(Long valorCompromisso) {
		this.valorCompromisso = valorCompromisso;
	}

	public Long getValorPago() {
		return valorPago;
	}

	public void setValorPago(Long valorPago) {
		this.valorPago = valorPago;
	}

	public boolean isAtrasado() {
		return atrasado;
	}

	public void setAtrasado(boolean atrasado) {
		this.atrasado = atrasado;
	}


	@Override
	public boolean equals(Object outro) {
		return ((Compromisso) outro).nomeCompromisso.equals(this.nomeCompromisso);
	}

}
