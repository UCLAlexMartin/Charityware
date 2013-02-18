package RESTCharityServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import charityHibernateEntities.UserType;
import charityHibernateManagers.UserTypeManager;

@Path("/userTypeService")
public class UserTypeService {
	
	@GET
    @Path("/charityConfig/{DBConfigPath}/userTypes/")
    @Produces("application/json")
    public List<UserType> getUserTypes(@PathParam("DBConfigPath")String DBConfigPath){
    	UserTypeManager userTypeManager = new UserTypeManager(DBConfigPath);
    	return new ArrayList<UserType>(userTypeManager.getUserTypes());
	  }

}
