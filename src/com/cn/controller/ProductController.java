package com.cn.controller;

import java.io.File;
import java.util.UUID;

import com.cn.config.JfinalConfig;
import com.cn.dao.IOrderDao;
import com.cn.dao.IProductDao;
import com.cn.dao.impl.OrderDaoImpl;
import com.cn.dao.impl.ProductDaoImpl;
import com.cn.lang.FileSizeMaxException;
import com.cn.lang.FileTypeException;
import com.cn.lang.FileWriteException;
import com.cn.model.product.Images;
import com.cn.model.product.Order;
import com.cn.model.product.Product;
import com.cn.model.product.Values;
import com.cn.utils.CookieUtil;
import com.cn.utils.FileUploadUtils;
import com.cn.utils.StringUtils;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;

public class ProductController extends Controller {
	private IProductDao productDao = new ProductDaoImpl();
	private String msg = "error";

	public void addProduct() {
		Product p = new Product();
		p.setAdder(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		p.setProductid(UUID.randomUUID().toString().replaceAll("-", ""));
		p.setCfid(getPara("cfid"));
		p.setCfname(getPara("cfname"));
		p.setContent(getPara("content"));
		p.setDetail(getPara("detail"));
		p.setPicurl(getPara("picurl"));
		p.setSort(getPara("sort"));
		p.setTitle(getPara("title"));
		p.setO1(getPara("o1"));
		p.setO2(getPara("o2"));
		p.setO3(getPara("o3"));
		p.setO4(getPara("o4"));
		p.setO5(getPara("o5"));
		if (productDao.addProduct(p)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editProduct() {
		Product p = new Product();
		p.setCfid(getPara("cfid"));
		p.setCfname(getPara("cfname"));
		p.setContent(getPara("content"));
		p.setDetail(getPara("detail"));
		p.setPicurl(getPara("picurl"));
		p.setSort(getPara("sort"));
		p.setTitle(getPara("title"));
		p.setId(getPara("id"));
		p.setO1(getPara("o1"));
		p.setO2(getPara("o2"));
		p.setO3(getPara("o3"));
		p.setO4(getPara("o4"));
		p.setO5(getPara("o5"));
		if (productDao.editProduct(p)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeProduct() {
		String id = getPara("id");
		if (productDao.removeProduct(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getProductList() {
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		Product p = new Product();
		p.setCfid(getPara("cfid"));
		p.setProductid(getPara("productid"));
		p.setTitle(getPara("title"));
		DataGrid<Product> dg = new DataGrid<Product>();
		dg.setRows(productDao.getProductList(p, page, rows));
		dg.setTotal(productDao.getProductCount(p));
		renderJson(dg);
	}

	public void addImages() {
		FileUploadUtils fuu = new FileUploadUtils();
		String uplodpath = StringUtils.getPath(JfinalConfig.PRODUCTUPLOADFILE);
		String filename = "";
		try {
			filename = fuu.uploadFile(getRequest(), uplodpath, 1048576, ".jpg,.png");
			msg = JfinalConfig.PRODUCTURL + filename;
			Images i = new Images();
			i.setFilepath(uplodpath + File.separator + filename);
			i.setPicurl(msg);
			i.setProductid(getPara("productid"));
			if (productDao.addImages(i)) {
				msg = "success";
			}
		} catch (FileSizeMaxException e) {
			e.printStackTrace();
			msg = "MAX";
		} catch (FileWriteException e) {
			e.printStackTrace();
			msg = "RNAME";
		} catch (FileTypeException e) {
			e.printStackTrace();
			msg = "TYPE";
		}
		renderText(msg);
	}

	public void removeImages() {
		String id = getPara("id");
		if (productDao.removeImages(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getImages() {
		String productid = getPara("productid");
		renderJson(productDao.getImagesList(productid));
	}

	public void getValues() {
		String productid = getPara("productid");
		Values v = productDao.getValues(productid);
		if (v != null) {
			renderJson(v);
		} else {
			renderText("");
		}
	}

	public void configValues() {
		String id = getPara("id");
		Values v = new Values();
		v.setId(getPara("id"));
		v.setPoint(getPara("point"));
		v.setProductid(getPara("productid"));
		v.setCount(getPara("count"));
		v.setPrice(getPara("price"));
		v.setLimitnum(getPara("limitnum"));
		v.setAct(getString(getParaValues("act")));
		v.setPerson(getString(getParaValues("person")));
		v.setConsumption(getString(getParaValues("consumption")));
		v.setAdder(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		if (id != null && !id.equals("")) {
			if (productDao.editValues(v)) {
				msg = "success";
			}
		} else {
			if (productDao.addValues(v)) {
				msg = "success";
			}
		}
		renderText(msg);
	}
	private String getString(String[] str){
		StringBuffer sb = new StringBuffer();
		if(str!=null){
			sb.append("[");
			for (int i = 0; i < str.length; i++) {
				sb.append("'"+str[i]+"',");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		return sb.toString();
	}
	public void getOrderList() {
		IOrderDao orderDao = new OrderDaoImpl();
		Order o = new Order();
		o.setAccount(getPara("account"));
		o.setConfirmtime(getPara("confirmtime"));
		o.setConfirmtime1(getPara("confirmtime1"));
		o.setOrderid(getPara("orderid"));
		o.setOrdertime(getPara("ordertime"));
		o.setOrdertime1(getPara("ordertime1"));
		o.setProductid(getPara("productid"));
		o.setStatus(getPara("status"));
		o.setWeixinid(getPara("weixinid"));
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		DataGrid<Order> dg = new DataGrid<Order>();
		dg.setRows(orderDao.getOrderList(o, page, rows));
		dg.setTotal(orderDao.getOrderCount(o));
		renderJson(dg);
	}

	public void getTypeList() {
		String tablename = getPara("tablename");
		renderJson(productDao.getTypeList(tablename));
	}
}
