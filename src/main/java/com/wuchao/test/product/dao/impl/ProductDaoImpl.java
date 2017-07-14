package com.wuchao.test.product.dao.impl;

import javax.annotation.Resource;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.test.product.dao.intf.ProductDAO;
import com.wuchao.test.product.po.Product;

@Repository("productDAO")
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDAO {
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

	public void addProduct(Product product) {
		// getSession().setDefaultReadOnly(false);
		// getSession().save(product);
		// System.out.println("ProductDaoImpl
		// addProduct!"+":"+product.getProductName());
		// getSession().flush();
		getMyHibernateTemplate().save(product);
		System.out.println("ProductDaoImpl addProduct!" + ":" + product.getProductName());
	}
}
