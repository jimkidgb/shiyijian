package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IGroupDao;
import com.cn.model.platform.Group;
import com.cn.utils.DbUtils;

public class GroupDaoImpl extends DbUtils implements IGroupDao {
	@Override
	public boolean addGroup(Group u) {
		String sql = "insert into t_platform_group values (null,?,?,?,now(),?,now())";
		Object[] params = new Object[] { u.getText(), u.getPid(), u.getRemarks(), u.getModifier() };
		return insert(sql, params);
	}

	@Override
	public boolean editGroup(Group u) {
		String sql = "update t_platform_group set text=?,pid=?,modifier=?,remarks=?,modifydate=now() where id = ?";
		Object[] params = new Object[] { u.getText(), u.getPid(), u.getModifier(), u.getRemarks(), u.getId() };
		return update(sql, params);
	}

	@Override
	public List<Group> getGroup() {
		String sql = "select * from t_platform_group";
		return find(Group.class, sql);
	}

	@Override
	public boolean removeGroup(String id) {
		String sql = "delete from t_platform_group where id = ?";
		return update(sql, id);
	}

	@Override
	public boolean isLower(String id) {
		String sql = "select count(*) from t_platform_group where pid = ?";
		int count = Integer.parseInt(findBy(sql, 1,id).toString());
		return count > 0 ? false : true;
	}

	@Override
	public boolean isUser(String id) {
		String sql = "select count(*) from t_platform_users where groupid = ?";
		int count = Integer.parseInt(findBy(sql, 1,id).toString());
		return count > 0 ? false : true;
	}
}
