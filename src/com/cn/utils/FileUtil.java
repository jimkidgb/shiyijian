package com.cn.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public boolean fileWriter(String content, String filepath) {
		boolean b = false;
		File f = new File(filepath);
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write(content);
			fw.close();
			b = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	public String fileReader(String filePath) {
		StringBuffer sb = new StringBuffer();
		FileReader fd = null;
		try {
			fd = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fd);
			String row = null;
			while ((row = br.readLine()) != null) {
				sb.append(row+"\r\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
