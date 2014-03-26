package br.gov.serpro.catalogo.bussiness;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.mail.Mail;
import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.User;

@BusinessController
public class EmailBC {

	@Inject
	private Mail mail;
	
	private String TEMPLATE_ASSUNTO = "Catalogo de tecnologia: {{operacao}}";
	private String TEMPLATE_USUARIO_MEMBRO = "<b>{nome}</b>, você foi adicionado a equipe de <a href='http://10.31.181.156:8080/catalogo-tecnologia/#{{fase}}/edit/{{id}}'>{{fase}}</a>";

	public void enviarEmailUsuarioAdicionadoEquipe(User usuario, Fase fase) {
		if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
			Beans.getReference(Logger.class).debug(usuario.getEmail());
			mail.to(usuario.getEmail())
					.from("catalogo-tecnologia@serpro.gov.br")
					.body()
					.html(TEMPLATE_USUARIO_MEMBRO.replace("{nome}", usuario.getName())
							.replace("{fase}", fase.getFase().name().toLowerCase())
							.replace("{id}",fase.getId().toString())
							)
					.subject(TEMPLATE_ASSUNTO.replace("{operacao}", "Adicionado a uma fase"))
					.send();			
		} else {
			throw new ValidationException().addViolation(null, "Usuário ("
					+ usuario.getCPF() + ") não possui e-mail");
		}

	}

}
