package systemHibernateManagers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import systemHibernateEntities.Charity;
import systemHibernateEntities.User;
import sharedHibernateResources.ConnectionManager;

public class CharityManager {
	
	private ConnectionManager conn;
	
	public CharityManager(){
		conn = new ConnectionManager("");
	} 

	public List<Charity> getCharitiesRequests(){
		List<Charity> charities = (List<Charity>)conn.getTable("Charity where isVerified = 0 and isActive = 0");
		return charities;
	}
	
	public List<Charity> getCharities(){
		List<Charity> results = new  ArrayList<Charity>();
		List<Charity> charities = (List<Charity>)conn.getTable("Charity");
		Iterator<Charity> charities_iter = charities.iterator();
		while(charities_iter.hasNext()){
			Charity char_temp = charities_iter.next();
			if(char_temp.getIsActive()&&char_temp.getIsVerified()){
				results.add(char_temp);
			}
		}
		return results;
	}
	
	public Charity getCharity(int charityID){
		Charity charity = (Charity)conn.get(Charity.class, charityID);
		return charity;
	}
	public Integer addCharity (Charity newCharity) { 
		UserManager userManager = new UserManager();
		User user = (User) userManager.getUsers("rcadmin").get(0);
		newCharity.setUser(user);
		return (Integer) conn.transaction("save",newCharity);
	}
	
	public Integer addCharities (List<Charity> newCharities) { 
		Iterator<Charity> char_iterator = newCharities.iterator();
		
		while(char_iterator.hasNext()){
			Charity charity = char_iterator.next();
			UserManager userManager = new UserManager();
			User user = (User) userManager.getUsers("rcadmin").get(0);
			charity.setUser(user);
			if(conn.transaction("save",charity)==null)
				return null;
		}
		
		return 1;
	}
}
