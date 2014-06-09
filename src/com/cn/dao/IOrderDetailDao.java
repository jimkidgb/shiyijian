package com.cn.dao;

import com.cn.model.shiyijian.OrderDetail;

public interface IOrderDetailDao {

	OrderDetail findbyOrderNo(String order_no);

}
