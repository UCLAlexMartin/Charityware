package systemHibernateManagers;

import systemHibernateEntities.User;

import java.util.ArrayList;

import sharedHibernateResources.ConnectionManager;

public class UserManager {
	
	//private String DBConfname;
	private ConnectionManager conn;
	
	public UserManager(){
		conn = new ConnectionManager("");
	}
	
	public  ArrayList<User> getUsers(String name){
		ArrayList<User> user = (ArrayList<User>)conn.getTable("User where userName = '"+ name+"'");
		return user;
	}

}
