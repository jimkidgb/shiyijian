package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IMenuDao;
import com.cn.model.platform.Menu;
import com.cn.utils.DbUtils;

public class MenuDaoImpl extends DbUtils implements IMenuDao {
	@Override
	public boolean addMenu(Menu m) {
		String sql = "INSERT INTO t_platform_menu VALUES (null,?,?,?,?,?,?,?,?,now(),?,now())";
		Object[] params = new Object[] { m.getPId(), m.getText(),
				m.getHandel(), m.getType(), m.getState(), m.getSort(),
				m.getIcons(), m.getRemarks(), m.getModifier() };
		return insert(sql, params);
	}

	@Override
	public boolean editMenu(Menu m) {
		String sql = "UPDATE t_platform_menu SET pid=?,text=?,handel=?,type=?,modifydate=now(),modifier=?,state=?,sort=?,icons=?,remarks=? WHERE id = ?";
		Object[] params = new Object[] { m.getPId(), m.getText(),
				m.getHandel(), m.getType(), m.getModifier(), m.getState(),
				m.getSort(), m.getIcons(), m.getRemarks(), m.getId() };
		return update(sql, params);
	}

	@Override
	public List<Menu> getMenu(String userid) {
		String sql = "select * from t_platform_menu order by sort asc";
		return find(Menu.class, sql);
	}

	@Override
	public boolean removeMenu(String id) {
		String sql = "delete from t_platform_menu where id = ?";
		return update(sql, id);
	}

	@Override
	public List<Menu> getMenulist(String id) {
		String sql = "SELECT * FROM t_platform_menu WHERE id in (SELECT menuid FROM t_platform_permissions WHERE roleid IN (SELECT roleid FROM t_platform_userrole WHERE userid = ?)) and type=1 or type=0  order by sort asc";
		//String sql = "SELECT * FROM t_platform_menu where type=1 or type=0  order by sort asc";
		return find(Menu.class, sql,id);
	}

	@Override
	public List<Menu> getFunction(String pid,String userid) {
		//String sql ="SELECT * FROM t_platform_menu where ";
		String sql = "SELECT * FROM t_platform_menu WHERE id in (SELECT menuid FROM t_platform_permissions WHERE roleid IN (SELECT roleid FROM t_platform_userrole WHERE userid = ?)) and pid = ? order by sort asc";
		return find(Menu.class, sql,new Object[]{userid,pid});
	}
}
