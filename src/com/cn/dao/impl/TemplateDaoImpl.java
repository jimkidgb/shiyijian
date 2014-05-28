package com.cn.dao.impl;

import java.util.List;

import com.cn.dao.ITemplateDao;
import com.cn.model.wx.Template;
import com.cn.utils.DbUtils;

public class TemplateDaoImpl extends DbUtils implements ITemplateDao {

	@Override
	public boolean addTemplate(Template t) {
		String sql = "insert into t_weixin_templatemsg values(null,?,?,?,now(),?)";
		Object[] params = new Object[] { t.getTemplateid(),
				t.getTemplatename(), t.getTemplatecontent(), t.getOffer() };
		return insert(sql, params);
	}

	@Override
	public boolean editTemplate(Template t) {
		String sql = "update t_weixin_templatemsg set template_id=?,template_name=?,template_content=? where id = ?";
		Object[] params = new Object[] { t.getTemplateid(),
				t.getTemplatename(), t.getTemplatecontent(), t.getId() };
		return update(sql, params);
	}

	@Override
	public String getTemplateCount() {
		String sql = "select count(*) from t_weixin_templatemsg";
		return findBy(sql, 1).toString();
	}

	@Override
	public List<Template> getTemplateList(Integer page, Integer rows) {
		String sql = "select id,TEMPLATE_ID as TEMPLATEID,TEMPLATE_NAME as TEMPLATENAME,TEMPLATE_CONTENT as TEMPLATECONTENT,ADDTIME,OFFER from t_weixin_templatemsg where 1=1";
		sql += " limit ?,?";
		return find(Template.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public boolean removeTemplate(String id) {
		String sql = "delete from t_weixin_templatemsg where id = ?";
		return update(sql, id);
	}

	@Override
	public Template getTemplate(String id) {
		String sql = "select id,TEMPLATE_ID as TEMPLATEID,TEMPLATE_NAME as TEMPLATENAME,TEMPLATE_CONTENT as TEMPLATECONTENT,ADDTIME,OFFER from t_weixin_templatemsg where id = ?";
		return findFirst(Template.class, sql,id);
	}
}
