package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IParamsDao;
import com.cn.model.wx.Params;
import com.cn.utils.DbUtils;

public class ParamsDaoImpl extends DbUtils implements IParamsDao {

	@Override
	public boolean addParams(Params p) {
		String sql = "insert into t_weixin_templateparams values(null,?,?,?)";
		Object[] params = new Object[] { p.getTitle(), p.getName(), p.getMsgid() };
		return insert(sql, params);
	}

	@Override
	public boolean editParams(Params p) {
		String sql = "update t_weixin_templateparams set title=?,name=? where id = ?";
		Object[] params = new Object[] { p.getTitle(), p.getName(), p.getId() };
		return update(sql, params);
	}

	@Override
	public List<Params> getParams(String msgid) {
		String sql = "select * from t_weixin_templateparams where msgid=?";
		return find(Params.class, sql, msgid);
	}

	@Override
	public boolean removeParams(String id) {
		String sql = "delete from t_weixin_templateparams where id = ?";
		return update(sql, id);
	}

}
