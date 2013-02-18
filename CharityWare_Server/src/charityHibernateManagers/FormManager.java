package charityHibernateManagers;


import charityHibernateEntities.FieldSelection;
import charityHibernateEntities.FieldType;
import charityHibernateEntities.Form;
import charityHibernateEntities.FormFields;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import sharedHibernateResources.ConnectionManager;

public class FormManager {
	private ConnectionManager conn;
	private String DBConfname;
	public FormManager(String DBConfname){
		//this.DBConfname = DBConfname;
		this.DBConfname = DBConfname;
		conn = new ConnectionManager(DBConfname);
		//conn.setDBConfname(DBConfname);
	}
	
	public List<Form> retrieve(){
		List<Form> forms = (List<Form>) conn.getTable("Form");
		return forms;
	}
	
	public List<Form> getForms(){
		List<Form> forms = (List<Form>) conn.getTable("Form where isActive = 1");
		return forms;
	} 
	
	public Form getForm(Integer id){
		Form form = (Form)conn.get(Form.class,id);
		return form;
	}
	
	public boolean deleteForm(String formId){
		
		Form  killme= (Form)conn.searchCriteria(Form.class,new Integer(formId));
		/*Form killMe = (Form ) session.createCriteria(Form.class)
                .add(Restrictions.idEq( new Integer(formId))).uniqueResult();*/
		if(killme!=null){
			Serializable serial = conn.transaction("delete", killme);
			if(serial!=null)
				return true;
		}
		return false;	
		//session.delete(killMe);
	}
	public void insertForm(HttpServletRequest req)
	{
		int argc = Integer.parseInt(req.getParameter("argc"));
		Form f = new Form();
		f.setFormName(req.getParameter("formname"));
		f.setIsActive(true);
	    f.setUrl("http://whatever");
	    long secondsAtInsert = Calendar.getInstance().getTimeInMillis();
	    f.setDateCreated(new java.sql.Date(secondsAtInsert));
	    FormTypeManager formTypeManager = new FormTypeManager(this.DBConfname);
	    f.setFormType(formTypeManager.getFormType(1));
	    if(conn.transaction("save",f)!=null){
		    Pattern d = Pattern.compile("(\\d+)(.*)");
		    for(int i = 0; i < argc; i++){
		    	FormFields ff = new FormFields();
		    	ff.setForm(f);
		    	String typeInfo = req.getParameter("type_"+i);
		    	Matcher m = d.matcher(typeInfo);
		    	if(m.matches()){
		    		FieldTypeManager fieldTypeManager = new FieldTypeManager(this.DBConfname);
		    		FieldType theType = fieldTypeManager.getFieldType(new Integer(m.group(1)));
		    		ff.setField_type(theType);		
		    	}
		    	ff.setDate_created(new java.sql.Date(secondsAtInsert));
	   		 	//ff.setTimestamp(new java.sql.Timestamp(secondsAtInsert));
	   		 	ff.setField_label(req.getParameter("name_"+i));
	   		 	ff.setIsActive(true);
	   		 	ff.setIsRequired(req.getParameter("isReq_"+i) != null);
	   		 	if(conn.transaction("save",ff)!=null)
	   		 	{ 	
	   		 		if(m.group(2) != null && !m.group(2).isEmpty()) //there's more than just the id, there are also the values for the selection	   		 	
					{
						String json = m.group(2);
						ObjectMapper mapper = new ObjectMapper();
						try{
							String[] selections = mapper.readValue(json, String[].class);
							for(int j = 0; j < selections.length; j++)
							{
								FieldSelection selection = new FieldSelection();
								selection.setField_selection_value(selections[i]);
								selection.setFormField(ff);
								//selection.setTimestamp(new java.sql.Timestamp(secondsAtInsert));
								conn.transaction("save",selection);
							}
						}catch(JsonParseException jpe){
							jpe.printStackTrace();
						}catch(JsonMappingException jme){
							jme.printStackTrace();
						}
						catch(IOException ioe){
							ioe.printStackTrace();
						}				
					}
	   		 	}
		    }
	    }	   
	}
}
