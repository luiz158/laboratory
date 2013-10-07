<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil"%>
<%@page import="br.gov.demoiselle.portal.evento.model.EventoParticipante"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.liferay.portlet.calendar.service.CalEventLocalServiceUtil"%>
<%@page import="com.liferay.portlet.calendar.model.CalEvent"%>
<%@include file="/html/evento/init.jsp" %>

 <%
	 long eventoId = ParamUtil.getLong(request, "eventoId");	
	 
	 CalEvent evento = CalEventLocalServiceUtil.getCalEvent(eventoId);
	 
	 String redirect = ParamUtil.getString(request, "redirect");
	 
	 PortletURL itURL = renderResponse.createRenderURL();
	 itURL.setParameter("jspPage", "/html/evento/admin/envio_comunicados.jsp");
	 itURL.setParameter("eventoId", String.valueOf(eventoId));
	 itURL.setParameter("redirect", redirect);
	 
 %>
 
<portlet:actionURL name="enviarComunicadoEvento"
				   var="enviarComunicadoEventoURL" windowState="normal" >

	 <portlet:param name="jspPage" value="/html/evento/admin/envio_comunicados.jsp" />
	 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	 <portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>

</portlet:actionURL>

<liferay-ui:error key="erro-endereco" message="Erro ao enviar email para {0}" />
<liferay-ui:error key="erro-envio-email" message="Erro ao enviar, {0}" />

<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />

<!-- Listagem dos Convidados -->
<aui:form action="<%= enviarComunicadoEventoURL %>" enctype="multipart/form-data" method="post" name="form">

	<!-- Área de texto -->
	<aui:input name="assunto" label="Assunto : " value="<%= new String(\"Comunicado para \" + evento.getTitle()) %>" size="50"/>
	<aui:input type="text" name="emailFrom" label="Email de envio : " size="50"/>
	
	<aui:input type="file" name="fileName" size="75" label="Anexo : "/>
	
	<label class="aui-field-label">
		Convite para Evento?
	</label>
	<input type="checkbox" id="isConvite" 
						   class="selectUserCheckBox" name="isConvite"
						   value="true" checked/>
						   
	<br/>						   
	<liferay-ui:input-editor name="textoComunicado"/>
	*Variáveis
	NOME_CONVIDADO = Nome do convidado	
	<br/>
	<br/>
	<br/>
	
	<liferay-ui:search-container emptyResultsMessage="Nenhum participante encontrado" orderByCol="name" >
								 
		<liferay-ui:search-container-results 
					results="<%= EventoParticipanteLocalServiceUtil.getListaDeParticipantes(eventoId) %>"
					total="<%= EventoParticipanteLocalServiceUtil.getListaDeParticipantes(eventoId).size()%>"/>
        
			<liferay-ui:search-container-row
				className="br.gov.demoiselle.portal.evento.model.EventoParticipante"
				keyProperty="eventoParticipanteId"
				modelVar="eventoParticipante" escapedModel="<%= true %>" >
			
				<liferay-ui:search-container-column-text name="Enviar Comunicado">
				
					<input type="checkbox" id="selectUserEnvioComunicadoCheckBox" 
						   class="selectUserCheckBox" name="selectUserEnvioComunicadoCheckBox"
						   value="<%= eventoParticipante.getEmail() %>" checked/>
							   
				</liferay-ui:search-container-column-text>
				
				<liferay-ui:search-container-column-text name="name" property="nome" />
				<liferay-ui:search-container-column-text name="email" property="email" />
				<liferay-ui:search-container-column-text name="organization" property="instituicaoEmpresa" />
				
			</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator paginate="false" />
	
	</liferay-ui:search-container>
	
	<aui:button-row>
 		<aui:button type="submit" value="Enviar Comunicado"/>
 	</aui:button-row>

</aui:form>