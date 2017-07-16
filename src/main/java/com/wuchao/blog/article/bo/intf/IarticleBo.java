package com.wuchao.blog.article.bo.intf;

import java.util.List;

import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.page.Page;

public interface IarticleBo {
	public void addIarticle(Iarticle article)throws DAOException;
	public Iarticle getIarticleById(int id)throws DAOException;
	public List<Iarticle> getIarticleByUserName(String username)throws DAOException;
	public Page<Iarticle> queryPageByUserName(String username,int offset, int length)throws DAOException;
	public void deleteIarticle(int id)throws DAOException;
	public int getAllRowCount(String username)throws DAOException;
	public List<Iarticle> getArticleListByArticleLabelId(int articleLabelId)throws DAOException;
}
