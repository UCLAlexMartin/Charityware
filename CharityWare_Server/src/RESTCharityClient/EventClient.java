package RESTCharityClient;


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

public class EventClient {
	private static String RestServiceURLPath;
	static {
		RestServiceURLPath = Configuration.getSiteUrl()+"RESTCharity/eventService";
	}
	public static Map<Integer,List<String>> getEvents(String DBConfig){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/charityConfig/").path(DBConfig).path("/events/").
				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
	}
	
}
