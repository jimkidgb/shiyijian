package com.cn.dao;

import java.util.List;

import com.cn.model.platform.Group;

public interface IGroupDao {
	public boolean addGroup(Group u);

	public boolean editGroup(Group u);

	public boolean removeGroup(String id);

	public List<Group> getGroup();

	public boolean isLower(String id);

	public boolean isUser(String id);
}