package com.cn.dao;

import java.util.List;

import com.cn.model.type.Item;
import com.cn.model.wx.MsgType;
import com.cn.model.wx.Wxmenu;

public interface IWxMenuDao {
	public boolean addWxMenu(Wxmenu m);

	public boolean editWxMenu(Wxmenu m);

	public boolean removeWxMenu(String id);

	public List<Wxmenu> getWxMenulist(String account);

	public List<Wxmenu> getWxMenuIsNull(String account);

	public List<MsgType> getMsgType();

	public boolean addItems(String content, String type, String id);

	public boolean addItems(List<Item> list, String type, String id);
	
	public boolean editStatus(String id);
	
	public boolean removeItems(String id);

	public String getItemContent(String menuid);
	
	public List<Item> getItemList(String menuid);
	
	public Object getYs(String menuid,Integer type);
	
	public boolean editStatus(String id,String status);
}
