package com.wuchao.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

	public static void main(String[] args) {
		//AnnotationConfigApplicationContext构造方法传入的是一个或多个注解类，注解类里使用@Bean声明了多个Bean
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext ();
		//context.register(Bean01.class);
		context.scan("com.springmvc.config");
		context.refresh();
	   
	
	}

}
