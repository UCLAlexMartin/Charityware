package RESTCharityClient;

import java.util.List;
import java.util.Map;

import charityHibernateEntities.User;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import staticResources.Configuration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserClient {
	private static String RestServiceURLPath;
	static {
		RestServiceURLPath = Configuration.getSiteUrl()+"RESTCharity/userService";
	}
	
	public static User get(String parameter){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client Myclient = Client.create(clientConfig);
		ClientResponse clientresponse = Myclient.resource(RestServiceURLPath)
				.path("/userName").path(parameter)
				.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(User.class);
	}
	
//	public static Map<Integer,List<String>> getForms(){
//		ClientConfig clientConfig = new DefaultClientConfig();
//		clientConfig.getClasses().add(JacksonJsonProvider.class);
//		Client client = Client.create(clientConfig);
//		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/json/users/forms/").
//				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
//				get(ClientResponse.class);
//		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
//	}
	
	public static Map<Integer,List<String>> getForms(String DBConfig){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/charityConfig/").path(DBConfig).path("/users/forms/").
		accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
		get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});
	}
	
	public static Map<Integer,Map<Integer,List<String>>> getFormEntities(String username){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath).path("/json/users/formEntities/").
				path(username).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,Map<Integer,List<String>>>>(){});
	}
	
	public static String getCharityActiveUsers(String DBConfig){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource(RestServiceURLPath)
		.path("/stats/ActiveUsers").path(DBConfig).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
		.get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<String>(){});
		
	}

}
