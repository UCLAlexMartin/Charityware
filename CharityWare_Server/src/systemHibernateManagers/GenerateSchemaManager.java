package systemHibernateManagers;

import java.util.HashMap;
import java.util.Map;

import sharedHibernateResources.ConnectionManager;
import staticResources.Configuration;



public class GenerateSchemaManager {
	private static ConnectionManager conn;
	
	
	public static Boolean generateSchema(int CharityId) throws Exception{
	
			
	   		String CharityName = "charity" + CharityId;
	   		String Username = Configuration.getMySQLrootUser();
	   		String Password = Configuration.getMySQLrootPassword();	   	
	   		
	   		Class.forName(Configuration.getMySQLdriver()).newInstance();
		    //conn  = DriverManager.getConnection(Configuration.getMySQLConUrl()+"System_DB_Test_Model", Username,Password);
	   		conn = new ConnectionManager("");
	   		try {
	   			
	   			/*CallableStatement statement = 
	   					//conn.prepareCall("{call spSchemaGeneration(?)}");
	   			statement.setString("DB_Name", CharityName);
	   	    	statement.executeQuery();
	   	    	*/
	   			Map<String,String> parameters_map = new HashMap<String,String>();
	   			parameters_map.put("DB_Name", CharityName);
	   			conn.runProcedure("spSchemaGeneration", parameters_map);
	   			System.out.println("Charity Schema Generated");
	   	    	GenerateConfig.execute(CharityName,Username,Password);
	   	    	System.out.println("Charity Config Generated");
	   	    	CharityManager chMng = new CharityManager();
	   	    	chMng.approveCharity(CharityId);
	   	    	System.out.println("Charity Table Updated");
	   	    	
	   	    	return true;
	   			}catch(Exception e){
	   				e.printStackTrace();
	   				return false;
	   			}	
	   	}
}
