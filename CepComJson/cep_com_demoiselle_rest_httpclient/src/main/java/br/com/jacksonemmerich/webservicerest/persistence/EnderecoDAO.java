package br.com.jacksonemmerich.webservicerest.persistence;

import java.io.IOException;

import javax.persistence.TypedQuery;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.map.ObjectMapper;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.com.jacksonemmerich.webservicerest.domain.Endereco;

@PersistenceController
public class EnderecoDAO extends JPACrud<Endereco, Long> {

	private static final long serialVersionUID = 1L;
	Endereco endereco;

	public Endereco getBuscaEndPorCEP(String cep) {
		HttpClient httpClient = new HttpClient();
		
		
		HttpMethod method = new GetMethod(
				"http://correiosapi.apphb.com/cep/" + cep);
		

		try {
			httpClient.executeMethod(method);
		} catch (HttpException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String responseBody;
		
		try {
			if(method.getStatusLine().getStatusCode()==HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED){ 
				return endereco = getBuscaEndPorCepComProxy(cep);
			}
			
			responseBody = method.getResponseBodyAsString();
			
			//2. Convert JSON to Java object
			ObjectMapper mapper = new ObjectMapper();
			endereco = mapper.readValue(responseBody, Endereco.class);

	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return endereco;
	}
	
	private Endereco getBuscaEndPorCepComProxy(String cep) {
		
	
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		//httpClient.getState().setProxyCredentials(new AuthScope("PROXY_ADDRESS", 8080, "PROXY_ADDRESS"), new NTCredentials("LOGIN", "PASS","LOCAL_MACHINE_IP","PROXY_ADDRESS"));
		
		//tem que configurar as credenciais do proxy, no meu caso o proxy é autenticado, logo tem que fazer a configuração da linha abaixo;
		httpClient.getState().setProxyCredentials(new AuthScope("10.101.1.250", 3128, "10.101.1.250"), new NTCredentials("261537", "260603","10.200.3.47","10.101.1.250"));
		httpClient.getHostConfiguration().setProxy("10.101.1.250", 3128);
		HttpMethod method = new GetMethod(
				"http://correiosapi.apphb.com/cep/" + cep);
		

		try {
			httpClient.executeMethod(method);
		} catch (HttpException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String responseBody;
		
		try {
			responseBody = method.getResponseBodyAsString();
			
			//2. Convert JSON to Java object
			ObjectMapper mapper = new ObjectMapper();
			endereco = mapper.readValue(responseBody, Endereco.class);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return endereco;
	}

	/**
	 * @param id 
	 * <p> o método recebe um parâmetro id do colaborador ao qual ele consulta o endereço</p>
	 *  método que retorna o endereço do colaborador
	 */ 
	public  Endereco getBuscaEndColaborador(Long id) {
		String consulta = "select e from Endereco e, Colaborador c where c.id = :id AND e.id = c.endereco";
		TypedQuery<Endereco> query = getEntityManager().createQuery(consulta, Endereco.class);
		query.setParameter("id", id);
		Endereco enderecoColaborador = query.getSingleResult();
		return enderecoColaborador;
	}
	
	//método para retornar um colaborador com o endereço
	
}
