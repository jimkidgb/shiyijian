package com.cn.dao;

import java.util.List;

import com.cn.model.platform.User;
import com.cn.model.platform.UserRole;
public interface IUserDao {
	public List<User> getUserList(String realname,String username,String groupid,Integer page, Integer rows);
	
	public List<User> getUserList();

	public String getUserCount(String realname,String username,String groupid);

	public boolean addUser(User u);

	public boolean editUser(User u);

	public boolean removeUser(String id);

	public User getUser(String username);
	
	public List<UserRole> getUserRole(String userid);
	
	public boolean updateStatus(String id,String status);
	
	public List<User> getUserList(String groupid);
}
