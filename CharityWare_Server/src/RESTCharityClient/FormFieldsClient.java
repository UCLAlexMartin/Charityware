package RESTCharityClient;

import charityHibernateEntities.User;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import staticResources.Configuration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class FormFieldsClient {
	private static String RestServiceURLPath;
	static {
		RestServiceURLPath = Configuration.getSiteUrl()+"RESTCharity/formFieldsService";
	}
	public static Map<Integer,List<String>> getData(Integer form_id){
		DefaultClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/json/formFields/").path(form_id.toString()).
				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
	}
	
	public static User get(String parameter){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client Myclient = Client.create(clientConfig);
		ClientResponse clientresponse = Myclient.resource(RestServiceURLPath)
				.path("userName").path(parameter)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(User.class);

	}

}
