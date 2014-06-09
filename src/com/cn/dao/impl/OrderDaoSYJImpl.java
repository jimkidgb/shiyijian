package com.cn.dao.impl;


import java.util.List;

import com.cn.dao.IOrderDaoSYJ;
import com.cn.model.platform.Codelist;
import com.cn.model.shiyijian.Order;
import com.cn.utils.DbUtils;

public class OrderDaoSYJImpl extends DbUtils implements IOrderDaoSYJ {

	@Override
	public List<Order> getnewOrderList(Order order, Integer page, Integer rows,List<Codelist> codelist) {
		String sql = "select * from t_client_order where 1=1 and order_status != -1 ";	
		if (order.getOrder_no() != null && !order.getOrder_no().equals("")) {
			sql += " and order_no like '%" + order.getOrder_no() + "%' ";
		}
		
		if (order.getOrder_status() != null && !order.getOrder_status().equals("") && !order.getOrder_status().equals("-2")) {
			sql += " and order_status = '" + order.getOrder_status() + "' ";
		}
		
		if(order.getStart_time()!= null && !order.getStart_time().equals("")){			
			sql += " and order_time > '" + order.getStart_time() + "' ";
		}
		
		if(order.getEnd_time()!= null && !order.getEnd_time().equals("")){			
			sql += " and order_time < '" + order.getEnd_time() + "' ";
		}
		
		sql += " order by id desc limit ?,?";	
		List<Order> orders =find(Order.class, sql, new Object[] { (page - 1) * rows, rows });
		 for(Order t : orders){
			 t.setShowtype(getcodevalue(codelist,t.getOrder_type()));	
			 String address = t.getOrder_city() + t.getOrder_district() + t.getOrder_address();
			 t.setShowaddress(address);
		 }	
		return orders;
	}

	@Override
	public String getnewOrderCount(Order order) {
		String sql = "select count(*) from t_client_order where 1=1 and order_status != -1 ";
		if (order.getOrder_no() != null && !order.getOrder_no().equals("")) {
			sql += " and order_no like '%" + order.getOrder_no() + "%' ";
		}
		if (order.getOrder_status() != null && !order.getOrder_status().equals("") && !order.getOrder_status().equals("-2")) {
			sql += " and order_status = '" + order.getOrder_status() + "' ";
		}
		if(order.getStart_time()!= null && !order.getStart_time().equals("")){			
			sql += " and order_time > '" + order.getStart_time() + "' ";
		}
		
		if(order.getEnd_time()!= null && !order.getEnd_time().equals("")){			
			sql += " and order_time < '" + order.getEnd_time() + "' ";
		}
		return findBy(sql, 1).toString();
	}

	public String getcodevalue(List<Codelist> codelist,String codeid){
		String flag = "未知";
		for(Codelist c : codelist){
			if(c.getCodeid().equals(codeid)){			
				flag = c.getCodevalue();
			}	
		}
		return flag;
	}
}
