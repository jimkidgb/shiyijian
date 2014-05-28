package com.cn.controller;

import java.io.File;

import com.cn.config.JfinalConfig;
import com.cn.dao.IActResourceDao;
import com.cn.dao.impl.ActResourceDaoImpl;
import com.cn.lang.FileSizeMaxException;
import com.cn.lang.FileTypeException;
import com.cn.lang.FileWriteException;
import com.cn.model.wx.ActResource;
import com.cn.utils.FileUploadUtils;
import com.cn.utils.StringUtils;
import com.jfinal.core.Controller;

public class ReController extends Controller {
	private IActResourceDao actDao = new ActResourceDaoImpl();
	private String msg = "error";

	public void uploadFile() {
		String actid = getPara("actid");
		String type = getPara("type");
		FileUploadUtils fuu = new FileUploadUtils();
		String name = "";
		if(type.equals("jsp")){
			name = "ACT"+actid;
		}else{
			name = "ACT"+actid+File.separator+type;
		}
		String uplodpath = StringUtils.getPath(JfinalConfig.ACTUPLOADPATH+File.separator+name);
		try {
			String s = fuu.uploadFile(getRequest(), uplodpath, 1048576);
			msg = name +File.separator+s;
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

	public void getActResource() {
		String actid = getPara("actid");
		renderJson(actDao.getActResourceList(actid));
	}

	public void addActResource() {
		ActResource a = new ActResource();
		a.setActid(getPara("actid"));
		a.setLocal(JfinalConfig.ACTUPLOADPATH + File.separator+ getPara("url"));
		a.setRemote(JfinalConfig.ACTURL + getPara("url").replaceAll("\\\\", "/"));
		a.setType(getPara("type"));
		if (actDao.addActResource(a)) {
			msg = "success";
		}
		renderText(msg);
	}

	public void removeActResource() {
		String id = getPara("id");
		String local = getPara("local");
		deleteFile(local);
		if (actDao.removeActResource(id)) {
			msg = "success";
		}
		renderText(msg);
	}

	private boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}
