package br.gov.serpro.catalogo.bussiness;

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
import br.gov.serpro.catalogo.event.FaseEvent;
import br.gov.serpro.catalogo.event.FaseEvent.ADD_INTERESSADO;
import br.gov.serpro.catalogo.event.FaseEvent.ADD_MEMBRO;

@BusinessController
public class EmailBC{

	@Inject
	private Logger logger;
	
	private static final String FROM = "naoresponda@catalogotecnologia.serpro";
	private static final String PROTOCOL = "smtp";
	private static final String HOST = "10.31.181.156";
	
	private String TEMPLATE_ASSUNTO = "Catalogo de tecnologia: {operacao}";
	private String TEMPLATE_USUARIO_MEMBRO = "<p><b>{nome}</b>, <br> você foi adicionado a equipe de {fase}</p>"+ 
	"<p><a target='_blank' href='http://"+HOST+":8080/catalogo-tecnologia/#{fase}/edit/{id}'>clique aqui para visualizar.</a></p>";
	private String TEMPLATE_USUARIO_INTERESSADO = "<p><b>{nome}</b>, <br> você foi adicionado aos interessados na {fase}</p>"+ 
			"<p><a target='_blank' href='http://"+HOST+":8080/catalogo-tecnologia/#{fase}/edit/{id}'>clique aqui para visualizar.</a></p>";
			
	
	public void usuarioAdicionadoNaEquipe(@Observes @ADD_MEMBRO FaseEvent e){
        logger.debug("FaseEvent @ADD_MEMBRO: " + e.getFase().getFase());		
		if (e.getUser().getEmail() != null && !e.getUser().getEmail().isEmpty()) {			
			enviarEmail(e.getUser().getEmail(),
					TEMPLATE_ASSUNTO.replace("{operacao}", "Adicionado a "+e.getFase().getFase().name()),
					TEMPLATE_USUARIO_MEMBRO.replace("{nome}", e.getUser().getName())
					.replace("{fase}", e.getFase().getFase().name().toLowerCase())
					.replace("{id}",e.getFase().getId().toString()));	
		} else {
			throw new ValidationException().addViolation(null, "Usuário ("
					+ e.getUser().getCPF() + ") não possui e-mail");
		}		
	}
	
	public void interessadoAdicionado(@Observes @ADD_INTERESSADO FaseEvent e){
        logger.debug("FaseEvent @ADD_MEMBRO: " + e.getFase().getFase());		
		if (e.getUser().getEmail() != null && !e.getUser().getEmail().isEmpty()) {			
			enviarEmail(e.getUser().getEmail(),
					TEMPLATE_ASSUNTO.replace("{operacao}", "Adicionado a "+e.getFase().getFase().name()),
					TEMPLATE_USUARIO_INTERESSADO.replace("{nome}", e.getUser().getName())
					.replace("{fase}", e.getFase().getFase().name().toLowerCase())
					.replace("{id}",e.getFase().getId().toString()));	
		} else {
			throw new ValidationException().addViolation(null, "Usuário ("
					+ e.getUser().getCPF() + ") não possui e-mail");
		}		
	}
	
	private void enviarEmail(String para, String titulo, String corpo){		
		logger.warn("Enviando e-mail: "+para);
		logger.debug(corpo);
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", HOST);
		Session session = Session.getInstance(properties, null);	
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
			message.setSubject(titulo);
			message.setText(corpo);
			Transport transport = session.getTransport(PROTOCOL);
			transport.connect();
			//transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			logger.debug("Enviando finalizado");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	

}
