package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IActResourceDao;
import com.cn.model.wx.ActResource;
import com.cn.utils.DbUtils;

public class ActResourceDaoImpl extends DbUtils implements IActResourceDao {

	@Override
	public boolean addActResource(ActResource a) {
		String sql = "insert into t_act_resource values (null,?,?,?,?)";
		Object[] params = new Object[] { a.getType(), a.getLocal(),
				a.getRemote(), a.getActid() };
		return update(sql, params);
	}

	@Override
	public List<ActResource> getActResourceList(String actid) {
		String sql = "select * from t_act_resource where actid = ?";
		return find(ActResource.class, sql, actid);
	}

	@Override
	public boolean removeActResource(String id) {
		String sql = "delete from t_act_resource where id = ?";
		return update(sql, id);
	}

}
