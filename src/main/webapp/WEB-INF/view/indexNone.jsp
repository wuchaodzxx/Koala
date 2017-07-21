<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="/Koala/resources/images/tagIco/home.png" type="image/x-icon"
	rel="shortcut icon">
<link rel="stylesheet" href="/Koala/layui/css/layui.css" media="all">
<link href="/Koala/bs3/dpl.css" rel="stylesheet">
<link href="/Koala/bs3/bui.css" rel="stylesheet">
<style>
a.hover-color:hover {
	color: red;
}
.contentDiv{color:#000;border:0px solid #393d49;} /* 默认的样式 */
.contentDiv:hover{color:#000;border:1px solid #169fe6;} /* 鼠标经过时的样式 */
.touming{background: rgba(0, 0, 0, 0);}/* 设置透明 */
</style>
<title>主页</title>
<script src="/Koala/layui/layui.js" charset="utf-8"></script>
<script src="/Koala/js/jquery-3.2.1.min.js" charset="utf-8"></script>
</head>
<body class='layui-bg-black'>
	<div class='layui-main touming' style="width: 100%; height: 80px;">
		<div style="float: right;background: rgba(0, 0, 0, 0);">
			<ul class="layui-nav touming" lay-filter="">
				<li class="layui-nav-item layui-this"><a href="/Koala/">主页</a></li>
				<li class="layui-nav-item "><a href="/Koala/newArticle">新博客</a></li>
				<c:if test="${user == null}">
					<li class="layui-nav-item"><a href="/Koala/login">登录</a></li>
				</c:if>
				<c:if test="${user != null}">
					<li class="layui-nav-item"><a href="/Koala/manager">管理</a></li>
					<li class="layui-nav-item"><a href="/Koala/logout">注销</a></li>
				</c:if>
				<li class="layui-nav-item"><a href="/Koala/about">关于</a></li>
				<div style="display: inline-block;">
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

	<div class='main' style="width: 100%;min-height:400px">
		
		<div style="width: 70%; margin-left:15%;float:left">
				<!-- 下面显示列表 -->
				<div id="list" class='layui-main touming' style="width: 100%;">
					<c:forEach var="article" items="${page.list}">
						<div class="contentDiv">
							<fieldset class="layui-elem-field layui-field-title">
								<blockquote class="layui-elem-quote">
									<a href="/Koala/${article.username}/browser?articleId=${article.id}">
										<font style="font-size:20px;">${article.title}</font>
									</a>
								</blockquote>
								<div style="font-family:'楷体';font-size:15px;">
									<div class="layui-field-box" style="color:#ffffff">
										<div>
											摘要：${article.abstractsDesc}
											<a href="/Koala/${article.username}/browser?articleId=${article.id}" style="font-size:20px;color:#169fe6">
												阅读全文
											</a>
										</div>
									</div>
									<div style="color:#ffffff">posted @ <fmt:formatDate type="date" value="${article.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>  ${article.username} 阅读(${article.browsers}) 评论(${article.comments})  </div>
								</div>
							</fieldset>
						</div>	
					<br/>	
					</c:forEach>				
				</div>				
				<!-- 分页控件 -->
				<div class='layui-main' style="width: 100%;text-align:right;">
					<div id="PagingBar"></div>
				</div>
			</div>
	</div>



	
	<div style="display:none" id="username">${TargetUserName}</div>
	<div style="display:none" id="pageSize">${pageSize}</div>
	<div style="display:none" id="currentPage">${currentPage}</div>
	<div style="display:none" id="totalPages">${totalPages}</div>
	
	<div class="layui-footer footer footer-doc"
		style="text-align: center; width: 100%; height: 100px; BACKGROUND-COLOR: transparent;clear:both">
		<div class="layui-main" style="BACKGROUND-COLOR: transparent">
			<p>
				<font style="color: #ffffff">Copyright © 吴超的博客 2017
					&nbsp;|&nbsp;访问量:${Isystem.amountOfAccess } </font>
			</p>
			<div class='layui-main' style="width: 100%; height: 10px"></div>
			<p class="hover-color">
				<img style="" src="/Koala/resources/images/tagIco/boke30px.png">
				<a href="http://www.cnblogs.com/wuchaodzxx/" target="_blank"><font
					style="color: #ffffff">博客园</font></a> &nbsp;&nbsp; <img style=""
					src="/Koala/resources/images/tagIco/git30px.png"> <a
					href="https://github.com/wuchaodzxx" target="_blank"><font
					style="color: #ffffff">Git仓库</font></a>
			</p>
		</div>
	</div>

	<script type="text/javascript" color="255,255,255" opacity='0.7'
		zIndex="-2" count="200" src="/Koala/js/canvas-nest.js"></script>
</body>
</html>
<script>
	var username = document.getElementById("username").innerText;;//用户名
	var pageSize = document.getElementById("pageSize").innerText;;//每页记录数目
	var totalPages = document.getElementById("totalPages").innerText;;//页数
	var currentPage = document.getElementById("currentPage").innerText;//当前页


	layui.use([ 'laypage', 'layer' ], function() {
		var laypage = layui.laypage;
		var layer = layui.layer;

		laypage({
			cont : 'PagingBar',
			first : 1,
			pages : totalPages,
			skin : '#1E9FFF',
			curr : currentPage,
			jump : function(obj, first) {
				//得到了当前页，用于向服务端请求对应数据
				//第一次加载
				if(first){
					//alert("first");
				}else{
					window.location.href="/Koala?currentPage="+obj.curr+"&totalPages="+totalPages;
				}

			}
		});

	});
</script>