package com.cn.controller;


import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.dao.IAccDao;
import com.cn.dao.ICodelistDao;
import com.cn.dao.IPicDao;
import com.cn.dao.impl.AccDaoImpl;
import com.cn.dao.impl.CodelistDaoImpl;
import com.cn.dao.impl.PicDaoImpl;
import com.cn.model.platform.Codelist;
import com.cn.model.shiyijian.Acc;
import com.cn.model.shiyijian.Pic;
import com.cn.utils.Constant;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class AccController extends Controller {
	private IAccDao accDao = new AccDaoImpl();
	private String msg = "error";
	private ICodelistDao codelsitDao = new CodelistDaoImpl();
	private IPicDao picDao =  new PicDaoImpl();
	
	public void addAcc(){
		Acc acc= new Acc();
		acc.setAccess_sku(getPara("access_sku"));
		acc.setAccess_name(getPara("access_name"));
		acc.setAccess_type(getPara("access_type"));
		acc.setStatus(getPara("status"));
		acc.setAccess_pic(getPara("access_pic"));
		acc.setAccess_price(getPara("access_price"));
		acc.setAccess_info(getPara("access_info"));
		if(!accDao.checkSku(acc)){
			if (accDao.addAcc(acc)) {
				msg = "success";
			}
		}else{
			msg = "alreadyin";
		}	
		renderText(msg);
	}
	
	public void getAccList(){
		List<Codelist> codelist = codelsitDao.getCodeByType(Constant.CODE_TYPE_ACC);
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		Acc a = new Acc();
		a.setAccess_sku(getPara("s_access_sku"));
		a.setAccess_name(getPara("s_access_name"));
		a.setAccess_type(getPara("s_access_type"));
		a.setStatus(getPara("s_status"));
		
		DataGrid<Acc> dg = new DataGrid<Acc>();
		List<Acc> rowslist = accDao.getAccList(a, page, rows,codelist);
		for(Acc ro : rowslist){			
			Pic p = picDao.getpic(ro.getAccess_pic());
			if(p!=null){
				ro.setRealpicpath(JfinalConfig.DOMAIN  + "\\" + p.getId() + "." + p.getPictype());
			}
		}
		dg.setRows(rowslist);
		dg.setTotal(accDao.getAccCount(a));
		renderJson(dg);
	}
	
	public void editAcc(){
		Acc acc= new Acc();
		acc.setId(getPara("id"));
		acc.setAccess_sku(getPara("access_sku"));
		acc.setAccess_name(getPara("access_name"));
		acc.setAccess_type(getPara("access_type"));
		acc.setStatus(getPara("status"));
		acc.setAccess_pic(getPara("access_pic"));
		acc.setAccess_price(getPara("access_price"));
		acc.setAccess_info(getPara("access_info"));
		if(!accDao.checkSku(acc)){
			if (accDao.editAcc(acc)) {
				msg = "success";
			}
		}else{
			msg = "alreadyin";
		}	
		renderText(msg);	
	}
	
	public void delAcc(){
		String id = getPara("id");
		if (accDao.delAcc(id)) {
			msg = "success";
		}
		renderText(msg);	
	}
	
	public void statuschange(){
		String id = getPara("id");
		String status = getPara("status");
		status = status.equals("0") ? "1" : "0";
		if (accDao.updateStatus(id, status)) {
			if (status.equals("1")) {
				msg = "success1";
			} else {
				msg = "success0";
			}
		}
		renderText(msg);
		
	}
	
}
