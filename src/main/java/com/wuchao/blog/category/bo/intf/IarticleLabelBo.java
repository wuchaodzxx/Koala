package com.wuchao.blog.category.bo.intf;

import java.util.List;

import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.utils.exception.DAOException;

public interface IarticleLabelBo {
	public void addArticleLabel(IarticleLabel articleLabel)throws DAOException;
	public void updateArticleLabel(IarticleLabel articleLabel)throws DAOException;
	public void deletArticleLabel(int articleLabelId)throws DAOException;
	public IarticleLabel getArticleLabel(int articleLabelId)throws DAOException;
	public List<IarticleLabel> getIarticleLabelListByUserId(int userId)throws DAOException;

}
