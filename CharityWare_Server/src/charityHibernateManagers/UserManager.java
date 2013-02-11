package charityHibernateManagers;

import charityHibernateEntities.Form;
import charityHibernateEntities.FormFields;
import charityHibernateEntities.FormPermissions;
import charityHibernateEntities.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sharedHibernateResources.ConnectionManager;




public class UserManager {
	
	private String DBConfname;
	
	public UserManager(String DBConfname){
		this.DBConfname = DBConfname;
	}
	
	public ArrayList<User> retrieve(){
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		ArrayList<User> users = (ArrayList<User>) conn.getTable("User");
		return users;
	}
	
	public  ArrayList<User> getUsers(String name){
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		ArrayList<User> user = (ArrayList<User>)conn.getTable("User where userName = '" + name+"'");
		return user;
	}
	
	
	
	public Integer addUserSample (String name,String pass) {
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		User user = new User (name,pass);
		return (Integer) conn.transaction("save",user);
		
	}
	
	public User getUser(Integer id){
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		User user = (User)conn.get(User.class,id);
		return user;
	}
	
	/*public static ArrayList<User> getUsers(String name){
		ArrayList<User> user = (ArrayList<User>)ConnectionManager.getTable("User where userName = '" + name+"'");
		return user;
	}*/
	
	public  void updateUserPassword (Integer userId,String userPassword ) {
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		User user = (User) conn.get(User.class, userId);
		user.setUserPassword(userPassword);
		conn.transaction("update",user);
	}
	
	public Map<Integer,List<String>> getForms(){
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		Map<Integer,List<String>> results = new TreeMap<Integer,List<String>>();
		ArrayList<User> users = (ArrayList<User>) conn.getTable("User");
		
		ArrayList<FormPermissions> formpermissions = (ArrayList<FormPermissions>) conn.getTable("FormPermissions");
		
		Iterator<User> iter = users.iterator();			
		while(iter.hasNext()){
			ArrayList<String> userdata = new ArrayList<String>();
			User user_cur = iter.next();
			Iterator<FormPermissions> formperm_iter = formpermissions.iterator();
			String value = null;
			while(formperm_iter.hasNext()){
				FormPermissions formperm_cur = formperm_iter.next();
				
				if(value==null){
					value = formperm_cur.getPk().getForm().getFormName();
				}else{
					value = value+","+formperm_cur.getPk().getForm().getFormName();
				}
			}
			userdata.add(user_cur.getUserName());
			userdata.add(user_cur.getUserTypeId().getUserType());
			userdata.add(user_cur.getUserEmail());
			userdata.add(value);			
			results.put(user_cur.getUser_id(),userdata);
		}
		return results;
	}
	public Map<Integer,Map<Integer,List<String>>> getFormEntities(String username){
		ConnectionManager conn = new ConnectionManager();
		conn.setDBConfname(this.DBConfname);
		ArrayList<User> users = (ArrayList<User>)conn.getTable("User where userName = '" + username+"'");
		User user = users.get(0);
		ArrayList<FormPermissions> formpermissions = (ArrayList<FormPermissions>) conn.getTable("FormPermissions");
		ArrayList<FormFields> formfieldsData = (ArrayList<FormFields>) conn.getTable("FormFields");
		//ArrayList<String> formFields_results = new ArrayList<String>();
		Map<Integer,Map<Integer,List<String>>> results = new TreeMap<Integer,Map<Integer,List<String>>>();
		Iterator<FormPermissions> formperm_iterator = formpermissions.iterator();
		while(formperm_iterator.hasNext()){
			FormPermissions formpermisions = formperm_iterator.next();
			
			Map<Integer,List<String>> formfields_map = new TreeMap<Integer,List<String>>();
			Form current_form = formpermisions.getPk().getForm();
			if(formpermisions.getPk().getUser_type().getUserTypeId().equals(user.getUserTypeId().getUserTypeId())&&current_form.getIsActive()){
				Iterator<FormFields> formfields_iterator = formfieldsData.iterator();
				while(formfields_iterator.hasNext()){
					FormFields formfields = formfields_iterator.next();
					if(formfields.getIsActive()){
						ArrayList<String> formFields_results = new ArrayList<String>();
						formFields_results.add(current_form.getFormName());
						if(formfields.getField_label()!=null)formFields_results.add(formfields.getField_label());
						if(formfields.getIsRequired()!=null)formFields_results.add(formfields.getIsRequired().toString());
						if(formfields.getDefault_value()!=null)formFields_results.add(formfields.getDefault_value().toString());
						if(formfields.getMaxValue()!=null)formFields_results.add(formfields.getMaxValue().toString());
						if(formfields.getMinValue()!=null)formFields_results.add(formfields.getMinValue().toString());
						if(formfields.getDate_created()!=null)formFields_results.add(formfields.getDate_created().toString());
						if(formfields.getField_type_id()!=null)formFields_results.add(formfields.getField_type_id().getField_type_id().toString());
						if(formfields.getX_coordinate()!=null)formFields_results.add(formfields.getX_coordinate().toString());
						if(formfields.getY_coordinate()!=null)formFields_results.add(formfields.getY_coordinate().toString());
						formfields_map.put(formfields.getF_id(),formFields_results);
					}
					
				}
				if(formfields_map.size()!=0){
					results.put(current_form.getFormId(), formfields_map);
				}
			
			}
				
		}		
		return results;
	}
	
	
	
	/*
	 * 
	 * public static Map<Integer,ArrayList<String>> readUsers() throws Exception {
	 //String result = "";
	 Map<Integer,ArrayList<String>> users = new TreeMap<Integer,ArrayList<String>>();
	 getCharityConn("Charity_Db_Test_Model");
	 statement = conn.createStatement();
	 resultSet = statement.executeQuery
			 	("SELECT Users.Username, User_Type.User_Type, Users.User_Email, GROUP_CONCAT(Form_Name) AS permissions, Users.User_Id " +
	"FROM User_Type INNER JOIN Users ON User_Type.User_Type_Id = Users.User_Type_Id "+
	"INNER JOIN Form_Permissions ON Form_Permissions.User_Type_Id = User_Type.User_Type_Id "+
	"INNER JOIN Form ON Form.Form_Id = Form_Permissions.Form_Id "+
			 	"GROUP BY User_Id ");

	 while(resultSet.next())
	 {
		 ArrayList<String> userdata = new ArrayList<String>();
		 userdata.add(resultSet.getString(1));
		 userdata.add(resultSet.getString(2));
		 userdata.add(resultSet.getString(3));
		 userdata.add(resultSet.getString(4));
		
		 
		 users.put(resultSet.getInt(5), userdata);
	 }
	 	return users;
	}*/
}
