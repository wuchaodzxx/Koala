package com.wuchao.test.product.bo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuchao.test.product.bo.intf.ProductBO;
import com.wuchao.test.product.dao.intf.ProductDAO;
import com.wuchao.test.product.po.Product;


@Service("productBO")
@Transactional
public class ProductBOImpl implements ProductBO{

	@Resource(name="productDAO")
	public ProductDAO productDAO;

	@Transactional(rollbackFor={Exception.class, RuntimeException.class})
	public void addProduct(Product product) throws Exception {
		System.out.println("ProductBOImpl addProduct!");
		productDAO.addProduct(product);
		//throw new RuntimeException("addProduct");
	}

}
