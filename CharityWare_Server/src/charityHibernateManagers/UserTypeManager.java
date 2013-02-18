package charityHibernateManagers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sharedHibernateResources.ConnectionManager;
import charityHibernateEntities.UserType;

public class UserTypeManager {
	
	private ConnectionManager conn;
	public UserTypeManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	
	public UserType getUserType(Integer userTypeId){
		UserType userType = (UserType)conn.get(UserType.class,userTypeId);
		return userType;
	}
	
	public List<UserType> getUserTypes(){
		List<UserType> results = new  ArrayList<UserType>();
		List<UserType> userTypes = (List<UserType>)conn.getTable("UserType where isActive = 1");
		Iterator<UserType> userTypes_iter = userTypes.iterator();
		while(userTypes_iter.hasNext()){
			UserType userType_temp = userTypes_iter.next();
			if(userType_temp.getIsActive()){
				results.add(userType_temp);
			}
		}
		return results;
	}


}
