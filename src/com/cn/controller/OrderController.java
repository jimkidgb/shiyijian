package com.cn.controller;

import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.dao.ICodelistDao;
import com.cn.dao.IOrderDaoSYJ;
import com.cn.dao.IOrderDetailDao;
import com.cn.dao.impl.CodelistDaoImpl;
import com.cn.dao.impl.OrderDaoSYJImpl;
import com.cn.dao.impl.OrderDeatailDaoImpl;
import com.cn.model.platform.Codelist;
import com.cn.model.shiyijian.Fabric;
import com.cn.model.shiyijian.Order;
import com.cn.model.shiyijian.Pic;
import com.cn.model.shiyijian.OrderDetail;
import com.cn.utils.Constant;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class OrderController extends Controller{
	private IOrderDaoSYJ orderDaoSYJ = new OrderDaoSYJImpl();
	private ICodelistDao codelsitDao = new CodelistDaoImpl();
	private IOrderDetailDao orderdetailDao = new OrderDeatailDaoImpl();
	private String msg = "error";
	
	public void getNewOrderList(){
		List<Codelist> codelist = codelsitDao.getCodeByType(Constant.CODE_ORDER_TYPE);
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		Order order = new Order();
		order.setOrder_no(getPara("s_order_no"));
	    order.setStart_time(getPara("s_ordertime_start"));
	    order.setEnd_time(getPara("s_ordertime_end"));
		order.setOrder_status(getPara("s_order_status"));
	    DataGrid<Order> dg = new DataGrid<Order>();
		List<Order> rowslist = orderDaoSYJ.getnewOrderList(order, page, rows,codelist);
		dg.setRows(rowslist);
		dg.setTotal(orderDaoSYJ.getnewOrderCount(order));
		renderJson(dg);	
	}
	
	public void printForm(){
		//获得是哪个订单的
		String order_no = getPara(0);
		OrderDetail printdetail = orderdetailDao.findbyOrderNo(order_no);
		if(printdetail != null){
			setAttr("order_no", printdetail.getOrder_no());
			setAttr("order_counts",printdetail.getOrder_counts());//数量
			setAttr("perheight",printdetail.getPerheight());//身高
			setAttr("perweight",printdetail.getPerweight());  //体重
			setAttr("neck",printdetail.getNeck());          //领围
			setAttr("waistline",printdetail.getWaistline());  //腰围
			setAttr("shoulder",printdetail.getShoulder());    //臂长
			setAttr("bust",printdetail.getBust());       //胸围
			setAttr("long_sleeve",printdetail.getLong_sleeve()); //长袖长
			setAttr("cuff",printdetail.getCuff());       //袖口宽
			setAttr("arm", printdetail.getArm());         //臂围
			setAttr("shirtVersion",printdetail.getShirtVersion());  //版型
			setAttr("collarVersion",printdetail.getCollarVersion());  //领型
			setAttr("cuffVersion",printdetail.getCuffVersion());    //袖口
			setAttr("pocketVersion",printdetail.getPocketVersion()); //口袋
			setAttr("backVersion",printdetail.getBackVersion());  //后背
			setAttr("hemVersion",printdetail.getHemVersion());   //下摆
			setAttr("buttonVersion",printdetail.getButtonVersion());  //纽扣
			setAttr("figureVersion",printdetail.getFigureVersion());   //体型特征
			setAttr("abdominalVersion",printdetail.getAbdominalVersion()); //腰部特征
			setAttr("shoulderVersion",printdetail.getShirtVersion());  //肩部特征
			setAttr("front_pic",JfinalConfig.DOMAIN2 + printdetail.getFront_pic()); //正面照片
			setAttr("flank_pic",JfinalConfig.DOMAIN2 + printdetail.getFlank_pic());  //侧面照片
			setAttr("placketVersion",printdetail.getPlacketVersion()); //门襟
			setAttr("order_ownercaller",printdetail.getOrder_ownercaller());  //个性签名			
		}		
		renderJsp("/pages/order/print.jsp");
	}
}
