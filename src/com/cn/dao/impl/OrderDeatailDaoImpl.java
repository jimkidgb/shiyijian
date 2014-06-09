package com.cn.dao.impl;


import com.cn.dao.IOrderDetailDao;
import com.cn.model.shiyijian.OrderDetail;
import com.cn.utils.DbUtils;

public class OrderDeatailDaoImpl extends DbUtils implements IOrderDetailDao{

	@Override
	public OrderDetail findbyOrderNo(String order_no) {
		String sql = "select * from t_client_orderdetail where order_no = ?";
		return findFirst(OrderDetail.class, sql, order_no);
	}

}
