package RESTCharityServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import charityHibernateEntities.Form;
import charityHibernateManagers.FormManager;

@Path("/formService")
public class FormService {

	@GET
    @Path("/forms/{DBConfigPath}/")
    @Produces("application/json")
    public List<Form> getForms(@PathParam("DBConfigPath")String DBConfigPath){
    	FormManager formManager = new FormManager(DBConfigPath);
    	return new ArrayList<Form>(formManager.getForms());
	  }
	
}
