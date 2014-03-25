package br.gov.serpro.catalogo.bussiness;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.resteasy.util.ValidationException;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.User;

@BusinessController
public class EmailBC {

	//@Inject 
	//private Mail mail;

	public void enviarEmailUsuarioAdicionadoEquipe(User usuario, Fase fase) {
		if(usuario.getEmail()!=null && !usuario.getEmail().isEmpty()){	
			Beans.getReference(Logger.class).debug(usuario.getEmail());
//			mail
//			.to(usuario.getEmail())
//			.from("catalogo-tecnologia@serpro.gov.br")
//			.body().html("<strong>"+usuario.getName()+"</strong>, você foi adicionado a equipe de <a href='http://localhost:8080/catalogo-tecnologia/#"+fase.getFase().name().toLowerCase()+"/edit/"+fase.getId()+"'>"+fase.getFase().name()+" </a>" )
//			.subject("Assunto")
//			.send();
		}else{
			throw new ValidationException().addViolation(null, "Usuário ("+usuario.getCPF()+") não possui e-mail");
		}
		
		
	}
}
