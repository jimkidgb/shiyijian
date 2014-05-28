package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IWxUserDao;
import com.cn.model.wx.Group;
import com.cn.model.wx.User;
import com.cn.utils.DbUtils;

public class WxUserDaoImpl extends DbUtils implements IWxUserDao {

	@Override
	public boolean addGroup(Group g) {
		String sql = "insert into t_weixin_group values(null,?,?,?)";
		Object[] params = new Object[] { g.getText(), g.getRtid(),"WX"};
		return insert(sql, params);
	}

	@Override
	public List<Group> getGroupList(String type) {
		String sql = "select * from t_weixin_group where type = ?";
		return find(Group.class, sql,type);
	}

	@Override
	public String getUserCount(User u, String groupid) {
		String sql = "select count(*) from t_weixin_user where 1=1 ";
		if (groupid != null) {
			sql += " and WEIXINID in (SELECT WEIXINID FROM t_weixin_user_group WHERE groupid = '" + groupid + "')";
		}
		sql += getWhereSql(u);
		return findBy(sql, 1).toString();
	}

	@Override
	public List<User> getUserList(User u, String groupid, Integer page, Integer rows) {
		String sql = "select * from t_weixin_user where 1=1 ";
		if (groupid != null) {
			sql += " and WEIXINID in (SELECT WEIXINID FROM t_weixin_user_group WHERE groupid = '" + groupid + "')";
		}
		sql += getWhereSql(u);
		sql += " order by id desc limit ?,?";
		return find(User.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public boolean editGroup(Group g) {
		String sql = "update t_weixin_group set text = ? where id = ?";
		return update(sql, new Object[] { g.getText(), g.getId() });
	}

	@Override
	public String getUserNOCount(User u) {
		String sql = "SELECT count(*) FROM t_weixin_user WHERE WEIXINID NOT IN (SELECT WEIXINID FROM t_weixin_user_group)";
		sql += getWhereSql(u);
		return findBy(sql, 1).toString();
	}

	@Override
	public List<User> getUserNOList(User u, Integer page, Integer rows) {
		String sql = "SELECT * FROM t_weixin_user WHERE WEIXINID NOT IN (SELECT WEIXINID FROM t_weixin_user_group)";
		sql += getWhereSql(u);
		sql += " order by id desc limit ?,?";
		return find(User.class, sql, new Object[] { (page - 1) * rows, rows });
	}
	
	@Override
	public String getUserSystemCount(User u, String groupid) {
		String sql = "select count(*) from t_weixin_user where 1=1 ";
		if (groupid != null) {
			sql += " and WEIXINID in (SELECT WEIXINID FROM t_weixin_user_group_system WHERE groupid = '" + groupid + "')";
		}
		sql += getWhereSql(u);
		return findBy(sql, 1).toString();
	}

	@Override
	public List<User> getUserSystemList(User u, String groupid, Integer page, Integer rows) {
		String sql = "select * from t_weixin_user where 1=1 ";
		if (groupid != null) {
			sql += " and WEIXINID in (SELECT WEIXINID FROM t_weixin_user_group_system WHERE groupid = '" + groupid + "')";
		}
		sql += getWhereSql(u);
		sql += " order by id desc limit ?,?";
		return find(User.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	private String getWhereSql(User u) {
		String whereSql = "";
		if (u.getAccount() != null && !u.getAccount().equals("")) {
			whereSql += " and account = '" + u.getAccount() + "'";
		}
		if (u.getWeixinid() != null && !u.getWeixinid().equals("")) {
			whereSql += " and weixinid = '" + u.getWeixinid() + "'";
		}
		if (u.getBandingstatus() != null && !u.getBandingstatus().equals("") && !u.getBandingstatus().equals("-1")) {
			whereSql += " and BANDINGSTATUS = '" + u.getBandingstatus() + "'";
		}
		if (u.getOrderstatus() != null && !u.getOrderstatus().equals("") && !u.getOrderstatus().equals("-1")) {
			whereSql += " and ORDERSTATUS = '" + u.getOrderstatus() + "'";
		}
		if (u.getWeixin_sex() != null && !u.getWeixin_sex().equals("") && !u.getWeixin_sex().equals("-1")) {
			whereSql += " and WEIXIN_SEX = '" + u.getWeixin_sex() + "'";
		}
		if (u.getCrm_mobile() != null && !u.getCrm_mobile().equals("")) {
			whereSql += " and CRM_MOBILE = '" + u.getCrm_mobile() + "'";
		}
		if (u.getCrm_name() != null && !u.getCrm_name().equals("")) {
			whereSql += " and CRM_NAME = '" + u.getCrm_name() + "'";
		}
		if (u.getWeixin_nickname() != null && !u.getWeixin_nickname().equals("")) {
			whereSql += " and WEIXIN_NICKNAME = '" + u.getWeixin_nickname() + "'";
		}
		if (u.getBandingtime() != null && !u.getBandingtime().equals("")) {
			whereSql += " and BANDINGTIME >='" + u.getBandingtime() + "' ";
		}
		if (u.getBandingtime1() != null && !u.getBandingtime1().equals("")) {
			whereSql += " and BANDINGTIME <='" + u.getBandingtime1() + "' ";
		}
		if (u.getOrdertime() != null && !u.getOrdertime().equals("")) {
			whereSql += " and ORDERTIME >='" + u.getOrdertime() + "' ";
		}
		if (u.getOrdertime1() != null && !u.getOrdertime1().equals("")) {
			whereSql += " and ORDERTIME <='" + u.getOrdertime1() + "' ";
		}
		return whereSql;
	}

	@Override
	public boolean moveUserGroup(String groupid, String[] userid) {
		boolean b = false;
		String sql = "insert into t_weixin_user_group value(null,?,?,'0')";
		for (int i = 0; i < userid.length; i++) {
			String delSql = "delete from t_weixin_user_group where weixinid = ?";
			update(delSql, userid[i]);
			Object[] params = new Object[] { userid[i], groupid };
			b = insert(sql, params);
		}
		return b;
	}

	@Override
	public String getRtid(String groupid) {
		String sql = "select retid from t_weixin_group where id = ?";
		return findBy(sql, 1, groupid).toString();
	}

	@Override
	public String getUserNOSystemCount(User u) {
		String sql = "SELECT count(*) FROM t_weixin_user WHERE WEIXINID NOT IN (SELECT WEIXINID FROM t_weixin_user_group_system)";
		sql += getWhereSql(u);
		return findBy(sql, 1).toString();
	}

	@Override
	public List<User> getUserNOSystemList(User u, Integer page, Integer rows) {
		String sql = "SELECT * FROM t_weixin_user WHERE WEIXINID NOT IN (SELECT WEIXINID FROM t_weixin_user_group_system)";
		sql += getWhereSql(u);
		sql += " order by id desc limit ?,?";
		return find(User.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public String getUserCount48(User u) {
		String sql = "SELECT count(*) FROM t_weixin_user WHERE WEIXINID IN (select DISTINCT(weixinid) FROM t_weixin_usernoterlog WHERE addtime >= DATE_ADD(now(), Interval -48 hour))";
		sql += getWhereSql(u);
		return findBy(sql, 1).toString();
	}

	@Override
	public List<User> getUserList48(User u, Integer page, Integer rows) {
		String sql = "SELECT * FROM t_weixin_user WHERE WEIXINID IN (select DISTINCT(weixinid) FROM t_weixin_usernoterlog WHERE addtime >= DATE_ADD(now(), Interval -48 hour))";
		sql += getWhereSql(u);
		sql += " order by id desc limit ?,?";
		return find(User.class, sql, new Object[] { (page - 1) * rows, rows });
	}
}
