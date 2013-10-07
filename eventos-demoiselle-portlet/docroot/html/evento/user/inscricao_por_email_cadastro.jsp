<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@include file="/html/evento/init.jsp" %>
 
<%
	long eventoId = ParamUtil.getLong(request, "eventoId");	
	String email = ParamUtil.getString(request, "email");	
	String redirect = ParamUtil.getString(request, "redirect");
	Boolean isParticipanteCadastrado = Boolean.FALSE;
	
	String nome = "";
	String instituicaoEmpresa = "";
	
	CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	EventoParticipante eventoParticipante = null;
	
	if(!email.isEmpty() && Validator.isEmailAddress(email)){
	
		eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
		
		if(eventoParticipante != null){
			nome = eventoParticipante.getNome();
			instituicaoEmpresa = eventoParticipante.getInstituicaoEmpresa();
			
			if(eventoParticipante.isInscricaoConfirmada()){
				isParticipanteCadastrado = Boolean.TRUE;
			}
		}
		
	}
	 
 %>
 
<portlet:actionURL name="inscreverParticipanteEventoPorEmail"
				   var="inscreverParticipanteEventoPorEmailURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	<portlet:param name="email" value="<%= email %>"/>
	<portlet:param name="jspPage" value="/html/evento/user/inscricao_por_email.jsp"/>
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:actionURL>
 
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />
 
<aui:form action="<%= inscreverParticipanteEventoPorEmailURL %>" method="post" name="form">
 
 	<% if(!isParticipanteCadastrado) {%>
	 	<aui:layout>
	 		<aui:column columnWidth="30">
	 			<aui:input name="emailParticipante" type="text" label="E-mail : " value="<%= email %>" disabled="true" size="50"/>
			 	<aui:input name="nome" type="text" label="Nome Completo : " value="<%= nome %>" showRequiredLabel="true" size="50" disabled="<%= eventoParticipante == null ? false : true %>"/>
			 	<aui:input name="instituicaoEmpresa" type="text" label="Instituição / Empresa : " value="<%= instituicaoEmpresa %>" showRequiredLabel="true" size="50" disabled="<%= eventoParticipante == null ? false : true %>"/>
			 </aui:column>
			 
	 	</aui:layout>
		
		<aui:button-row>
	 		<aui:button type="submit" value="Confirmar Inscrição"/>
	 	</aui:button-row>
	 	<%} else { %>
 	
 	<h2>Sua inscrição já esta confirmada nesse evento.</h2>
 	
 	<%} %>
 	
 			
</aui:form>