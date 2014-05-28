package com.cn.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StringUtils {
	public static String[] col = new String[] {"","A", "B", "C", "D", "E", "F",
		"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
		"T", "U", "V", "W", "X", "Y", "Z", "AB", "BC", "CD", "DE", "EF",
		"FG", "GH", "HI", "IJ", "JK", "KL", "LM", "MN", "NO", "OP", "PQ",
		"QR", "RS", "ST", "TU", "UV", "VW", "WX", "XY" };
	public static String getPath(String path) {
		String dir = path+File.separator+ File.separator;
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdirs();
		}
		return dir;
	}
	public static boolean getStrBoolean(String str){
		if(str!=null && !str.equals("")){
			return true;
		}
		return false;
	}
	public static boolean getObjBoolean(Object obj){
		if(obj!=null && !obj.equals("")){
			return true;
		}
		return false;
	}
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString.replaceAll("-", "");
	}

	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString.replaceAll(":", "");
	}

	public static String FormetFileSize(long size) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (size < 1024) {
			fileSizeString = df.format((double) size) + "B";
		} else if (size < 1048576) {
			fileSizeString = df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			fileSizeString = df.format((double) size / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) size / 1073741824) + "G";
		}
		return fileSizeString;
	}
	public static String getTdTAG(int size){
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		for (int i = 0; i <= size; i++) {
			sb.append("<td style='background-color:#EFEBDE;'>"+col[i]+"</td>");
		}
		sb.append("</tr>");
		return sb.toString();
	}
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static <T> List<T> getSubListPage(List<T> list, int skip,int pageSize) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int startIndex = (skip-1)*pageSize;
        int endIndex = skip * pageSize;
        if (startIndex > endIndex || startIndex > list.size()) {
            return null;
        }
        if (endIndex > list.size()) {
            endIndex = list.size();
        }
        return list.subList(startIndex, endIndex);
    }
}
