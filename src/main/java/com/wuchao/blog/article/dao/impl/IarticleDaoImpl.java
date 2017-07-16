package com.wuchao.blog.article.dao.impl;

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

import com.wuchao.blog.article.dao.intf.IarticleDao;
import com.wuchao.blog.article.po.Iarticle;
import com.wuchao.blog.user.controller.LoginController;
import com.wuchao.utils.exception.DAOException;
@Repository("iarticleDao")
public class IarticleDaoImpl extends HibernateDaoSupport implements IarticleDao {
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
	public void addIarticle(Iarticle article) throws DAOException {
		try {
			this.getHibernateTemplate().save(article);
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
	}

	@Override
	public Iarticle getIarticleById(int id) throws DAOException {
		List list = null;
		Iarticle article = null;
		try {
	        list = this.getHibernateTemplate().find("from Iarticle u where u.id=?",id); 
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
		if(list!=null && list.size()>0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				article = (Iarticle) iter.next();
			}
		}
		return article;
	}

	@Override
	public List<Iarticle> getIarticleByUserName(String username) throws DAOException {
		List<Iarticle> list = null;
		try {
	        list = (List<Iarticle>) this.getHibernateTemplate().find("from Iarticle u where u.username=? order by u.modifyDate desc",username); 
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}	
		return list;
	}

	//分页查询
	@Override
	public List<Iarticle> queryPageByUserName(String username,int offset, int length) throws DAOException {
		List<Iarticle>  list = null;
		try {
			 Query query = getSession().createQuery("from Iarticle a where a.username="+"'"+username+"'"+"order by a.modifyDate desc");
			 query.setFirstResult(offset);
	         query.setMaxResults(length);
	         list = query.list();
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
		return list;
	}

	@Override
	public void deleteIarticle(int id) throws DAOException {
		List list = null;
		Iarticle article = null;
		try {
	        list = this.getHibernateTemplate().find("from Iarticle u where u.id=?",id); 
	        if(list!=null && list.size()>0) {
				for (Iterator iter = list.iterator(); iter.hasNext();) {
					article = (Iarticle) iter.next();
				}
			}
			if(article!=null) {
				this.getHibernateTemplate().delete(article);
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new DAOException();
		}
	}
	
	//获取该用户的所有记录数
	@Override
	public int getAllRowCount(String username) throws DAOException {
		try {
	        String hql = "select count(*) from Iarticle as a where a.username="+"'"+username+"'";  
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
