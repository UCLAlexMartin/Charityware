package charityHibernateManagers;

import charityHibernateEntities.FilledForm;
import charityHibernateEntities.FormFields;
import charityHibernateEntities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import sharedHibernateResources.ConnectionManager;

public class FilledFormManager {
	private ConnectionManager conn;
	
	public FilledFormManager(String DBConfname){
		//this.DBConfname = DBConfname;
		System.out.println("HELLLOOO!");
		conn = new ConnectionManager(DBConfname);
		//conn.setDBConfname(DBConfname);
	}
	public  String getRecordsData(){
		
		Map<String,HashSet<Integer>> results = new TreeMap<String,HashSet<Integer>>();
		List<FilledForm> filled_forms = (ArrayList<FilledForm>) conn.getTable("FilledForm");
		Iterator<FilledForm> iter = filled_forms.iterator();
		while (iter.hasNext()){
			FilledForm filled_form = iter.next();
			if(filled_form.getIsActive()){
				FormFields formfields = filled_form.getFormFields();
				if(formfields.getIsActive()){
					Integer temp_record_id = filled_form.getRecord_id();
					HashSet<Integer> records = results.get(filled_form.getUser_id().getUserName());
					if(records!=null){
						records.add(temp_record_id);
						results.put(filled_form.getUser_id().getUserName(),records );
					}						
					else{
						records = new HashSet<Integer>();
						records.add(temp_record_id);
						results.put(filled_form.getUser_id().getUserName(),records );
					}
				}
			}
		}
		StringBuilder finalresult = new StringBuilder();
		//Map<String,Integer> final_results = new TreeMap<String,Integer>();
		Iterator<Entry<String,HashSet<Integer>>> results_iter = results.entrySet().iterator();
		finalresult.append('[');
		while(results_iter.hasNext()){
			Entry<String,HashSet<Integer>> entry = results_iter.next();
			//final_results.put(entry.getKey(), entry.getValue().size());
			finalresult.append(String.format("[\"%s\",%d],", entry.getKey(), entry.getValue().size()));
		}
		finalresult.setCharAt(finalresult.length()-1, ']');
		return finalresult.toString();
	}
	
	public Integer addFilledForm(Integer user_ID, Integer field_ID, String value,String DBConfname){
		UserManager userManager = new UserManager(DBConfname);
		User user = userManager.getUser(user_ID);
		System.out.println("User Name:"+user.getUserName());
		FormFieldsManager formfieldsManager = new FormFieldsManager(DBConfname);
		FormFields form_field_id = formfieldsManager.getFormFields(field_ID);
		System.out.println("Form Field Label:"+form_field_id.getField_label());
		FilledForm filledForm = new FilledForm( user,  form_field_id);
		filledForm.setValue(value);
		return (Integer) conn.transaction("save",filledForm);
	}
	
	public ArrayList<FilledForm> getFilledForms(){
		ArrayList<FilledForm> filledForms = (ArrayList<FilledForm>)conn.getTable("FilledForm");
		return filledForms;
	}
	
	public List<FilledForm> getFilledForms(String column,List<FormFields> fields){
		List<FilledForm> results =(List<FilledForm>) conn.searchCriteria(FilledForm.class, column, fields);
		return results;
	}
	
}
