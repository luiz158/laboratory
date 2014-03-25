package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@NamedQueries({ @NamedQuery(name = Anexo.ANEXOS_DA_DEMANDA_NA_FASE, query = "select new br.gov.serpro.catalogo.entity.Anexo(a.id, a.fase.id, a.nomeArquivo, a.tipoArquivo, a.tamanhoArquivo) from Anexo a where (a.fase.id = :fase)") })
@Entity
public class Anexo {

	public static final String ANEXOS_DA_DEMANDA_NA_FASE = "ANEXOS_DA_DEMANDA_NA_FASE";

	public Anexo() {

	}

	public Anexo(Long id, Long fase, String nomeArquivo, String tipoArquivo,
			Integer tamanhoArquivo) {
		this.id = id;
		this.fase = new Fase();
		this.fase.setId(fase);
		this.nomeArquivo = nomeArquivo;
		this.tamanhoArquivo = tamanhoArquivo;
		this.tipoArquivo = tipoArquivo;
	}

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "anexo_seq")
	@SequenceGenerator(name = "anexo_seq", sequenceName = "anexo_id_seq", allocationSize=1)
	private Long id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] arquivo;

	@ManyToOne(targetEntity=Fase.class) 
    @JoinColumn(name="fase_id", nullable=false, updatable=false)
	private Fase fase;

	private String nomeArquivo;

	private String tipoArquivo;

	private Integer tamanhoArquivo;

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

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

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

	public Integer getTamanhoArquivo() {
		return tamanhoArquivo;
	}

	public void setTamanhoArquivo(Integer tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}

}
