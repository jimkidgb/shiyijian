package com.cn.dao;

import java.util.List;

import com.cn.model.platform.Role;

public interface IRoleDao {
	public boolean addRole(Role r);

	public boolean editRole(Role r);

	public boolean removeRole(String id);

	public List<Role> getRoleList(String rolename, Integer page, Integer rows);

	public String getRoleCount(String rolename);
	
	public String getPre(String roleid);
	
	public List<Role> getRoleList();
}
