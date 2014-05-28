package com.cn.dao;

import java.util.List;

import com.cn.model.wx.Usernoterlog;

public interface IStatisticalDao {
	public String getStatisticalCount(String status, String date, String type,String account);

	public String getDayCount(String status, String type,String account);
	
	public String getDayCount();
	
	public List<Usernoterlog> getUsernoterlog48();
}
