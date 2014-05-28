package com.cn.dao;

import java.util.List;

import com.cn.model.wx.ActResource;

public interface IActResourceDao {
	public boolean addActResource(ActResource a);

	public boolean removeActResource(String id);

	public List<ActResource> getActResourceList(String actid);
}
