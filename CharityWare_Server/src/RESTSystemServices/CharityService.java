package RESTSystemServices;

import systemHibernateEntities.Charity;
import systemHibernateManagers.CharityManager;
import systemHibernateManagers.GenerateSchemaManager;
import systemHibernateManagers.UserManager;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.GenericType;

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
	 
	 
	 @GET
	 @Path("/charities")
	 @Produces("application/json")
	 public List<Charity> getCharities() {
		 CharityManager charityManager = new CharityManager();
		 List<Charity> charities = charityManager.getCharities();
		 return charities;
	 }
	 
	 
	 @GET
	 @Path("/charities/{charityid}")
	 @Produces("application/json")
	 public Charity getCharity(@PathParam("charityid") int charityID) {
		 CharityManager charityManager = new CharityManager();
		 Charity ch = charityManager.getCharity(charityID);
		 return ch;   
	 }
	 
	 @GET
	 @Path("/charity/user/{userid}")
	 @Produces("application/json")
	 public Charity getUserCharity(@PathParam("userid") int userID) {
		 System.out.println("Service");
		 CharityManager charityManager = new CharityManager();
		 Charity ch = charityManager.getUserCharity(userID);
		 System.out.println("Service return");
		 return ch;   
	 }
	 
	 /**
	   * PUT method for updating or creating an instance of CharityResource
	   * @param content representation for the resource
	   * @return an HTTP response with content of the updated or created resource.
	   */
	 @Path("/addCharity/{chName}/{RegNo}/{chEmail}/{chDescription}/{AdminUsername}/{AdminPassword}")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("text/plain")
	 public String postCharity(@PathParam("chName") String chName,@PathParam("RegNo") String RegNo,@PathParam("chEmail") String chEmail,@PathParam("chDescription") String chDescription,@PathParam("AdminUsername") String AdminUsername,@PathParam("AdminPassword") String AdminPassword) {		 		 
		 CharityManager charityManager = new CharityManager();
		 return charityManager.addCharity(chName,RegNo,chEmail,chDescription,AdminUsername,AdminPassword).toString();
	 }
	 
	 @Path("/addCharities")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("text/plain")
	 public String postCharities(List<Charity> charities) {		 
		 
		 CharityManager charityManager = new CharityManager();
		 
		 return charityManager.addCharities(charities).toString();
	 }

	 @POST
	 @Path("/generateSchema/{charityId}")
	 @Produces("application/json")
	 public Boolean postGenerateSchema(@PathParam("charityId") String charityId){		 
		 try{ 
			System.out.println("Get Charity ID"); 
		 	int charID = Integer.parseInt(charityId);		 	 
			Boolean result = GenerateSchemaManager.generateSchema(charID);
			System.out.println(result);
			return result;
			
		 }catch (Exception ex)
		 {
			 ex.printStackTrace();
			 return false;
		 }
	    	
	 }
	 
	 @POST
	 @Path("/rejectCharity/{charityId}")
	 @Produces("application/json")
	 //@Consumes("application/json")
	 public Boolean postRejectCharity(@PathParam("charityId") String charityId){		 
		 try{ 
			 //isVerified = 0
			 //isActive = 1
			 //connection_string = charity + ChairtyId
			System.out.println("Get Charity ID"); 
			int charID = Integer.parseInt(charityId);
			CharityManager charityMgr = new CharityManager();
			charityMgr.rejectCharity(charID);
			return true;
		 }catch (Exception ex)
		 {
			 ex.printStackTrace();
			 return false;
		 }		 
	 }
	
	 @POST
	 @Path("/deleteCharityAccount/{charityId}")
	 @Produces("application/json")
	 //@Consumes("application/json")
	 public Boolean pOSTDeleteCharityAccount(@PathParam("charityId") String charityId){		 
		 try{ 
			 //isActive = 0
			System.out.println("Get Charity ID"); 
			int charID = Integer.parseInt(charityId);
			CharityManager charityMgr = new CharityManager();
			charityMgr.deleteCharityAccount(charID);
			return true;
		 }catch (Exception ex)
		 {
			 ex.printStackTrace();
			 return false;
		 }
	    	
	 }
	 
		@GET
		@Path("/stats/VerifiedCharities")
		@Produces("application/json")
		public String getSystemVerifiedCharities(){
			System.out.println("Get Verified Charities Statistics");
			CharityManager charityManager = new CharityManager();
			String stats = charityManager.getSystemVerifiedCharities();
			System.out.println("Statistics Retrieved");
			if(stats.isEmpty())
			{
				return null;
			}
			else
			{
				return stats;
			}
		} 
		
		@GET
		@Path("/stats/CharityRegistrationActivity")
		@Produces("application/json")
		public String getCharityRegistrationActivity(){
			System.out.println("Get Charity Registration Activity Statistics");
			CharityManager charityManager = new CharityManager();
			String stats = charityManager.getCharityRegistrationActivity();
			System.out.println("Statistics Retrieved");
			if(stats.isEmpty())
			{
				return null;
			}
			else
			{
				return stats;
			}
		} 

}
