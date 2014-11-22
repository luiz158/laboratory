<%@page import="br.gov.demoiselle.portal.evento.model.impl.EventoConfiguracaoImpl"%>
<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@page import="br.gov.demoiselle.portal.evento.service.EventoConfiguracaoLocalServiceUtil"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoConfiguracao"%>
<%@include file="/html/evento/init.jsp" %>

<%
	long eventoId = ParamUtil.getLong(request, "eventoId");
 	EventoConfiguracao eventoConfiguracao = EventoConfiguracaoLocalServiceUtil.getEventoConfiguracaoPeloEvento(eventoId);
 	 
 	 if(eventoConfiguracao == null){
 		eventoConfiguracao = new EventoConfiguracaoImpl();
 	 }
 	 
	 CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	 String redirect = ParamUtil.getString(request, "redirect");
	 
 %>
 
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />

 <portlet:actionURL name="salvarEventoConfiguracao"
				    var="salvarEventoConfiguracaoURL" windowState="normal">

	<portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	<portlet:param name="redirect" value="<%= redirect %>"/>

</portlet:actionURL>

<aui:form action="<%= salvarEventoConfiguracaoURL %>" method="post" name="form">

	<table>
		<tr>
			<td>
				<label for="abertoAoPublico" class="aui-field-label">Evento aberto ao Público ?</label>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<input name="abertoAoPublico" type="checkbox" <%= eventoConfiguracao.isAbertoAoPublico() ? "checked" : "" %>/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="cidadeDoEvento" class="aui-field-label">Cidade do Evento</label>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<aui:input name="cidadeDoEvento" type="text" label="" value="<%= eventoConfiguracao.getCidadeDoEvento() %>" size="50"/>
			</td>
		</tr>
	</table>
	
	<aui:button-row>
 		<aui:button type="submit" value="Salvar"/>
 	</aui:button-row>
 	
 </aui:form>