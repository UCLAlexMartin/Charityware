package charityHibernateManagers;


import charityHibernateEntities.User;
import sharedHibernateResources.ConnectionManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CharityLoginManager {

	public static ArrayList<User> getUsers(String name, String DBConfname){
		System.out.println("Start Conn");
		ConnectionManager conn = new ConnectionManager();
		System.out.println("Set DB");
		conn.setDBConfname(DBConfname);
		System.out.println("Select");
		ArrayList<User> user = (ArrayList<User>)conn.getTable("User where userName = '" + name+"'");
		return user;
	}
	
}
