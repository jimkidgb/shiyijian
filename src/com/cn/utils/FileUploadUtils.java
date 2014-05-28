package com.cn.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cn.lang.FileSizeMaxException;
import com.cn.lang.FileTypeException;
import com.cn.lang.FileWriteException;
public class FileUploadUtils {
	public String uploadFile(HttpServletRequest request, String dir,long sizeMax,String fileType) throws FileSizeMaxException,FileWriteException, FileTypeException {
		String filename = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(sizeMax);
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
						if (fileType.indexOf(type) == -1) {
							throw new FileTypeException();
						}
						File fileOnServer = new File(dir, System.currentTimeMillis()+ type);
						try {
							item.write(fileOnServer);
						} catch (Exception e) {
							e.printStackTrace();
							throw new FileWriteException();
						}
						filename = fileOnServer.getName();
					}
				}
			}
		} else {
			System.out.println("the enctype must be multipart/form-data");
		}
		return filename;
	}
	
	public String uploadFile(HttpServletRequest request, String dir,long sizeMax) throws FileSizeMaxException,FileWriteException, FileTypeException {
		String filename = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(sizeMax);
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
						File fileOnServer = new File(dir, filename);
						try {
							item.write(fileOnServer);
						} catch (Exception e) {
							e.printStackTrace();
							throw new FileWriteException();
						}
						filename = fileOnServer.getName();
					}
				}
			}
		} else {
			System.out.println("the enctype must be multipart/form-data");
		}
		return filename;
	}
}
