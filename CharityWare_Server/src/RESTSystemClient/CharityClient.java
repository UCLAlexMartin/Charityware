package RESTSystemClient;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import staticResources.Configuration;
import systemHibernateEntities.Charity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;



public class CharityClient {
	private static String RestServiceURLPath;
	static {
		RestServiceURLPath = Configuration.getSiteUrl()+"RESTSystem/charityService";
	}
	public static Map<Integer,List<String>> getCharitiesRequests(){
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		
		ClientResponse clientresponse = client.resource(RestServiceURLPath)
		.path("/charityApprovals").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,List<String>>>(){});	
	}
	
	public static void addCharity(Charity charity){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		//ClientResponse clientresponse = 
		client.resource(RestServiceURLPath)
				.path("/addCharity/").post(Charity.class,charity);
				
				//new GenericType<Charity>(charity){}
	}
}
