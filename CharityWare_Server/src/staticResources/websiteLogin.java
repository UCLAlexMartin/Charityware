package staticResources;

import systemHibernateEntities.User;
import RESTSystemClient.UserClient;

public class websiteLogin {
	
	public static void main(String[] arg)
	{
		User usr = websiteLogin.login("ucladmin","open");
		if(usr != null)
		{
			System.out.println("Login succesful");
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
		return true;
	}
	

}
