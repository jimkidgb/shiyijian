package com.cn.controller;

import com.cn.config.JfinalConfig;
import com.cn.dao.IClassificationDao;
import com.cn.dao.impl.ClassificationDaoImpl;
import com.cn.lang.FileSizeMaxException;
import com.cn.lang.FileTypeException;
import com.cn.lang.FileWriteException;
import com.cn.model.product.Classification;
import com.cn.utils.CookieUtil;
import com.cn.utils.FileUploadUtils;
import com.cn.utils.StringUtils;
import com.jfinal.core.Controller;

public class ClassificationController extends Controller {
	private IClassificationDao cDao = new ClassificationDaoImpl();
	private String msg = "error";
	
	public void uploadFile() {
		FileUploadUtils fuu = new FileUploadUtils();
		String uplodpath = StringUtils.getPath(JfinalConfig.PRODUCTUPLOADFILE);
		try {
			msg = JfinalConfig.PRODUCTURL+ fuu.uploadFile(getRequest(), uplodpath, 1048576,".jpg,.png");
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
	public void addClassification() {
		Classification c = new Classification();
		c.setModifier(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		c.setName(getPara("name"));
		c.setPid(getPara("pid").equals("")?"0":getPara("pid"));
		c.setSort(getPara("sort"));
		c.setPicurl(getPara("picurl"));
		if (cDao.addSort(c)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void editClassification() {
		Classification c = new Classification();
		c.setModifier(CookieUtil.getCookieByName(getRequest(), CookieUtil.USERNAME));
		c.setName(getPara("name"));
		c.setPid(getPara("pid").equals("")?"0":getPara("pid"));
		c.setSort(getPara("sort"));
		c.setPicurl(getPara("picurl"));
		c.setId(getPara("id"));
		if (cDao.editSort(c)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeClassification() {
		String id = getPara("id");
		if (cDao.removeSort(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void getClassificationList() {
		renderJson(cDao.getSortLisr());
	}
}
