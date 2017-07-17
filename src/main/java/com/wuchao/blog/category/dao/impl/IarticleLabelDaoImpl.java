package com.wuchao.blog.category.dao.impl;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.blog.category.dao.intf.IarticleLabelDao;
import com.wuchao.blog.category.po.IarticleLabel;
import com.wuchao.blog.user.controller.LoginController;
import com.wuchao.utils.exception.DAOException;

@Repository("iarticleLableDao")
public class IarticleLabelDaoImpl extends HibernateDaoSupport implements IarticleLabelDao {
private static Logger log = Logger.getLogger(LoginController.class);  
	
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
	public void addArticleLabel(IarticleLabel articleLabel) throws DAOException {
		try {
			this.getHibernateTemplate().save(articleLabel);
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
	}

	@Override
	public void updateArticleLabel(IarticleLabel articleLabel) throws DAOException {
		try {
			this.getHibernateTemplate().update(articleLabel);
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
	}

	@Override
	public void deletArticleLabel(int articleLabelId) throws DAOException {
		List list = null;
		IarticleLabel articleLabel = null;
		try {
	        list = this.getHibernateTemplate().find("from IarticleLabel u where u.id=?",articleLabelId); 
	        if(list!=null && list.size()>0) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					articleLabel = (IarticleLabel) iter.next();
				}
			}
			if(articleLabel!=null) {
				this.getHibernateTemplate().delete(articleLabel);
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
	}

	@Override
	public IarticleLabel getArticleLabel(int articleLabelId) throws DAOException {
		List list = null;
		IarticleLabel articleLabel = null;
		try {
	        list = this.getHibernateTemplate().find("from IarticleLabel u where u.id=?",articleLabelId); 
	        if(list!=null && list.size()>0) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					articleLabel = (IarticleLabel) iter.next();
				}
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
		return articleLabel;
	}

	@Override
	public List<IarticleLabel> getIarticleLabelListByUserId(int userId) throws DAOException {
		List<IarticleLabel> list = null;
		try {
	        list = (List<IarticleLabel>) this.getHibernateTemplate().find("from IarticleLabel u where u.userId=?",userId); 
	       
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
		return list;
	}
	@Override
	public int getArticleNumsByArticleLabelId(int articleLabelId) throws DAOException {
		// TODO Auto-generated method stub
		try {
	        String hql = "select count(*) from Iarticle as a where a.labelId="+articleLabelId;  
	        Query query =  getSession().createQuery(hql);  
	        Long lo=(Long)query.uniqueResult();  
	        Integer intge=new Integer(String.valueOf(lo));  
	        return intge;   
	        
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
	}

}
