package com.wuchao.blog.article.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.blog.article.bo.intf.IarticleBo;
import com.wuchao.blog.article.dao.intf.IarticleDao;
import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.utils.exception.DAOException;
import com.wuchao.utils.page.Page;

@Service("iarticleBo")
@Transactional
public class IarticleBoImpl implements IarticleBo {
	@Resource(name="iarticleDao")
	public IarticleDao iarticleDao;
	@Override
	public void addIarticle(Iarticle article) throws DAOException {
		iarticleDao.addIarticle(article);
	}

	@Override
	public Iarticle getIarticleById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleDao.getIarticleById(id);
	}

	@Override
	public List<Iarticle> getIarticleByUserName(String username) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleDao.getIarticleByUserName(username);
	}

	@Override
	public Page<Iarticle> queryPageByUserName(String username,int currentPage,int pageSize) throws DAOException {
		Page page = new Page();        
        //总记录数
        int allRow = iarticleDao.getAllRowCount(username);
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);  
        //分页查询结果集
        List<Iarticle> list = iarticleDao.queryPageByUserName(username,offset, pageSize); 

        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        
        return page;
	}

	@Override
	public void deleteIarticle(int id) throws DAOException {
		iarticleDao.deleteIarticle(id);
	}

	@Override
	public int getAllRowCount(String username) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleDao.getAllRowCount(username);
	}

	@Override
	public List<Iarticle> getArticleListByArticleLabelId(int articleLabelId) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleDao.getArticleListByArticleLabelId(articleLabelId);
	}

	@Override
	public void deletIarticlesByLabelId(int labelId) throws DAOException {
		iarticleDao.deletIarticlesByLabelId(labelId);
	}

	@Override
	public void updateArticle(Iarticle iarticle) throws DAOException {
		iarticleDao.updateArticle(iarticle);
	}

}
