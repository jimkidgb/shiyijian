package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cn.dao.IRoleDao;
import com.cn.model.platform.Role;
import com.cn.utils.DbUtils;

public class RoleDaoImpl extends DbUtils implements IRoleDao {

	@Override
	public boolean addRole(Role r) {
		boolean b = true;
		String sql = "insert into t_platform_role values(null,?,?,now(),?,now())";
		Object[] params = new Object[] { r.getRolename(), r.getRemarks(),r.getModifier() };
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		QueryRunner qr = new QueryRunner();
		try {
			int key = 0;
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
			sql = "insert into t_platform_permissions values(null,?,?,now(),?,now())";
			String[] strs = r.getPermissions().split(",");
			Object[][] param = new Object[strs.length][3];
			for (int i = 0; i < strs.length; i++) {
				param[i][0] = key;
				param[i][1] = strs[i];
				param[i][2] = r.getModifier();
			}
			qr.batch(conn, sql, param);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
			rollback(conn);
		} finally {
			close(rs, stmt, conn);
		}
		return b;
	}

	@Override
	public boolean editRole(Role r) {
		boolean b = true;
		String sql = "update t_platform_role set rolename=?,remarks=?,modifier=?,modifydate=now() where id = ?";
		Object[] params = null;
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			params = new Object[] { r.getRolename(), r.getRemarks(),r.getModifier(), r.getId() };
			qr.update(conn, sql, params);
			sql = "delete from t_platform_permissions where roleid = ?";
			qr.update(conn, sql, r.getId());
			sql = "insert into t_platform_permissions values(null,?,?,now(),?,now())";
			String[] strs = r.getPermissions().split(",");
			Object[][] param = new Object[strs.length][3];
			for (int i = 0; i < strs.length; i++) {
				param[i][0] = r.getId();
				param[i][1] = strs[i];
				param[i][2] = r.getModifier();
			}
			qr.batch(conn, sql, param);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
			rollback(conn);
		} finally {
			close(null, null, conn);
		}
		return b;
	}

	@Override
	public boolean removeRole(String id) {
		boolean b = true;
		String sql = "delete from t_platform_role where id = ?";
		Connection conn = null;
		QueryRunner qr = new QueryRunner();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			qr.update(conn, sql, id);
			sql = "delete from t_platform_permissions where roleid = ?";
			qr.update(conn, sql, id);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
			rollback(conn);
		} finally {
			close(null, null, conn);
		}
		return b;
	}

	@Override
	public String getRoleCount(String rolename) {
		String sql = "select count(*) from t_platform_role where 1=1 ";
		if (rolename != null && !rolename.equals("")) {
			sql += " and rolename like '%" + rolename + "%' ";
		}
		return findBy(sql, 1).toString();
	}

	@Override
	public List<Role> getRoleList(String rolename, Integer page, Integer rows) {
		String sql = "select * from t_platform_role where 1=1 ";
		if (rolename != null && !rolename.equals("")) {
			sql += " and rolename like '%" + rolename + "%' ";
		}
		sql += " limit ?,?";
		return find(Role.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public String getPre(String roleid) {
		String sql = "select menuid from t_platform_permissions where roleid = '"+ roleid + "'";
		return findColumn(sql);
	}

	@Override
	public List<Role> getRoleList() {
		String sql = "select * from t_platform_role where 1=1";
		return find(Role.class, sql);
	}
}
