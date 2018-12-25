package server;

import java.util.Iterator;
import java.util.LinkedList;
 
public class dataUtil 
{
	private static LinkedList<user_info> online_user = new LinkedList<user_info>(); //在线用户表
	
	private static LinkedList<user_info> user_list = new LinkedList<user_info>(); //用户缓存表
	
	
	/* 数据操作功能函数 */
	public static boolean addOnlineUser(user_info user)
	{   //用户登录 向在线用户列表添加用户 仅在登录成功时调用
		if(!online_user.contains(user))
		{
			online_user.add(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean removeOnlineUser(user_info user)
	{  //用户退出 从在线用户列表移除用户
		if(online_user.contains(user))
		{
			online_user.remove(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean addNewUser(user_info user) 
	{   //用户注册 向缓存表添加新用户
		if(!user_list.contains(user))
		{
			user_list.add(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isUserNameExist(String user_name) 
	{   //判断用户名是否存在
		boolean result = false;
		Iterator<user_info> iterator = user_list.iterator();
		while(iterator.hasNext())
		{
			user_info user = (user_info) iterator.next();
			String name = user.getUser_name();
			if(name.equals(user_name))
			{
				result = true;
				break;
			}
		}
		return result;
	}
	
	public static user_info getUser(String user_name)
	{   //仅在确定用户存在时调用
		Iterator<user_info> iterator = user_list.iterator();
		user_info user = new user_info();
		while(iterator.hasNext())
		{
			user = (user_info) iterator.next();
			String name = user.getUser_name();
			if(name.equals(user_name))
			{
				break;
			}
		}
		return user;
	}
	
	
	/* 定时任务 在线程中执行 或在新用户注册成功时执行 */
	public static void loadUserList()
	{   //从数据库加载用户缓存表
		
	}
	
	public static void saveUserList() 
	{   //向数据库回写用户缓存表
		
	}
 
 
}