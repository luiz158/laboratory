<%@page import="br.gov.demoiselle.portal.evento.service.EventoParticipanteLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.Junction"%>
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
	itURL.setParameter("jspPage", "/html/evento/admin/edit.jsp");
	itURL.setParameter("eventoId", String.valueOf(eventoId));
	itURL.setParameter("redirect", redirect);
	 
	String keyword = ParamUtil.getString(request, "keyword");
	 
%>
 
<portlet:actionURL name="updateEventoParticipante"
				   var="updateEventoParticipanteURL" windowState="normal" >

	 <portlet:param name="jspPage" value="/html/evento/admin/edit.jsp" />
	 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	 <portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>

</portlet:actionURL>
				   
<portlet:renderURL var="importacaoParticipantesURL">
	 <portlet:param name="jspPage" value="/html/evento/admin/importacao.jsp" />
	 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	 <portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>
</portlet:renderURL>
 
<portlet:renderURL var="adicionarParticipanteURL">
	 <portlet:param name="jspPage" value="/html/evento/admin/participante.jsp" />
	 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	 <portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>
</portlet:renderURL>
 
<portlet:renderURL var="configuracaoEventoURL">
	 <portlet:param name="jspPage" value="/html/evento/admin/configuracao.jsp" />
	 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	 <portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>
</portlet:renderURL>

<portlet:renderURL var="enviarComunicadoURL">
	 <portlet:param name="jspPage" value="/html/evento/admin/envio_comunicados.jsp" />
	 <portlet:param name="eventoId" value="<%= String.valueOf(eventoId) %>"/>
	 <portlet:param name="redirect" value="<%= PortalUtil.getCurrentURL(renderRequest) %>"/>
</portlet:renderURL>

<liferay-ui:success key="success-upload" message="Upload realizado com sucesso." />
<liferay-ui:error key="error-upload-arquivo-vazio" message="Arquivo vazio, favor verificar." />
<liferay-ui:error key="error-arquivo-invalido" message="Ocorreu um erro na leitura do arquivo, verifique a formatação dos dados." />
<liferay-ui:error key="error-pessoa-ja-cadastrada" message="O email informado já esta cadastrado para esse evento." />
 
<liferay-ui:header backURL="<%= redirect %>" title="<%= evento.getTitle() %>" />

<!-- Pesquisar usuário -->
<aui:form action="<%= itURL %>" method="post" name="formPesquisa">
	
	<aui:input name="keyword" type="text" label="Palavra-chave :" inlineField="true" style="width: 350px;"/>
 	<aui:button type="submit" value="Pesquisar"/>

</aui:form>

<br/> 
<hr/>

<!-- Importação dos participantes -->
<aui:button type="button" value="Importar Participantes" href="<%= importacaoParticipantesURL %>"/>

<!-- Adicionar novo participante -->
<aui:button type="button" value="Adicionar Participante" href="<%= adicionarParticipanteURL %>"/>

<!-- Configurações do Evento-->
<aui:button type="button" value="Configurações do Evento" href="<%= configuracaoEventoURL %>"/>

<!-- Enviar Comunicado do Evento -->
<aui:button type="button" value="Enviar Comunicado" href="<%= enviarComunicadoURL %>"/>

<hr/>
 
 <!-- Listagem de Participantes -->
 <aui:form action="<%= updateEventoParticipanteURL %>" method="post" name="form">
 
 	<aui:button-row>
 		<aui:button type="submit"/>
 	</aui:button-row>
 	
	<liferay-ui:search-container delta="75" emptyResultsMessage="Nenhum participante encontrado"
								 iteratorURL="<%= itURL %>" orderByCol="name" >
								 
		<aui:input name="start" type="hidden" value="<%=  searchContainer.getStart() %>"/>
		<aui:input name="end" type="hidden" value="<%= searchContainer.getEnd() %>"/>
		
		<liferay-ui:search-container-results>
        
        <%    
                LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();
        
        		DynamicQuery query = DynamicQueryFactoryUtil.forClass(EventoParticipante.class).
        								add(PropertyFactoryUtil.forName("eventoId").eq(eventoId));
        		
        		if(!keyword.isEmpty()){
        			Junction juncao = RestrictionsFactoryUtil.disjunction();
        			juncao.add(PropertyFactoryUtil.forName("email").like("%" + keyword + "%"));
        			juncao.add(PropertyFactoryUtil.forName("nome").like("%" + keyword + "%"));
        			juncao.add(PropertyFactoryUtil.forName("instituicaoEmpresa").like("%" + keyword + "%"));
        			
        			query.add(juncao);
        		}
        		
                results = EventoParticipanteLocalServiceUtil.dynamicQuery(query, searchContainer.getStart(), searchContainer.getEnd());
               
                if(!keyword.isEmpty()){
                	total = results.size();
                }
                else{
                	total = EventoParticipanteLocalServiceUtil.getListaDeParticipantes(eventoId).size();
                }
                
                pageContext.setAttribute("results", results);
                pageContext.setAttribute("total", total);
        %>
          
   		</liferay-ui:search-container-results>
		
		<liferay-ui:search-container-row
			className="br.gov.demoiselle.portal.evento.model.EventoParticipante"
			keyProperty="eventoParticipanteId"
			modelVar="eventoParticipante" escapedModel="<%= true %>" >
		
			<liferay-ui:search-container-column-text name="Inscrição">
			
				<input type="checkbox" id="selectUserCheckBox" 
					   class="selectUserCheckBox" name="selectUserIncricaoCheckBox"
					   value="<%= eventoParticipante.getEmail() %>" 
					   <%= eventoParticipante.isInscricaoConfirmada() ? "checked" : "" %>/>
						   
			</liferay-ui:search-container-column-text>
			
			<liferay-ui:search-container-column-text name="Participação">
			
				<input type="checkbox" id="selectUserParcipacaoCheckBox" 
					   class="selectUserParcipacaoCheckBox" name="selectUserParcipacaoCheckBox"
					   value="<%= eventoParticipante.getEmail() %>" 
					   <%= eventoParticipante.isParticipacaoConfirmada() ? "checked" : "" %>/>
						   
			</liferay-ui:search-container-column-text>
			
			<liferay-ui:search-container-column-text name="name" property="nome" />
			<liferay-ui:search-container-column-text name="email" property="email" />
			<liferay-ui:search-container-column-text name="organization" property="instituicaoEmpresa" />
			<liferay-ui:search-container-column-jsp align="left" path="/html/evento/admin/participante_actions.jsp"/>
			
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator />
	
	</liferay-ui:search-container>
	
	<aui:button-row>
 		<aui:button type="submit"/>
 	</aui:button-row>

</aui:form>