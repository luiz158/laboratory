<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@include file="/html/evento/init.jsp" %>

 <%
 
 	EventoParticipante eventoParticipante = null;
 	
	long eventoId = ParamUtil.getLong(request, "eventoId"); 
	
 	CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
 	String redirect = ParamUtil.getString(request, "redirect");
 
	long eventoParticipanteId = ParamUtil.getLong(request, "eventoParticipanteId");
	
	if(eventoParticipanteId > 0){
		eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoParticipanteId);	
	}
	 
 %>
 
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />

<portlet:actionURL name="adicionarParticipanteAoEvento"
				    var="adicionarParticipanteAoEventoURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:actionURL>

<portlet:actionURL name="atualizarParticipanteEvento"
				    var="atualizarParticipanteEventoURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	<portlet:param name="eventoParticipanteId" value="<%= String.valueOf(eventoParticipanteId) %>"/>
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:actionURL>

<aui:form action="<%= eventoParticipante == null ? adicionarParticipanteAoEventoURL : atualizarParticipanteEventoURL %>" method="post" name="form">

	<% if(eventoParticipante == null){ %>
	<aui:input name="email" type="text" label="E-mail : " value="" size="50"/>
	<aui:input name="nome" type="text" label="Nome Completo : " showRequiredLabel="true" size="50"/>
 	<aui:input name="instituicaoEmpresa" type="text" label="Instituição / Empresa : " showRequiredLabel="true" size="50"/>
	<% } else { %>
	<aui:input name="email" type="text" label="E-mail : " value="<%= eventoParticipante.getEmail() %>" size="50" disabled="true"/>
	<aui:input name="nome" type="text" label="Nome Completo : " value="<%= eventoParticipante.getNome() %>" showRequiredLabel="true" size="50"/>
 	<aui:input name="instituicaoEmpresa" type="text" label="Instituição / Empresa : " value="<%= eventoParticipante.getInstituicaoEmpresa() %>" showRequiredLabel="true" size="50"/>
	<% } %>
	
	<aui:button-row>
 		<aui:button type="submit" value="Salvar"/>
 	</aui:button-row>
 	
 </aui:form>