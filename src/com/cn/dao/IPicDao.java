package com.cn.dao;

import com.cn.model.shiyijian.Pic;

public interface IPicDao {
   public String addpic(String uplodpath, String type);
   
   public Pic getpic(String id);
}
