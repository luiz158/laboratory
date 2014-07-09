package br.gov.serpro.catalogo.bussiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.serpro.catalogo.AppConfig;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseHistorico.OPERACAO;
import br.gov.serpro.catalogo.entity.FaseInteressado;
import br.gov.serpro.catalogo.entity.FaseMembro;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.event.FaseEvent;
import br.gov.serpro.catalogo.event.FaseEvent.ADD_INTERESSADO;
import br.gov.serpro.catalogo.event.FaseEvent.ADD_MEMBRO;
import br.gov.serpro.catalogo.event.FaseEvent.ATUALIZAR;
import br.gov.serpro.catalogo.event.FaseEvent.CRIAR;
import br.gov.serpro.catalogo.event.FaseEvent.FINALIZAR;
import br.gov.serpro.catalogo.persistence.FaseInteressadoDAO;
import br.gov.serpro.catalogo.persistence.FaseMembroDAO;

@BusinessController
public class EmailBC{

	@Inject
	private Logger logger;
	
	@Inject
	private FaseMembroDAO faseMemebroDAO;
	
	@Inject
	private FaseInteressadoDAO faseInteressadoDAO;
	
	@Inject
	private AppConfig config;
		
	private static final String FROM = "naoresponda@catalogotecnologia.serpro";
	private static final String PROTOCOL = "smtp";
	private static final String HOST = "10.31.181.156";
		
	private String getAssunto(String operacao){
		return "Catalogo de tecnologia: "+operacao;
	}
	
	private String gerarTextoMembroAdicionado(String nome, Fase fase){
		String nomeFase = fase.getFase().name().toLowerCase();
		StringBuffer sb = new StringBuffer();
		sb.append("<p><b>").append(nome).append("</b>,você foi adicionado a equipe de ");
		sb.append(nomeFase).append("</p><br>");
		sb.append("<p>Para visualizar acesse a seguinte URL: ").append(getFaseUrl(fase, nomeFase)).append("</p>");		
		return sb.toString();
	}
		
	private String gerarTextoMembrosInteressados(Fase fase){		
		String nomeFase = fase.getFase().name().toLowerCase();
		StringBuffer sb = new StringBuffer();
		sb.append("<p>Ocorreram mudanças na ");
		sb.append(nomeFase).append("</p><br>");
		sb.append("<p>Para visualizar acesse a seguinte URL: ").append(getFaseUrl(fase, nomeFase)).append("</p>");
		return sb.toString();
	}
	
	private String gerarTextoInteressadoAdicionado(String nome, Fase fase){
		String nomeFase = fase.getFase().name().toLowerCase();
		StringBuffer sb = new StringBuffer();
		sb.append("<p><b>").append(nome).append("</b>,você foi adicionado ao conjunto de interessados por uma ");
		sb.append(nomeFase).append("</p><br>");
		sb.append("<p>Para visualizar acesse a seguinte URL: ").append(getFaseUrl(fase, nomeFase)).append("</p>");		
		return sb.toString();
	}

	private String getFaseUrl(Fase fase, String nomeFase) {
		String url = config.getUrl()+"/home.html#/"+nomeFase+"/edit/"+fase.getId();
		return url;
	}
	
	public void usuarioAdicionadoNaEquipe(@Observes @ADD_MEMBRO FaseEvent e){
        logger.debug("FaseEvent @ADD_MEMBRO: " + e.getFase().getFase());		
		if (e.getUser().getEmail() != null && !e.getUser().getEmail().isEmpty()) {			
			enviarEmail(e.getUser().getEmail(),
					getAssunto("Membro de uma "+e.getFase().getFase().name()),
					gerarTextoMembroAdicionado(e.getUser().getName(), e.getFase()));	
		} else {
			throw new ValidationException().addViolation(null, "Usuário ("
					+ e.getUser().getCPF() + ") não possui e-mail");
		}		
	}
	
	public void interessadoAdicionado(@Observes @ADD_INTERESSADO FaseEvent e){
        logger.debug("FaseEvent @ADD_INTERESSADO: " + e.getFase().getFase());		
		if (e.getUser().getEmail() != null && !e.getUser().getEmail().isEmpty()) {				
			enviarEmail(e.getUser().getEmail(),
					getAssunto("Interessado em uma "+e.getFase().getFase().name()),
					gerarTextoInteressadoAdicionado(e.getUser().getName(), e.getFase()));
			
		} else {
			throw new ValidationException().addViolation(null, "Usuário ("
					+ e.getUser().getCPF() + ") não possui e-mail");
		}		
	}
	
	private void enviarEmail(String para, String titulo, String corpo){				
//		Properties properties = System.getProperties();
//		properties.setProperty("mail.smtp.host", HOST);
//		Session session = Session.getInstance(properties, null);	
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(FROM));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
//			message.setSubject(titulo);
//			message.setText(corpo);
//			Transport transport = session.getTransport(PROTOCOL);
//			transport.connect();
//			if(!config.isDebug()){
//				transport.sendMessage(message, message.getAllRecipients());
//			}else{
//				logger.warn("Enviaria e-mail: "+para);
//				logger.warn(corpo);
//			}
//			transport.close();
//			logger.debug("Enviando finalizado");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
	}
	
	private void enviarEmail(List<String> para, String titulo, String corpo){	
//		Properties properties = System.getProperties();
//		properties.setProperty("mail.smtp.host", HOST);
//		Session session = Session.getInstance(properties, null);	
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(FROM));
//			for (String email : para) {
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			}			
//			message.setSubject(titulo);
//			message.setText(corpo);
//			Transport transport = session.getTransport(PROTOCOL);
//			transport.connect();
//			if(!config.isDebug()){
//				transport.sendMessage(message, message.getAllRecipients());
//			}else{
//				logger.warn("Enviaria e-mail: "+para);
//				logger.warn(corpo);
//			}
//			transport.close();
//			logger.debug("Enviando finalizado");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
	}
	
	
	private void enviarEmailMembrosInteressados(Fase fase, OPERACAO operacao){
//		List<String> emails = new ArrayList<String>();
//		List<FaseMembro> membros =  faseMemebroDAO.membrosDaFase(fase);
//		for (FaseMembro f : membros) {
//			emails.add(f.getUser().getEmail());
//		}
//		
//		List<FaseInteressado> interessados =  faseInteressadoDAO.interessados(fase);
//		for (FaseInteressado f : interessados) {
//			emails.add(f.getUser().getEmail());
//		}
//		
//		if (!emails.isEmpty()) {
//			enviarEmail(
//					emails,
//					getAssunto("Ocorreram mudanças em uma "+ fase.getFase().name()),
//					gerarTextoMembrosInteressados(fase));
//		}
		
	}

	public void faseCriar(@Observes @CRIAR FaseEvent e){
        logger.debug("FaseEvent CRIAR: " + e.getFase().getFase());
        enviarEmailMembrosInteressados(e.getFase(),OPERACAO.CRIAR);
    }
	
	public void faseSalva(@Observes @ATUALIZAR FaseEvent e){
        logger.debug("FaseEvent ATUALIZAR: " + e.getFase().getFase());
        enviarEmailMembrosInteressados(e.getFase(),OPERACAO.ATUALIZAR);
    }	

	public void faseFinalizada(@Observes @FINALIZAR FaseEvent e){
		logger.debug("FinalizarFaseEvent: " + e.getFase().getFase());
        OPERACAO operacao = (e.getFase().getSituacao().equals(Situacao.APROVADO))?OPERACAO.APROVAR:OPERACAO.REPROVAR;
        enviarEmailMembrosInteressados(e.getFase(),operacao);
    }

}
