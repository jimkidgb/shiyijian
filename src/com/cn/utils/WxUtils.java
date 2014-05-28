package com.cn.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.cn.config.JfinalConfig;
import com.cn.model.wx.Error;
import com.cn.model.wx.Wxmenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WxUtils {

	public String getMenuJson(List<Wxmenu> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"button\":[");
		for (Wxmenu wxmenu : list) {
			if(wxmenu.getPid().equals("0")){
				if(wxmenu.getEvent()==null){
					sb.append("{\"name\":\""+wxmenu.getName()+"\",\"sub_button\":[");
					for (Wxmenu w1 : list) {
						if(wxmenu.getId().equals(w1.getPid())){
							if(w1.getEvent().equals("view")){
								sb.append("{\"type\":\""+w1.getEvent()+"\",\"name\":\""+w1.getName()+"\",\"url\":\""+w1.getUrl()+"\"},");
							}else{
								sb.append("{\"type\":\""+w1.getEvent()+"\",\"name\":\""+w1.getName()+"\",\"key\":\""+w1.getKey()+"\"},");
							}
						}
					}
					sb.delete(sb.length()-1, sb.length());
					sb.append("]},");
				}else{
					if(wxmenu.getEvent().equals("view")){
						sb.append("{\"type\":\""+wxmenu.getEvent()+"\",\"name\":\""+wxmenu.getName()+"\",\"url\":\""+wxmenu.getUrl()+"\"},");
					}else{
						sb.append("{\"type\":\""+wxmenu.getEvent()+"\",\"name\":\""+wxmenu.getName()+"\",\"key\":\""+wxmenu.getKey()+"\"},");
					}
				}
			}
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]}");
		return sb.toString();
	}

	public Error getAccessToken(String appid, String appsecrt) {
		Error error = null;
		String url = JfinalConfig.ASSETSTOKENURL + "&appid=" + appid + "&secret=" + appsecrt;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String assetsToken = new String(jsonBytes, "UTF-8");
			Gson g = new Gson();
			error = g.fromJson(assetsToken, new TypeToken<Error>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}

	public Error createMenu(String menuJson, String assetsToken) {
		Error error = null;
		String url = JfinalConfig.CREATEMENUURL + assetsToken;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(menuJson.getBytes("UTF-8"));
			os.flush();
			os.close();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			Gson g = new Gson();
			error = g.fromJson(result, new TypeToken<Error>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}

	public Error removeMenu(String assetsToken) {
		Error error = null;
		String url = JfinalConfig.REMOVEMENUURL + assetsToken;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			Gson g = new Gson();
			error = g.fromJson(result, new TypeToken<Error>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}
	
	public Error createGroup(String assetsToken,String groupJson) {
		Error error = null;
		String url = JfinalConfig.CREATEMENUURL + assetsToken;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(groupJson.getBytes("UTF-8"));
			os.flush();
			os.close();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			Gson g = new Gson();
			error = g.fromJson(result, new TypeToken<Error>() {
			}.getType());
			if(error.getErrcode()==null || error.getErrcode().equals("")){
				String f = "\"id\":";
				String s = result.substring(result.indexOf(f)+f.length(), result.length());
				String s2 = s.substring(0, s.indexOf(",")).trim();
				error.setErrmsg(s2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}
	public static void main(String[] args) {
		String result = "{\"group\": {\"id\": 107,\"name\": \"test\"}}";
		String f = "\"id\":";
		String s = result.substring(result.indexOf(f)+f.length(), result.length());
		String s2 = s.substring(0, s.indexOf(","));
		System.out.println(s2);
	}
	public Error updateGroup(String assetsToken,String groupJson) {
		Error error = null;
		String url = JfinalConfig.CREATEMENUURL + assetsToken;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(groupJson.getBytes("UTF-8"));
			os.flush();
			os.close();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			Gson g = new Gson();
			error = g.fromJson(result, new TypeToken<Error>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}
}
