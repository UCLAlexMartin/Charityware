package charityHibernateManagers;

import charityHibernateEntities.Form;
import charityHibernateEntities.FormPermissions;
import charityHibernateEntities.User;
import charityHibernateEntities.UserType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;

import sharedHibernateResources.ConnectionManager;

public class UserManager {
	
	private String DBConfname;
	private ConnectionManager conn;
	
	public UserManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
		this.DBConfname=DBConfname;
	}
	
	public List<User> retrieve(){		
		List<User> users = (List<User>) conn.getTable("User");
		return users;
	}
	
	public  List<User> getUsers(String name){
		List<User> user = (List<User>)conn.getTable("User where userName = '"+ name +"'");
		return user;
	}
	
	
	public Integer addUserSample (String name,String pass) {
		User user = new User (name,pass);
		return (Integer) conn.transaction("save",user);
		
	}
	
	public Integer addUser (String name,String pass,String email, int userTypeId ) {
		UserTypeManager ut =new UserTypeManager(this.DBConfname);
		UserType usertype = (UserType)ut.getUserType(new Integer (userTypeId));
		User user = new User(name,pass);
		user.setDateCreated(new Date(Calendar.DATE));
		user.setUserEmail(email);
		user.setUserType(usertype);
		user.setIsActive(true);
		return (Integer) conn.transaction("save",user);	
	}
	
	public User getUser(Integer id){
		User user = (User)conn.get(User.class,id);
		return user;
	}
	
	
	/*public static ArrayList<User> getUsers(String name){
		ArrayList<User> user = (ArrayList<User>)ConnectionManager.getTable("User where userName = '" + name+"'");
		return user;
	}*/
	
	public  void updateUserPassword (Integer userId,String userPassword ) {
		User user = (User) conn.get(User.class, userId);
		user.setUserPassword(userPassword);
		conn.transaction("update",user);
	}
	
	public Map<Integer,List<String>> getForms(){
		Map<Integer,List<String>> results = new TreeMap<Integer,List<String>>();
		List<User> users = (List<User>) conn.getTable("User where isActive = 1");
		
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
			userdata.add(user_cur.getUserType().getUserType());
			userdata.add(user_cur.getUserEmail());
			userdata.add(value);			
			results.put(user_cur.getUser_id(),userdata);
		}
		return results;
	}
	
	public List<Form> getFormEntities(String username){
		
		ArrayList<User> users = (ArrayList<User>)conn.getTable("User where userName = '" + username+"'");
		User user = users.get(0);
		List<Form> results = new ArrayList<Form>();
		ArrayList<FormPermissions> formPermissionsList = (ArrayList<FormPermissions>) conn.getTable("FormPermissions");
		Iterator<FormPermissions> formPermissions_iter = formPermissionsList.iterator();
		//Session session = conn.getSession();
		while(formPermissions_iter.hasNext()){
			FormPermissions formPermissions = formPermissions_iter.next();
			
			if(user.getUserType().getUserTypeId().equals(formPermissions.getPk().getUser_type().getUserTypeId())){
				
				results.add(formPermissions.getPk().getForm());
			}
			
		}
		//conn.closeSession(session);
		return results;
	}
	
	public void deactivateUserAccount(Integer userId){
		User user = (User) conn.get(User.class, userId);
		user.setIsActive(false);
		user = (User) conn.merge(user);
		conn.transaction("update",user);
	}
	
	public String getCharityActiveUsers(){
		//String result = "";
		ArrayList<User> activeUser = (ArrayList<User>)conn.getTable("User where isActive=1");
		ArrayList<User> inactiveUser = (ArrayList<User>)conn.getTable("User where isActive=0");
		
		StringBuilder finalresult = new StringBuilder();
		
		finalresult.append('[');
		finalresult.append(String.format("[\"%s\",%d],", "Active Accounts", activeUser.size()));
		finalresult.append(String.format("[\"%s\",%d]", "Inactive Accounts", inactiveUser.size()));
		finalresult.append(']');
		
		return finalresult.toString();
	}
	
	
	
	
	/*public Map<Integer,Map<Integer,List<String>>> getFormEntities(String username){
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
	}*/
	
	/*public Map<FormKey,List<FormFields>> getFormEntities2(String username){
		ArrayList<User> users = (ArrayList<User>)conn.getTable("User where userName = '" + username+"'");
		User user = users.get(0);
		Map<FormKey,List<FormFields>> results = new TreeMap<FormKey,List<FormFields>>();
		ArrayList<FormPermissions> formpermissions = (ArrayList<FormPermissions>) conn.getTable("FormPermissions");
		ArrayList<FormFields> formfieldsData = (ArrayList<FormFields>) conn.getTable("FormFields");
		Iterator<FormPermissions> formperm_iterator = formpermissions.iterator();
		while(formperm_iterator.hasNext()){
			FormPermissions formpermisions = formperm_iterator.next();
			Form current_form = formpermisions.getPk().getForm();
			
			if(formpermisions.getPk().getUser_type().getUserTypeId().equals(user.getUserType().getUserTypeId())&&current_form.getIsActive()){
				FormKey fk = new FormKey(current_form.getFormId(),current_form.getFormName());
				Iterator<FormFields> formfields_iterator = formfieldsData.iterator();
				List<FormFields> formfields_list = new ArrayList<FormFields>();
				while(formfields_iterator.hasNext()){
					FormFields formfields = formfields_iterator.next();
					//if (formfields.getForm().getFormId().equals(current_form.getFormId())){
						formfields_list.add(formfields);
					//}
				}
				results.put(fk, formfields_list);
			}
		}
		return results;
	}*/
	
	
	
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
