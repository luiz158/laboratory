<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@include file="/html/evento/init.jsp" %>
 
<%
	 long eventoId = ParamUtil.getLong(request, "eventoId");	
	 String email = ParamUtil.getString(request, "email");	
	 
	 CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	 
	 String redirect = ParamUtil.getString(request, "redirect");
	 
	 EventoParticipante eventoParticipante = EventoParticipanteLocalServiceUtil.getEventoParticipante(eventoId, email);
%>
 
<portlet:actionURL name="inscreverParticipanteEvento"
				   var="inscreverParticipanteEventoURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	<portlet:param name="email" value="<%= email %>"/>
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:actionURL>

<portlet:actionURL name="removerParticipanteEvento"
				   var="removerParticipanteEventoURL" windowState="normal">
				   
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:actionURL>
 
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />
 
<%
 	if(eventoParticipante != null){ 
%>
	
<aui:form action="<%= removerParticipanteEventoURL %>" method="post" name="form">
 		
 		<h3>Para remover sua inscrição clique no botão abaixo.</h3>
		
		<aui:input name="eventoParticipanteId" type="hidden" value="<%= eventoParticipante.getEventoParticipanteId() %>"/>
		
	 	<aui:button-row>
	 		<aui:button type="submit" value="Remover Inscrição"/>
	 	</aui:button-row>
	 	
</aui:form>
		 	
<% 
	}
	else{
%>

<aui:form action="<%= inscreverParticipanteEventoURL %>" method="post" name="form">
	
 		<h3>Para confirmar sua inscrição clique no botão abaixo.</h3>
		
	 	<aui:button-row>
	 		<aui:button type="submit" value="Confirmar Inscrição"/>
	 	</aui:button-row>

</aui:form> 		
<%
 	}
%>
