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
		try{
			User MyUser = UserClient.get(userName);
			if(PasswordEncryption.encryptPassword(password, MyUser.getSalt()).equals(MyUser.getUserPassword()))
			{
				return MyUser;
			}else
			{
				return null;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
