package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.ICodelistDao;
import com.cn.model.platform.Codelist;
import com.cn.utils.DbUtils;

public class CodelistDaoImpl extends DbUtils implements ICodelistDao{

	@Override
	public List<Codelist> getCodeByType(String type) {
		String sql = "select * from t_platform_codelist where status = 1 and codetype = ? order by sort asc";
		return find(Codelist.class, sql, new Object[] {type});
	}

}
