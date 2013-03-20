package RESTCharityClient;

import charityHibernateEntities.Form;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import staticResources.Configuration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class FormClient {
	private static String RestServiceURLPath;
	static {
		RestServiceURLPath = Configuration.getSiteUrl()+"RESTCharity/formService";
	}
	//http://localhost:8080/CharityWare_Lite/RESTCharity/formService/form/hibernate.cfg.xml/id/1
	public static List<Form> getForms(String DBConfig){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/forms/").path(DBConfig).
		accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<List<Form>>(){});
	}
	
	public static Form getForm(String DBConfig, Integer id){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/form/").path(DBConfig).path("/id/"+id).
		accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Form>(){});
	}
	
	

}
