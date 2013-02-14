package systemHibernateManagers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import staticResources.Configuration;
import systemHibernateEntities.Charity;


public class GenerateSchemaManager {
	
	private static Connection conn = null;
	
	public static Boolean generateSchema(int CharityId) throws Exception{
	
	   		String CharityName = "charity" + CharityId;
	   		String Username = Configuration.getMySQLrootUser();
	   		String Password = Configuration.getMySQLrootPassword();	   	
	   		
	   		Class.forName(Configuration.getMySQLdriver()).newInstance();
		    conn  = DriverManager.getConnection(Configuration.getMySQLConUrl()+"System_DB_Test_Model", Username,Password);
	   		
	   		try {
	   			
	   			CallableStatement statement = conn.prepareCall("{call spSchemaGeneration(?)}");
	   			statement.setString("DB_Name", CharityName);
	   	    	statement.executeQuery();
	   	    	GenerateConfig.execute(CharityName,Username,Password);
	   	    	return true;
	   			}catch(Exception e){
	   				e.printStackTrace();
	   				return false;
	   			}	
	   	}
	
	public static Boolean approveCharity(int CharityId) throws Exception{
		
		
		Charity approvedCharity = new Charity();
		approvedCharity.setIsActive(true);
		approvedCharity.setIsVerified(true);
		
		return true;
		
	}
	

//	private boolean generateSchema(int CharityId)
//	{
//		boolean isSuccessful;
//		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//	    Transaction tx = null;
//	    
//	    String DBName = "Charity" + CharityId;
//	    
//	    try{
//	         tx = session.beginTransaction();
//	         
//	         session.createSQLQuery("call spSchemaGeneration(':DB_Name')").setParameter("DB_Name", DBName);
//	                  	         
//	         tx.commit();
//	         isSuccessful = true;
//	         System.out.print("Schema generated Successfully");
//
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace();
//	         isSuccessful = false;
//	         System.out.print("Cannot generate Schema");
//	         
//	      }finally {
//	         session.close();
//	      }
//	    
//	    return isSuccessful;	
//		
	

}
