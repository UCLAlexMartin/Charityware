package RESTCharityServices;

import charityHibernateManagers.FilledFormManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;

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

}
