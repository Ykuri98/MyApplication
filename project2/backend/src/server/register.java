package server;

import server.dataUtil;
import server.user_info;
 
public class register 
{
	private static String user_type = "user"; 
	
	public static String register(String user_name,String user_password)
	{
		String response = "";
		
		if(dataUtil.isUserNameExist(user_name))
		{
			response = "exist";
		}
		
		else 
		{
			user_info user = new user_info();
			user.setUser_name(user_name);
			user.setUser_password(user_password);
			user.setUser_type(user_type);
			dataUtil.addNewUser(user);
			response = "succeed";
		}
		
		System.out.println(response);  //
		
		return response;
	}
 
}