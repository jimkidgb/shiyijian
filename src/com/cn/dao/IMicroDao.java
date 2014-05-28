package com.cn.dao;

import java.util.List;

import com.cn.model.type.Item;
import com.cn.model.wx.Micro;

public interface IMicroDao {
	public boolean configMicro(Micro m);
	
	public Micro getMicro(String menuid);

	public boolean addItem(Item i);

	public boolean editItem(Item i);

	public boolean removeItem(String id);

	public List<Item> getItemList(Integer page, Integer rows);

	public String getItemCount();
}
