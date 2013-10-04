<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@include file="/html/evento/init.jsp" %>
 
<%
	 long eventoId = ParamUtil.getLong(request, "eventoId");	
	 String email = ParamUtil.getString(request, "email");	
	 
	 CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	 
	 String redirect = ParamUtil.getString(request, "redirect");
	 
%>
 
<portlet:renderURL var="verificarEmailParticipanteEventoURL">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	<portlet:param name="jspPage" value="/html/evento/user/inscricao_por_email_cadastro.jsp"/>
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:renderURL>
				   
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />
 
<aui:form action="<%= verificarEmailParticipanteEventoURL %>" method="post" name="form">
 	
	<aui:input name="email" type="text" label="E-mail : " showRequiredLabel="true" size="50"/>
	
	<aui:button-row>
 		<aui:button type="submit" value="Avançar"/>
 	</aui:button-row>
 	
</aui:form>