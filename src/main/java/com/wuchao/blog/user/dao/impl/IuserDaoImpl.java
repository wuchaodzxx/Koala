package com.wuchao.blog.user.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wuchao.blog.user.dao.intf.IuserDao;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.test.product.po.Product;
import com.wuchao.utils.exception.DAOException;

@Repository("iuserDao")
public class IuserDaoImpl extends HibernateDaoSupport implements IuserDao {
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
	

	public void addIuser(Iuser user) throws DAOException {		
		try {
			this.getHibernateTemplate().save(user);
		}catch(Exception e) {
			throw new DAOException();
		}
	}

	public Iuser getIuserByUsername(String username) throws DAOException {
		List list = null;
		Iuser iuser = null;
		try {
	        list = this.getHibernateTemplate().find("from Iuser u where u.username=?",username); 
		}catch(Exception e) {
			throw new DAOException();
		}
		if(list!=null && list.size()>0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				iuser = (Iuser) iter.next();
			}
		}
		return iuser;
	}

}
