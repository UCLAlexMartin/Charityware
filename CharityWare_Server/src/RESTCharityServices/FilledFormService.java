package RESTCharityServices;
import java.util.List;

import charityHibernateEntities.FilledForm;
import charityHibernateManagers.FilledFormManager;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

//import org.codehaus.jackson.map.type;


@Path("/filledFormService")
public class FilledFormService {
	
	@GET
    @Path("/{DBConfigPath}/filledforms/records")
    @Produces("application/json")
    public GenericEntity<String> getRecordsDataJSON(@PathParam("DBConfigPath")String DBConfigPath){
		FilledFormManager filledFormManager = new FilledFormManager(DBConfigPath);
    	GenericEntity<String> entity = new GenericEntity<String>(filledFormManager.getRecordsData()){};
    	return entity;       
    }
	
	@Path("/{DBConfigPath}/filledforms/insertFilledForms")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("text/plain")
	public String addFilledForms(@PathParam("DBConfigPath")String DBConfigPath,String data)  {		 		 
		try{		
			List<FilledForm> filledforms = (List<FilledForm>)new ObjectMapper().readValue(data, List.class);
			FilledFormManager filledFormManager = new FilledFormManager(DBConfigPath);
			return filledFormManager.addFilledForms(filledforms).toString();
		}
		catch(Exception e){
			return "0";
			
		}		 
	 }
	/*
	@POST
	@Path("/{DBConfigPath}/filledforms/insertFilledForms")
	@Consumes("application/json")
	public Response insertFilledForms(JAXBElement<List<FilledForm>> todo){
		
	}*/
}
