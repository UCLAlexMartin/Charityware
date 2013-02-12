package systemHibernateManagers;

import java.util.ArrayList;

import systemHibernateEntities.Charity;
import sharedHibernateResources.ConnectionManager;



public class CharityManager {
	

	//private String DBConfname;
	private ConnectionManager conn;
	
	
	//	public static Integer addCharity (Charity newCharity) { 
	//		return (Integer) ConnectionManager.transaction("save",newCharity);
	//	}
	
	public CharityManager(){
		//this.DBConfname = DBConfname;
		System.out.println("HELLLOOO!");
		conn = new ConnectionManager("");
		//conn.setDBConfname(DBConfname);
	} 

	public ArrayList<Charity> getCharitiesRequests(){
		ArrayList<Charity> charity = (ArrayList<Charity>)conn.getTable("Charity where isVerified = 0 and isActive = 0");
		return charity;
	}
	
	
}
