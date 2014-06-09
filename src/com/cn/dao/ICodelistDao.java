package com.cn.dao;

import java.util.List;

import com.cn.model.platform.Codelist;



public interface ICodelistDao {

	public List<Codelist> getCodeByType(String type);

}
