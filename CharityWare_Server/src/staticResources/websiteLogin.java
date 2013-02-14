package staticResources;

import java.io.File;

import systemHibernateEntities.User;
import RESTSystemClient.UserClient;


public class websiteLogin {
	
	public static void main(String[] arg)
	{
		User usr = websiteLogin.login("ucladmin","open");
		if(usr != null)
		{
			System.out.println("Login succesful");
			String url = "http://localhost:8080/CharityWare_Lite/default.jsp";
			if(isAuthenticated(usr, url))
			{
				System.out.println("Valid to access: " + url);
			}
			else
			{
				System.out.println("Invalid to access: " + url);
			}
		}
		else
		{
			System.out.println("Login failed");
		}
		
	}
	
	public static User login(String userName, String password)
	{
		System.out.println("Start login");
		try{
			System.out.println("Contact service");
			User MyUser = UserClient.get(userName);
			System.out.println("Check password");
			if(PasswordEncryption.encryptPassword(password, MyUser.getSalt()).equals(MyUser.getUserPassword()))
			{
				System.out.println("End MyUser");
				return MyUser;
			}else
			{
				System.out.println("End Null");
				return null;
			}
		}catch(Exception e)
		{
			System.out.println("End Print Stack");
			e.printStackTrace();
		}
		System.out.println("End null");
		return null;
	}
	
	public static boolean isAuthenticated(User user, String URL)
	{
		Boolean ret = false;
		System.out.println(new File(URL).getName());
		System.out.println(user.getUserType().getUserTypeId());
		switch(new File(URL).getName().toLowerCase())
		{
		case "default.jsp":
				switch(user.getUserType().getUserTypeId())
				{
				case 0:
					ret = true;
					break;
				case 1:
					ret = true;
					break;
				}
			break;
		
		}
		return ret;
	}
	

}
