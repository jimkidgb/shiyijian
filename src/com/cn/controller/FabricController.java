package com.cn.controller;


import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.dao.IFabricDao;
import com.cn.dao.IPicDao;
import com.cn.dao.impl.FabricDaoImpl;
import com.cn.dao.impl.PicDaoImpl;
import com.cn.model.shiyijian.Fabric;
import com.cn.model.shiyijian.Pic;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class FabricController extends Controller {
	private IFabricDao fabDao = new FabricDaoImpl();
	private IPicDao picDao =  new PicDaoImpl();
	private String msg = "error";
	
	public void addfabric(){
		Fabric fab = new Fabric();
		fab.setSku(getPara("sku"));
		fab.setColor(getPara("color"));
		fab.setElement(getPara("element"));
		fab.setThickness(getPara("thickness"));
		fab.setPic(getPara("pic"));
		fab.setYarn(getPara("yarn"));
		fab.setIntro(getPara("intro"));
		fab.setStatus(getPara("status"));
		fab.setName(getPara("name"));
		fab.setPrice(getPara("price"));
		if(!fabDao.checkFab(fab)){
			if (fabDao.addFab(fab)) {
				msg = "success";
			}
		}else{
			msg = "alreadyin";
		}	
		renderText(msg);
	}
	
	
	public void editfabric(){
		Fabric fab = new Fabric();
		fab.setId(getPara("id"));
		fab.setSku(getPara("sku"));
		fab.setColor(getPara("color"));
		fab.setElement(getPara("element"));
		fab.setThickness(getPara("thickness"));
		fab.setPic(getPara("pic"));
		fab.setYarn(getPara("yarn"));
		fab.setIntro(getPara("intro"));
		fab.setStatus(getPara("status"));
		fab.setName(getPara("name"));
		fab.setPrice(getPara("price"));
		if(!fabDao.checkFab(fab)){
			if (fabDao.editFab(fab)) {
				msg = "success";
			}
		}else{
			msg = "alreadyin";
		}		
		renderText(msg);	
		
	}
	
	
	
	
	public void getFabList(){
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		Fabric fab = new Fabric();
		fab.setSku(getPara("s_sku"));
		fab.setName(getPara("s_name"));
		fab.setStatus(getPara("s_status"));
		DataGrid<Fabric> dg = new DataGrid<Fabric>();
		//转换图片的显示路径
		List<Fabric> rowslist = fabDao.getFabList(fab, page, rows);
		for(Fabric ro : rowslist){			
			Pic p = picDao.getpic(ro.getPic());
			if(p!=null){
				ro.setRealpicpath(JfinalConfig.DOMAIN  + "\\" + p.getId() + "." + p.getPictype());
			}
		}
		dg.setRows(rowslist);
		dg.setTotal(fabDao.getFabCount(fab));
		renderJson(dg);
	}
	
	
	public void delfabric(){
		String id = getPara("id");
		if (fabDao.delfabric(id)) {
			msg = "success";
		}
		renderText(msg);	
	}
	
	public void statuschange(){
		String id = getPara("id");
		String status = getPara("status");
		status = status.equals("0") ? "1" : "0";
		if (fabDao.updateStatus(id, status)) {
			if (status.equals("1")) {
				msg = "success1";
			} else {
				msg = "success0";
			}
		}
		renderText(msg);
		
	}
	
}
