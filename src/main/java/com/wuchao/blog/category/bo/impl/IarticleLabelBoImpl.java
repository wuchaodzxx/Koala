package com.wuchao.blog.category.bo.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.blog.article.dao.intf.IarticleDao;
import com.wuchao.blog.category.bo.intf.IarticleLabelBo;
import com.wuchao.blog.category.dao.intf.IarticleLabelDao;
import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.utils.exception.DAOException;

@Service("iarticleLabelBo")
@Transactional
public class IarticleLabelBoImpl implements IarticleLabelBo {
	@Resource(name="iarticleLableDao")
	public IarticleLabelDao iarticleLableDao;
	
	@Override
	public void addArticleLabel(IarticleLabel articleLabel) throws DAOException {
		iarticleLableDao.addArticleLabel(articleLabel);
	}

	@Override
	public void updateArticleLabel(IarticleLabel articleLabel) throws DAOException {
		iarticleLableDao.updateArticleLabel(articleLabel);
	}

	@Override
	public void deletArticleLabel(int articleLabelId) throws DAOException {
		iarticleLableDao.deletArticleLabel(articleLabelId);
	}

	@Override
	public IarticleLabel getArticleLabel(int articleLabelId) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleLableDao.getArticleLabel(articleLabelId);
	}

	@Override
	public List<IarticleLabel> getIarticleLabelListByUserId(int userId) throws DAOException {
		List<IarticleLabel> list = iarticleLableDao.getIarticleLabelListByUserId(userId);
		if(list==null || list.size()==0) return list;
		//下面计算每个IarticleLabel中Iarticle的个数
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			IarticleLabel articleLabel = (IarticleLabel) iter.next();
			articleLabel.setArticleNum(iarticleLableDao.getArticleNumsByArticleLabelId(articleLabel.getId()));
		}
		return list;
	}

}
