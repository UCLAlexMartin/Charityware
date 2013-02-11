package staticResources;

import java.io.FileInputStream;
import javax.xml.bind.JAXBContext;

public  class Configuration {

	private static String MySQLrootUser;//="root";
	private static String MySQLrootPassword;//="root";
	private static String MySQLdriver;//="com.mysql.jdbc.Driver";
	private static String MySQLConUrl;// = "jdbc:mysql://127.0.0.1:3306/";
	
	private static String SiteUrl;// = "http://localhost:8080/CharityWare_FINAL/"; //URL of the website, not the database
	private static Resources  resource;
	static {
		
	

		try{
			JAXBContext context = JAXBContext.newInstance(Resources.class);
			resource = (Resources) context.createUnmarshaller().unmarshal(new FileInputStream("src\\staticResources\\Resources.xml"));
			/*
			System.out.println(resource.getSiteUrl());
			System.out.println(resource.getMySQLDriver());
			System.out.println(resource.getMySQLConUrl());
			System.out.println(resource.getMySQLRootUser());
			System.out.println(resource.getMySQLRootPassword());*/
			MySQLrootUser = resource.getMySQLRootUser();
			MySQLrootPassword = resource.getMySQLRootPassword();
			MySQLdriver = resource.getMySQLDriver();
			MySQLConUrl = resource.getMySQLConUrl();
			SiteUrl = resource.getSiteUrl();
		}
		catch(Exception e){
			e.printStackTrace();
		}
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