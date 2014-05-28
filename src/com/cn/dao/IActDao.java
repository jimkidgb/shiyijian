package com.cn.dao;

import java.util.List;

import com.cn.model.wx.Act;
import com.cn.model.wx.Export;

public interface IActDao {
	public boolean addAct(Act a);

	public boolean editAct(Act a);

	public boolean removeAct(String id);

	public List<Act> getActList(Act a, Integer page, Integer rows);

	public String getActCount(Act a);

	public boolean editStatus(String id, String status);
	
	public void addSQL(String inPath,String outPath);
	
	public List<Export> getExport();
	
	public String getColumnJson(String sql);
	
	public String getDataJson(String sql);
	
	public Export getExport(String id);

}
