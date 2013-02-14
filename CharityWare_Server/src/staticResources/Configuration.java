package staticResources;

import java.io.FileInputStream;
import javax.xml.bind.JAXBContext;

public  class Configuration {

	private static String MySQLrootUser;
	private static String MySQLrootPassword;
	private static String MySQLdriver;
	private static String MySQLConUrl;
	
	private static String SiteUrl;//URL of the website, not the database
	private static Resources  resource;
	static {
		System.out.println("Configuration construct");
		try{
			System.out.println("New JAXB parser");
			JAXBContext context = JAXBContext.newInstance(Resources.class);
			System.out.println("Get file");
			System.out.println();
			resource = (Resources) context.createUnmarshaller().unmarshal(Configuration.class.getResourceAsStream("Resources.xml"));
			//resource = (Resources) context.createUnmarshaller().unmarshal(new FileInputStream("C:\\Users\\Alex\\Desktop\\RELEASE_Charityware\\CharityWare_Server\\src\\staticResources\\Resources.xml"));
			System.out.println("Set variables");
			MySQLrootUser = resource.getMySQLRootUser();
			MySQLrootPassword = resource.getMySQLRootPassword();
			MySQLdriver = resource.getMySQLDriver();
			MySQLConUrl = resource.getMySQLConUrl();
			SiteUrl = resource.getSiteUrl();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Configuration end");
	}
	
	public static String getMySQLrootUser() {
		return MySQLrootUser;
	}
	public static String getMySQLrootPassword() {
		return MySQLrootPassword;
	}
	public static String getMySQLdriver() {
		return MySQLdriver;
	}
	public static String getMySQLConUrl() {
		return MySQLConUrl;
	}
	public static String getSiteUrl() {
		return SiteUrl;
	}
}