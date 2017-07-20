package com.wuchao.blog.comment.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.blog.comment.bo.intf.IarticleCommentBo;
import com.wuchao.blog.comment.dao.intf.IarticleCommentDao;
import com.wuchao.blog.comment.po.IarticleComment;
import com.wuchao.blog.user.dao.intf.IuserDao;
import com.wuchao.utils.exception.DAOException;

@Service("iarticleCommentBo")
@Transactional
public class IarticleCommentBoImpl implements IarticleCommentBo {
	@Resource(name="iarticleCommentDao")
	public IarticleCommentDao iarticleCommentDao;
	
	@Override
	public void addIarticleComment(IarticleComment articleComment) throws DAOException {
		// TODO Auto-generated method stub
		iarticleCommentDao.addIarticleComment(articleComment);
	}

	@Override
	public IarticleComment getIarticleComment(int articleCommentId) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleCommentDao.getIarticleComment(articleCommentId);
	}

	@Override
	public List<IarticleComment> getIarticleCommentList(int articleId) throws DAOException {
		// TODO Auto-generated method stub
		return iarticleCommentDao.getIarticleCommentList(articleId);
	}

}
