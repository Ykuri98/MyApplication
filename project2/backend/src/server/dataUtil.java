package server;

import java.util.Iterator;
import java.util.LinkedList;
 
public class dataUtil 
{
	private static LinkedList<user_info> online_user = new LinkedList<user_info>(); //�����û���
	
	private static LinkedList<user_info> user_list = new LinkedList<user_info>(); //�û������
	
	
	/* ���ݲ������ܺ��� */
	public static boolean addOnlineUser(user_info user)
	{   //�û���¼ �������û��б�����û� ���ڵ�¼�ɹ�ʱ����
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
	{  //�û��˳� �������û��б��Ƴ��û�
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
	{   //�û�ע�� �򻺴��������û�
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
	{   //�ж��û����Ƿ����
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
	{   //����ȷ���û�����ʱ����
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
	
	
	/* ��ʱ���� ���߳���ִ�� �������û�ע��ɹ�ʱִ�� */
	public static void loadUserList()
	{   //�����ݿ�����û������
		
	}
	
	public static void saveUserList() 
	{   //�����ݿ��д�û������
		
	}
 
 
}