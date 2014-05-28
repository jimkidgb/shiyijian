package com.cn.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cn.config.JfinalConfig;
import com.cn.dao.IActDao;
import com.cn.dao.impl.ActDaoImpl;
import com.cn.model.wx.Act;
import com.cn.model.wx.Export;
import com.cn.utils.CookieUtil;
import com.cn.utils.ExportUtils;
import com.cn.utils.FileUtil;
import com.cn.viewmodel.DataGrid;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;

public class ActController extends Controller {
	private IActDao actDao = new ActDaoImpl();
	private String msg = "error";

	public void addAct() {
		Act a = new Act();
		a.setEnddate(getPara("enddate"));
		a.setStartdate(getPara("startdate"));
		a.setName(getPara("name"));
		a.setUrl(getPara("url"));
		a.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (actDao.addAct(a)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editAct() {
		Act a = new Act();
		a.setEnddate(getPara("enddate"));
		a.setStartdate(getPara("startdate"));
		a.setName(getPara("name"));
		a.setUrl(getPara("url"));
		a.setId(getPara("id"));
		a.setModifier(CookieUtil.getCookieByName(getRequest(),CookieUtil.USERNAME));
		if (actDao.editAct(a)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeAct() {
		String id = getPara("id");
		String enddate = getPara("enddate");
		String startdate = getPara("startdate");
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date d0 = new Date();
			Date d1 = df.parse(startdate);
			Date d2 = df.parse(enddate);
			if (d0.getTime() >= d1.getTime() && d0.getTime() <= d2.getTime()) {
				msg = "removeNo";
			} else {
				if (actDao.removeAct(id)) {
					msg = "success";
				}
			}
		} catch (Exception e) {
			msg = "error";
		}
		renderText(msg);
	}

	public void getActList() {
		Act a = new Act();
		a.setName(getPara("name"));
		Integer page = Integer.parseInt(getPara("page"));
		Integer rows = Integer.parseInt(getPara("rows"));
		DataGrid<Act> dg = new DataGrid<Act>();
		dg.setRows(actDao.getActList(a, page, rows));
		dg.setTotal(actDao.getActCount(a));
		renderJson(dg);
	}

	public void editStatus() {
		String id = getPara("id");
		String status = getPara("status").equals("0") ? "1" : "0";
		if (actDao.editStatus(id, status)) {
			msg = "success" + status;
		}
		renderText(msg);
	}
	public void addSQL(){
		msg = "SQL脚本执行失败";
		String time = System.currentTimeMillis()+"";
		String inPath = getRequest().getSession().getServletContext().getRealPath("/")+"file"+File.separator+"sql"+File.separator+time+".sql";
		String outPath = getRequest().getSession().getServletContext().getRealPath("/")+"file"+File.separator+"sql"+File.separator+time+".out";
		String sql = getPara("sql");
		FileUtil fu = new FileUtil();
		if(fu.fileWriter(sql, inPath)){
			actDao.addSQL(inPath, outPath);
			msg = fu.fileReader(outPath);
		}
		renderText(msg);
	}
	public void getExportList(){
		renderJson(actDao.getExport());
	}
	
	public void getDataJson(){
		String id = getPara("id");
		Export e = actDao.getExport(id);
		String dataJson = "";
		if(e!=null){
			String sql = e.getPresql();
			String column = actDao.getColumnJson(sql);
			String data = actDao.getDataJson(sql);
			dataJson = "{\"rows\":"+data+",\"columns\":["+column+"]}";
		}
		renderJson(dataJson);
	}
	public void exportFile(){
		String id = getPara("id");
		Export e = actDao.getExport(id);
		ExportUtils eu = new ExportUtils();
		String path = PathKit.getWebRootPath() + File.separator + "file" + File.separator + "excelTEM" + File.separator +System.currentTimeMillis()+ ".xlsx";
		eu.exportExcel(e.getExpsql(), JfinalConfig.EXPORTCOUNT, path);
		File file =new File(path);
		if(file.exists()) {
			renderFile(file);
		}
	}
}
