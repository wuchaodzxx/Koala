package com.wuchao.blog.comment.dao.intf;

import java.util.List;

import com.wuchao.blog.comment.po.IarticleComment;
import com.wuchao.utils.exception.DAOException;

public interface IarticleCommentDao {
	public void addIarticleComment(IarticleComment articleComment) throws DAOException;
	public IarticleComment getIarticleComment(int articleCommentId) throws DAOException;
	public List<IarticleComment> getIarticleCommentList(int articleId) throws DAOException;
}
