package com.wuchao.blog.comment.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wuchao.blog.comment.dao.intf.IarticleCommentDao;
import com.wuchao.blog.comment.po.IarticleComment;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;

@Repository("iarticleCommentDao")
public class IarticleCommentDaoImpl  extends HibernateDaoSupport implements IarticleCommentDao {
	@Autowired
	private SessionFactory sessionFactory;

	// 获取和当前线程绑定的Seesion
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	private HibernateTemplate getMyHibernateTemplate() {
		HibernateTemplate MyHibernateTemplate = getHibernateTemplate();
		MyHibernateTemplate.setCheckWriteOperations(false);
		return MyHibernateTemplate;
	}
	@Resource(name = "sessionFactory")
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);

	}
	@Override
	public void addIarticleComment(IarticleComment articleComment) throws DAOException {
		try {
			this.getHibernateTemplate().save(articleComment);
		}catch(Exception e) {
			throw new DAOException();
		}
	}

	@Override
	public IarticleComment getIarticleComment(int articleCommentId) throws DAOException {
		List list = null;
		IarticleComment articleComment = null;
		try {
	        list = this.getHibernateTemplate().find("from IarticleComment u where u.id=?",articleCommentId); 
		}catch(Exception e) {
			throw new DAOException();
		}
		if(list!=null && list.size()>0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				articleComment = (IarticleComment) iter.next();
			}
		}
		return articleComment;
	}

	@Override
	public List<IarticleComment> getIarticleCommentList(int articleId) throws DAOException {
		List<IarticleComment> list = null;
		try {
	        list = (List<IarticleComment>) this.getHibernateTemplate().find("from IarticleComment u where u.articleId=?",articleId); 
		}catch(Exception e) {
			throw new DAOException();
		}
		
		return list;
	}

}
