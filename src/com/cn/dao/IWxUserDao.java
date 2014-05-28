package com.cn.dao;

import java.util.List;

import com.cn.model.wx.Group;
import com.cn.model.wx.User;

public interface IWxUserDao {
	public boolean addGroup(Group g);

	public boolean editGroup(Group g);
	
	public String getRtid(String groupid);

	public List<Group> getGroupList(String type);

	public List<User> getUserList(User u, String groupid,Integer page, Integer rows);
	
	public String getUserCount(User u, String groupid);
	
	public List<User> getUserSystemList(User u, String groupid,Integer page, Integer rows);
	
	public String getUserSystemCount(User u, String groupid);
	
	public List<User> getUserNOSystemList(User u,Integer page, Integer rows);

	public String getUserNOSystemCount(User u);
	
	public List<User> getUserNOList(User u,Integer page, Integer rows);

	public String getUserNOCount(User u);
	
	public boolean moveUserGroup(String groupid,String [] userid);
	
	public List<User> getUserList48(User u,Integer page, Integer rows);

	public String getUserCount48(User u);
}
