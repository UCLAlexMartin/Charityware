package RESTCharityClient;

import java.util.ArrayList;


import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import staticResources.Configuration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class FieldSelectionClient {
	private static String RestServiceURLPath;
	static {
		RestServiceURLPath = Configuration.getSiteUrl()+"RESTCharity/fieldSelectionService";
	}
	
	public static ArrayList<String> getEvents(Integer field_id){
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/json/selectionValues/"+field_id).
				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<ArrayList<String>>(){});
	}

}
