package com.webviewprototype.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import javax.ws.rs.core.MediaType;

import com.webviewprototype.facade.RestServiceFacade;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import env.Entities.Form;

public class RestServiceFacadeImpl implements RestServiceFacade {

	public RestServiceFacadeImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/*public ArrayList<Form> getData(String username){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource("http://localhost:8080/CharityWare/REST/userService").path("json/user/forms/"+username).
				accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<ArrayList<Form>>(){});
	}*/

	public  Map<Integer,Map<Integer,List<String>>> getFormEntities(String username){
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse clientresponse = client.resource("http://localhost:8080/CharityWare/REST/userService/").path("json/users/formEntities/").
				path(username).accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);
		return clientresponse.getEntity(new GenericType<Map<Integer,Map<Integer,List<String>>>>(){});
	}

}
