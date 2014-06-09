package com.cn.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cn.config.JfinalConfig;
import com.cn.dao.IClassificationDao;
import com.cn.dao.ICodelistDao;
import com.cn.dao.IPicDao;
import com.cn.dao.impl.ClassificationDaoImpl;
import com.cn.dao.impl.CodelistDaoImpl;
import com.cn.dao.impl.PicDaoImpl;
import com.cn.lang.FileScaleException;
import com.cn.lang.FileSizeMaxException;
import com.cn.lang.FileTypeException;
import com.cn.lang.FileWriteException;
import com.cn.model.platform.Codelist;
import com.cn.model.product.Classification;
import com.cn.model.shiyijian.Pic;
import com.cn.utils.Constant;
import com.cn.utils.CookieUtil;
import com.cn.utils.FileUploadUtils;
import com.cn.utils.StringUtils;
import com.jfinal.core.Controller;

public class ClassificationController extends Controller {
	private IClassificationDao cDao = new ClassificationDaoImpl();
	private ICodelistDao codelsitDao = new CodelistDaoImpl();
	private IPicDao picDao = new PicDaoImpl();
	
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
	
	public void getClassAccList(){
		List<Codelist> temp = codelsitDao.getCodeByType(Constant.CODE_TYPE_ACC);
		for(Codelist t : temp){
			t.setId(t.getCodeid());
		}
		renderJson(temp);		
	}
	
	public void getClassOrderTypeList(){
		List<Codelist> temp = codelsitDao.getCodeByType(Constant.CODE_ORDER_TYPE);
		for(Codelist t : temp){
			t.setId(t.getCodeid());
		}
		renderJson(temp);		
	}
	
	public void getClassOrderStatusList(){
		List<Codelist> temp = codelsitDao.getCodeByType(Constant.CODE_ORDER_STATUS);
		for(Codelist t : temp){
			t.setId(t.getCodeid());
		}
		renderJson(temp);		
	}
	
	public void getClassStatusList(){
		List<Codelist> temp = codelsitDao.getCodeByType(Constant.CODE_TYPE_STATUS);
		for(Codelist t : temp){
			t.setId(t.getCodeid());
		}
		renderJson(temp);
	}
	
	public void uploadFileSYJ(){
		Date d = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String timepath = sdf.format(d);
		//String uplodpath = StringUtils.getPath(JfinalConfig.UPLOADFILEPATH  + "\\" + timepath + "\\");
		String uplodpath = StringUtils.getPath(JfinalConfig.UPLOADFILEPATH );
		String sizeMax = "40960";
		sizeMax = JfinalConfig.FILEMAXSIZE;
		HttpServletRequest request = getRequest();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String filename = "";
		String filetype = ".jpg,.png";
		
		String dataJson = "";
		String picurl = "";
		
		if (isMultipart) {
			try {		
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(Long.valueOf(sizeMax));
				List<FileItem> fileItems = null;
				try {
					fileItems = upload.parseRequest(request);
				} catch (FileUploadException e) {
					throw new FileSizeMaxException();
				}
				Iterator<FileItem> iter = fileItems.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (!item.isFormField()) {
						String fileName = item.getName();
						if (fileName != null) {
							File fullFile = new File(item.getName());
							filename = fullFile.getName();
							String type = filename.substring(filename.lastIndexOf("."), filename.length());
							if (filetype.indexOf(type.toLowerCase()) == -1) {
								throw new FileTypeException();
							}
												
							//判断长宽比例	
		                    try{ 
			                    BufferedImage buff = ImageIO.read(item.getInputStream()); 
			                    long Width = buff.getWidth() * 1L;
			                    long Height = buff.getHeight() * 1L;
			                  	            
			                    if(!scaleCheck(Width,Height)){
			                    	throw new FileScaleException();	
			                    }
		                    } catch(Exception e){
		                    	e.printStackTrace();
		                    	throw new FileScaleException();	                              	                    	
		                    }
		                    	                    					
							//先插数据库
		                    String id = picDao.addpic(timepath,type.toLowerCase());
		                    
							File fileOnServer = new File(uplodpath, id + type.toLowerCase());
							try {
								item.write(fileOnServer);
							} catch (Exception e) {
								e.printStackTrace();
								throw new FileWriteException();
							}
		                    msg = id;
		                    picurl = JfinalConfig.DOMAIN + id + type.toLowerCase();
						}
					}
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
			} catch (FileScaleException e){
				e.printStackTrace();
				msg = "SCALE";
			} catch (Exception e){
				e.printStackTrace();				
				msg = "ERRO";				
			}
			
		}else {
			System.out.println("the enctype must be multipart/form-data");
			msg = "FORM";
		}
		
		
		dataJson = "{\"msg\":\""+msg+"\",\"picurl\":\""+picurl+ "\"}";
		
		renderText(dataJson);
	}
	
	
	public  boolean scaleCheck(long width, long height) {
		boolean flag = false;
		long [] basicscale = new long[] {1/1,16/9,9/16};
		for(long t : basicscale){
			if(width/height == t){
				flag = true;
				break;
			}		
		}	
		return flag;
	}
	
}
