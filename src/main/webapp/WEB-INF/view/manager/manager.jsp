<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="/Koala/resources/images/tagIco/home.png"
	type="image/x-icon" rel="shortcut icon">
<link rel="stylesheet" href="/Koala/layui/css/layui.css"
	media="all">
<style>
a.hover-color:hover{color:red;} 
</style>
<title>管理</title>
<script src="/Koala/layui/layui.js" charset="utf-8"></script>
</head>
<body class='layui-bg-black'>
	<div class='layui-main' style="width: 100%; height: 80px">
		<div style="float: right">
			<ul class="layui-nav" lay-filter="" >
				<li class="layui-nav-item"><a href="/Koala/${user.username}/home">主页</a></li>
				<li class="layui-nav-item "><a href="">修改密码</a></li>
				<li class="layui-nav-item"><a href="">修改资料</a></li>
				<li class="layui-nav-item"><a href="/Koala/logout">注销</a></li>
				<div class="layui-nav" style="display: inline-block;">
					<div class="layui-inline " style="margin-top: 6px;">
						<div class="layui-inline" style="width: 40px; height: 40px">
							<img style="width: 100%;"
								src="/Koala/resources/images/tagIco/portrait50px.png"
								class="layui-circle">
						</div>
					</div>
				</div>
			</ul>

		</div>
	</div>
	<div class='layui-main' style="width: 100%; height: 20px"></div>
	<div class='layui-main' style="width: 100%;height: 700px"></div>

	<div class="layui-footer footer footer-doc" style="text-align: center;width: 100%;height: 50px;BACKGROUND-COLOR: transparent">
	  <div class="layui-main" style="BACKGROUND-COLOR: transparent">
	    <p><font style="color:#ffffff">Copyright © 吴超的博客 2017 &nbsp;|&nbsp;访问量:${Isystem.amountOfAccess } </font></p>
	    <div class='layui-main' style="width: 100%;height: 10px"></div>
	    <p  class="hover-color" >
	      <img style="" src="/Koala/resources/images/tagIco/boke30px.png">
	      <a  href="http://www.cnblogs.com/wuchaodzxx/" target="_blank"><font style="color:#ffffff">博客园</font></a>
	      &nbsp;&nbsp;
	      <img style="" src="/Koala/resources/images/tagIco/git30px.png">
	      <a href="https://github.com/wuchaodzxx" target="_blank"><font style="color:#ffffff">Git仓库</font></a>
	    </p>
	  </div>
	</div>
	<div class='layui-main' style="width: 100%;height: 20px"></div>
	<script type="text/javascript" color="255,255,255" opacity='0.7'
		zIndex="-2" count="200" src="/Koala/js/canvas-nest.js"></script>
</body>
</html>