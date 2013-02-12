package RESTSystemServices;

import systemHibernateEntities.Charity;
import systemHibernateManagers.CharityManager;

import java.util.ArrayList;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/charityService")
public class CharityService {
	
	
	 @GET
	 @Path("/charityApprovals")
	 @Produces("application/json")
	 public ArrayList<Charity> getCharitiesForApproval(){
		 	System.out.println("Get all Charity Requests");
		 	CharityManager charityManager = new CharityManager();
			
	    	ArrayList<Charity> charities = charityManager.getCharitiesRequests();
	     	System.out.println("Charity Requests Array Populated");
	    	return charities;
	 }

}
