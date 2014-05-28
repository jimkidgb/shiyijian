package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.IOrderDao;
import com.cn.model.product.Order;
import com.cn.utils.DbUtils;

public class OrderDaoImpl extends DbUtils implements IOrderDao {

	@Override
	public List<Order> getOrderList(Order o, Integer page, Integer rows) {
		String sql = "select * from t_order where 1=1 ";
		if (o.getOrderid() != null && !o.getOrderid().equals("")) {
			sql += " and orderid = '" + o.getOrderid() + "' ";
		}
		if (o.getWeixinid() != null && !o.getWeixinid().equals("")) {
			sql += " and weixinid = '" + o.getWeixinid() + "' ";
		}
		if (o.getProductid() != null && !o.getProductid().equals("")) {
			sql += " and productid = '" + o.getProductid() + "' ";
		}
		if (o.getAccount() != null && !o.getAccount().equals("")) {
			sql += " and account = '" + o.getAccount() + "' ";
		}
		if (o.getStatus() != null && !o.getStatus().equals("")) {
			sql += " and status = '" + o.getStatus() + "' ";
		}
		if (o.getOrdertime() != null && !o.getOrdertime().equals("")) {
			sql += " and ordertime >='" + o.getOrdertime() + "' ";
		}
		if (o.getOrdertime1() != null && !o.getOrdertime1().equals("")) {
			sql += " and ordertime <='" + o.getOrdertime1() + "' ";
		}
		if (o.getConfirmtime() != null && !o.getConfirmtime().equals("")) {
			sql += " and confirmtime >='" + o.getConfirmtime() + "' ";
		}
		if (o.getConfirmtime1() != null && !o.getConfirmtime1().equals("")) {
			sql += " and confirmtime <='" + o.getConfirmtime1() + "' ";
		}
		sql += " order by id desc limit ?,?";
		return find(Order.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public String getOrderCount(Order o) {
		String sql = "select count(*) from t_order where 1=1 ";
		if (o.getOrderid() != null && !o.getOrderid().equals("")) {
			sql += " and orderid = '" + o.getOrderid() + "' ";
		}
		if (o.getWeixinid() != null && !o.getWeixinid().equals("")) {
			sql += " and weixinid = '" + o.getWeixinid() + "' ";
		}
		if (o.getProductid() != null && !o.getProductid().equals("")) {
			sql += " and productid = '" + o.getProductid() + "' ";
		}
		if (o.getAccount() != null && !o.getAccount().equals("")) {
			sql += " and account = '" + o.getAccount() + "' ";
		}
		if (o.getStatus() != null && !o.getStatus().equals("")) {
			sql += " and status = '" + o.getStatus() + "' ";
		}
		if (o.getOrdertime() != null && !o.getOrdertime().equals("")) {
			sql += " and ordertime >='" + o.getOrdertime() + "' ";
		}
		if (o.getOrdertime1() != null && !o.getOrdertime1().equals("")) {
			sql += " and ordertime <='" + o.getOrdertime1() + "' ";
		}
		if (o.getConfirmtime() != null && !o.getConfirmtime().equals("")) {
			sql += " and confirmtime >='" + o.getConfirmtime() + "' ";
		}
		if (o.getConfirmtime1() != null && !o.getConfirmtime1().equals("")) {
			sql += " and confirmtime <='" + o.getConfirmtime1() + "' ";
		}
		return findBy(sql, 1).toString();
	}
}
