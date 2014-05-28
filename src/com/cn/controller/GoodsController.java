package com.cn.controller;


import com.cn.dao.IMicroDao;
import com.cn.dao.impl.GoodsDaoImpl;
import com.cn.model.type.Item;
import com.cn.model.wx.Micro;
import com.cn.utils.CookieUtil;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class GoodsController extends Controller {
	private IMicroDao microDao = new GoodsDaoImpl();
	private String msg = "error";

	public void addItem() {
		Item i = new Item();
		i.setDescription(getPara("description"));
		i.setMenuid(getPara("menuid"));
		i.setTitle(getPara("title"));
		i.setPicurl(getPara("picurl"));
		i.setUrl(getPara("url"));
		i.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (microDao.addItem(i)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void configMicro() {
		Micro i = new Micro();
		i.setMenuid(getPara("menuid"));
		i.setTitle(getPara("title"));
		i.setPicurl(getPara("picurl"));
		i.setId(getPara("id"));
		i.setMenuid(getPara("menuid"));
		i.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (microDao.configMicro(i)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editItem() {
		Item i = new Item();
		i.setDescription(getPara("description"));
		i.setMenuid(getPara("menuid"));
		i.setTitle(getPara("title"));
		i.setPicurl(getPara("picurl"));
		i.setUrl(getPara("url"));
		i.setId(getPara("id"));
		i.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (microDao.editItem(i)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getItemList() {
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		DataGrid<Item> dg = new DataGrid<Item>();
		dg.setTotal(microDao.getItemCount());
		dg.setRows(microDao.getItemList(page, rows));
		renderJson(dg);
	}

	public void removeItem() {
		String id = getPara("id");
		if (microDao.removeItem(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getMicro() {
		String menuid = getPara("id");
		Micro m =microDao.getMicro(menuid);
		if(m!=null){
			renderJson(m);
		}else{
			renderText("");
		}
	}
}
