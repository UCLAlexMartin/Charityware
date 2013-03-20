package RESTCharityServices;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import java.lang.reflect.Type;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
	
	@Path("/{DBConfigPath}/forms/insertForm")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("text/plain")
	 public String addForm(@PathParam("DBConfigPath")String DBConfigPath,String data)  {      
	  try{  
	   data =data.split("=")[1];
	   data=URLDecoder.decode(data);
	   Gson gson = new GsonBuilder().setDateFormat("yyyy-dd-MM").create();
	   Type formtype = new TypeToken<Form>() {}.getType();
	   Form form = gson.fromJson(data, formtype);
	   FormManager formManager = new FormManager(DBConfigPath);
	   return formManager.addForm(form).toString();
	     }
	     catch(Exception e){
	      return "0";
	     }   
	 }
	
}
