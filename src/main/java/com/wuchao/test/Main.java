package com.wuchao.test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
	 public static String delHTMLTag(String htmlStr){ 
	        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(htmlStr); 
	        htmlStr=m_script.replaceAll(""); //过滤  
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(htmlStr); 
	        htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	    } 
	    public static String delSpace(String htmlStr){ 
	        htmlStr = htmlStr.replaceAll("\n", "");
	        htmlStr = htmlStr.replaceAll("\t", "");
	        return htmlStr.trim(); //返回文本字符串 
	    }
	public static void main(String[] args) {
		//AnnotationConfigApplicationContext构造方法传入的是一个或多个注解类，注解类里使用@Bean声明了多个Bean
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		//AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext ();
		//context.register(Bean01.class);
		//context.scan("com.springmvc.config");
		//context.refresh();
		String s = "Gradle插件通过引入特定领域的约定和任务来构建你的项目。Java插件是Gradle自身装载的一个插件。Java插件提供的基本功能远比源代码编译和打包多。它为你的项目建立了一个标准的项目布局，并确保有意义，有顺序地执行任务。现在，为你的项目创建一个构建脚本并使用Java插件。 使用Java插件";
		System.out.println(s.length());
	
	}

}
