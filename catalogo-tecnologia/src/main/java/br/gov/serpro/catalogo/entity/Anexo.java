package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries({  
	  @NamedQuery(
			  name = Anexo.ANEXOS_DA_DEMANDA__NA_FASE, 
			  query = "select new br.gov.serpro.catalogo.entity.Anexo(a.id, a.analise.id, a.fase, a.nomeArquivo, a.tipoArquivo, a.tamanhoArquivo) from Anexo a where (a.analise.id = :demanda) and (a.fase = :fase)")
	}) 
@Entity
public class Anexo {
	
	public static final String ANEXOS_DA_DEMANDA__NA_FASE ="ANEXOS_DA_DEMANDA__NA_FASE";

	public Anexo(){
		
	}
	
	public Anexo(Long id,Long analise,Integer fase,String nomeArquivo,String tipoArquivo,Integer tamanhoArquivo){
		this.id = id;
		this.analise = new Analise();
		this.analise.setId(analise);
		this.fase = fase;
		this.nomeArquivo = nomeArquivo;
		this.tamanhoArquivo = tamanhoArquivo;
		this.tipoArquivo = tipoArquivo;		
	}
	
	
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;
	
	@Lob @Basic(fetch=FetchType.LAZY)
	private byte[] arquivo;
	
	@ManyToOne
	private Analise analise;
	
	private Integer fase;
	
	private String nomeArquivo;
	
	private String tipoArquivo;
	
	private Integer tamanhoArquivo;
	

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Analise getAnalise() {
		return analise;
	}

	public void setAnalise(Analise analise) {
		this.analise = analise;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	public Integer getTamanhoArquivo() {
		return tamanhoArquivo;
	}

	public void setTamanhoArquivo(Integer tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}
	
	

}
