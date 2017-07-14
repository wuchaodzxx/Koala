package com.wuchao.test.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class SpringUsersWsClient {
	public static void main(String[] args) {

		// 调用WebService

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IService.class);
		factory.setAddress("http://localhost:8080/SpringProject/service/Users");
		IService service = (IService) factory.create();
		System.out.println("#############Client getUserByName##############");
		User user = service.getUserByName("hoojo");
		System.out.println(user);
		user.setAddress("China-Guangzhou");
		service.setUser(user);
	}
}
