package com.cn.dao;

import java.util.List;

import com.cn.model.wx.Params;

public interface IParamsDao {
	public boolean addParams(Params p);

	public boolean editParams(Params p);

	public boolean removeParams(String id);

	public List<Params> getParams(String msgid);
}
