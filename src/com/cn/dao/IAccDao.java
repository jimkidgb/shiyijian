package com.cn.dao;

import java.util.List;

import com.cn.model.platform.Codelist;
import com.cn.model.shiyijian.Acc;

public interface IAccDao {
  public boolean addAcc(Acc acc);
  public boolean checkSku(Acc acc);
  public List<Acc> getAccList(Acc a, Integer page, Integer rows,List<Codelist> codelist);
  public String getAccCount(Acc a);
  public boolean editAcc(Acc acc);
  public boolean delAcc(String id);
  public boolean updateStatus(String id, String status);
  
}
