package server;

import server.dataUtil;

public class login 
{
	public static String login(String user_name,String user_password) 
	{
		String response = "";
		
		if(dataUtil.isUserNameExist(user_name))
		{
			user_info user = dataUtil.getUser(user_name);
			if(user.getUser_password().equals(user_password))
			{
				response = user.getUser_type();
				dataUtil.addOnlineUser(user);
			}
			else {
				response = "failed";
			}
		}
		
		else {
			response = "not_exist";
		}
		
		System.out.println(response); //
		
		return response;
	}
 
}