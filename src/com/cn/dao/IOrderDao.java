package com.cn.dao;

import java.util.List;

import com.cn.model.product.Order;

public interface IOrderDao {
	public List<Order> getOrderList(Order o, Integer page, Integer rows);
	public String getOrderCount(Order o);
}
