package com.cn.dao;

import java.util.List;

import com.cn.model.platform.Menu;

public interface IMenuDao {
	public boolean addMenu(Menu m);
	public boolean editMenu(Menu m);
	public boolean removeMenu(String id);
	public List<Menu> getMenu(String id);
	public List<Menu> getMenulist(String userid);
	public List<Menu> getFunction(String pid,String userid);
}
