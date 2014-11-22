package br.gov.serpro.catalogo.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;

@Entity
public class GrupoHistorico {

	public enum OPERACAO{CRIAR, ATUALIZAR, EXCLUIR, INSERIR_USUARIO}

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "fase_historico_seq")
	@SequenceGenerator(name = "fase_historico_seq", sequenceName = "fase_historico_id_seq", allocationSize=1)
	private Long id;
	
	@Transient
	private List<Grupo> listaGrupos;
	
	private String grupo;
	
	private String usuarioRresponsavelAlteracao;
	
	private String usuarioAlterado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private OPERACAO operacao;
	
	@Column(length=2000)
	private String descricao;
	
	@Transient
	private String horario;

	public GrupoHistorico(){
		
	}
	
	public GrupoHistorico(Grupo grupo, OPERACAO operacao) {
		User user = (User)Beans.getReference(SecurityContext.class).getUser();
		this.usuarioRresponsavelAlteracao = user.getCPF();
		this.grupo = grupo.getNome();
		this.operacao = operacao;
		this.data = new Date();
		this.usuarioAlterado = null;
		this.descricao = gerarDescricao();
	}
	
	public GrupoHistorico(List<Grupo> listaGrupos, OPERACAO operacao, User user) {
		User usuarioResponsavelAlteracao = (User)Beans.getReference(SecurityContext.class).getUser();
		this.usuarioRresponsavelAlteracao = usuarioResponsavelAlteracao.getCPF();
		this.listaGrupos = listaGrupos;
		this.operacao = operacao;
		this.data = new Date();
		this.usuarioAlterado = user.getCPF();
		this.descricao = gerarDescricaoInserirUsuario();
	}
	
	private String gerarDescricao(){
		StringBuffer sb = new StringBuffer();
		sb.append("Executou a ação: ").append(operacao.name());
		sb.append(" o Grupo: ").append(grupo);
		return sb.toString();
	}

	private String gerarDescricaoInserirUsuario() {
		StringBuffer sb = new StringBuffer();
		if((this.listaGrupos != null) && (this.listaGrupos.size() > 0)){
			sb.append("Executou a ação: ").append(operacao.name());
			sb.append(" nos Grupos: ");
			
			Iterator<Grupo> i = this.listaGrupos.iterator();
			int cont = 1;
			while(i.hasNext()) {
				Grupo grupo = (Grupo)i.next();
				if(cont == this.listaGrupos.size()) {
					sb.append(grupo.getNome());
				}else {
					sb.append(grupo.getNome()+", ");
				}
				cont++;
			}
		}
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setGrupo(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public String getUsuario() {
		return usuarioRresponsavelAlteracao;
	}

	public void setUsuario(String usuario) {
		this.usuarioRresponsavelAlteracao = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OPERACAO getOperacao() {
		return operacao;
	}

	public void setOperacao(OPERACAO operacao) {
		this.operacao = operacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public String getGrupo() {
		return this.grupo;
	}
}