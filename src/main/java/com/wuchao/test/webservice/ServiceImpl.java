package com.wuchao.test.webservice;

import java.util.Date;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Repository;


/**
 * <b>function:</b> WebService传递复杂对象，如JavaBean、Array、List、Map等
 */
@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("deprecation")
public class ServiceImpl implements IService {
	public User getUserByName(@WebParam(name = "name") String name) {
		User user = new User();
		user.setId(new Date().getSeconds());
		user.setName(name);
		user.setAddress("china");
		user.setEmail(name + "@test.com");
		return user;
	}

	public void setUser(User user) {
		System.out.println("############Server setUser###########");
		System.out.println("setUser:" + user);
	}
}
