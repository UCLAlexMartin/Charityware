package RESTSystemClient;

import systemHibernateEntities.User;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import staticResources.Configuration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserClient {
	
	public static User get(String parameter){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client Myclient = Client.create(clientConfig);
		ClientResponse clientresponse = Myclient.resource(Configuration.getSiteUrl())
				.path("userService").path("userName").path(parameter)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(User.class);
	}
	
}
