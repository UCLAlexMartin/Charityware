package systemHibernateManagers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import staticResources.Configuration;



public class GenerateSchemaManager {
	private static Connection conn = null;
	
	
	public static Boolean generateSchema(int CharityId) throws Exception{
	
			CharityManager chMng = new CharityManager();
	   		String CharityName = "charity" + CharityId;
	   		String Username = Configuration.getMySQLrootUser();
	   		String Password = Configuration.getMySQLrootPassword();	   	
	   		
	   		Class.forName(Configuration.getMySQLdriver()).newInstance();
		    conn  = DriverManager.getConnection(Configuration.getMySQLConUrl()+"System_DB_Test_Model", Username,Password);
	   		
	   		try {
	   			
	   			CallableStatement statement = conn.prepareCall("{call spSchemaGeneration(?)}");
	   			statement.setString("DB_Name", CharityName);
	   	    	statement.executeQuery();
	   	    	System.out.println("Charity Schema Generated");
	   	    	
	   	    	GenerateConfig.execute(CharityName,Username,Password);
	   	    	System.out.println("Charity Config Generated");
	   	    	
	   	    	chMng.approveCharity(CharityId);
	   	    	System.out.println("Charity Table Updated");
	   	    	
	   	    	return true;
	   			}catch(Exception e){
	   				e.printStackTrace();
	   				return false;
	   			}	
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
