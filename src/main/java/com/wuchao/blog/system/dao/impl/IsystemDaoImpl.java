package com.wuchao.blog.system.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wuchao.blog.system.dao.intf.IsystemDao;
import com.wuchao.blog.system.po.Isystem;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.exception.DAOException;
@Repository("isystemDao")
public class IsystemDaoImpl extends HibernateDaoSupport implements IsystemDao {
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
	

	public void addIsystem(Isystem isystem) throws DAOException {
		// TODO Auto-generated method stub
		try {
			this.getHibernateTemplate().save(isystem);
		}catch(Exception e) {
			throw new DAOException();
		}
	}


	public Isystem getIsystemByDefault() throws DAOException {
		List list = null;
		Isystem isystem = null;
		try {
	        list = this.getHibernateTemplate().find("from Isystem u where u.adminName=?", this.defaultAdminName); 
		}catch(Exception e) {
			throw new DAOException();
		}
		if(list!=null && list.size()>0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				isystem = (Isystem) iter.next();
			}
		}
		return isystem;
	}
	@Override
	public void saveIsystem(Isystem isystem) throws DAOException {
		this.getHibernateTemplate().update(isystem);
	}

}
