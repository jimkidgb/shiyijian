package com.cn.dao;

import java.util.List;

import com.cn.model.platform.Codelist;
import com.cn.model.shiyijian.Order;

public interface IOrderDaoSYJ {

	List<Order> getnewOrderList(Order order, Integer page, Integer rows,List<Codelist> codelist);

	String getnewOrderCount(Order order);

}
