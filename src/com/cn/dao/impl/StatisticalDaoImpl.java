package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IStatisticalDao;
import com.cn.model.wx.Usernoterlog;
import com.cn.utils.DbUtils;

public class StatisticalDaoImpl extends DbUtils implements IStatisticalDao {

	@Override
	public String getDayCount(String status, String type, String account) {
		String sql = "";
		if (type.equals("gz")) {
			sql = "select COUNT(*) from t_weixin_user where TO_DAYS(ORDERTIME) = TO_DAYS(NOW()) and ACCOUNT = ? AND ORDERSTATUS = ?";
		} else {
			sql = "select COUNT(*) from t_weixin_user where TO_DAYS(BANDINGTIME) = TO_DAYS(NOW()) and ACCOUNT = ? AND BANDINGSTATUS = ?";
		}
		return findBy(sql, 1, new Object[] { account, status }).toString();
	}

	@Override
	public String getStatisticalCount(String status, String date, String type, String account) {
		String sql = "";
		if (type.equals("gz")) {
			sql = "select COUNT(*) from t_weixin_user where TO_DAYS(ORDERTIME) = TO_DAYS(?) and ACCOUNT = ? AND ORDERSTATUS = ?";
		} else {
			sql = "select COUNT(*) from t_weixin_user where TO_DAYS(BANDINGTIME) = TO_DAYS(?) and ACCOUNT = ? AND BANDINGSTATUS = ?";
		}
		return findBy(sql, 1, new Object[] { date, account, status }).toString();
	}

	@Override
	public String getDayCount() {
		String sql = "select count(id) from t_weixin_usernoterlog where TO_DAYS(addtime) = TO_DAYS(NOW())";
		return findBy(sql, 1).toString();
	}

	@Override
	public List<Usernoterlog> getUsernoterlog48() {
		String sql  = "SELECT DISTINCT(weixinid),COUNT(id) as count,name FROM t_weixin_usernoterlog WHERE addtime >= DATE_ADD(now(), Interval -48 hour)";
		return find(Usernoterlog.class, sql);
	}

}
