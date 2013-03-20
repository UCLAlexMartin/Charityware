package RESTCharityServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;

import charityHibernateEntities.Form;
import charityHibernateManagers.FormManager;

@Path("/formService")
public class FormService {

	@GET
    @Path("/forms/{DBConfigPath}/")
    @Produces("application/json")
    public GenericEntity<List<Form>> getForms(@PathParam("DBConfigPath")String DBConfigPath){
    	FormManager formManager = new FormManager(DBConfigPath);
    	return new GenericEntity<List<Form>>(formManager.getForms()){};
	  }
	
	@GET
    @Path("/form/{DBConfigPath}/id/{formID}")
    @Produces("application/json")
    public Form getForm(@PathParam("DBConfigPath")String DBConfigPath,@PathParam("formID")String formID){
    	FormManager formManager = new FormManager(DBConfigPath);
    	return formManager.getForm(Integer.parseInt(formID));
	  }
	
}
