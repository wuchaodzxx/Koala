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
<link href="/Koala/resources/images/tagIco/wenzhan.png" type="image/x-icon"
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
<title>${Article.title}</title>
<script src="/Koala/layui/layui.js" charset="utf-8"></script>
<script src="/Koala/js/jquery-3.2.1.min.js" charset="utf-8"></script>
</head>
<body class='layui-bg-black'>
	<div class='layui-main touming' style="width: 100%; height: 80px;">
		<div style="float: right;background: rgba(0, 0, 0, 0);">
			<ul class="layui-nav touming" lay-filter="">
				<li class="layui-nav-item"><a href="/Koala/${TargetUserName}/home">主页</a></li>
				<li class="layui-nav-item "><a href="/Koala/newArticle">新博客</a></li>
				<li class="layui-nav-item"><a href="/Koala/manager">管理</a></li>
				<li class="layui-nav-item"><a href="">联系</a></li>
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

	<div class='main' style="width: 100%;min-height:450px;height:auto;">
		
		<!-- 左侧边栏satrt -->
		<div style="width: 20%; margin-left:2%;float:left;">
			<div class="row" style="width: 100%;">
	      		<div class="span12" style="width: 100%;">
			        <div class="panel panel-primary">
			          <div class="panel-header clearfix">
			            <h3 class="pull-left">分类管理</h3>
			          </div>
			          <div class="panel-body" style="background-color:#f2f2f2">
			           		     <section contextmenu="mymenu">
									<c:forEach var="articleLabel" items="${ArticleLabelList}">
						            	<a href="/Koala/${TargetUserName}/browserByLabel?articleLabelId=${articleLabel.id}">
											<font style="font-size:15px;">${articleLabel.name}(${articleLabel.articleNum})</font>
										</a>
						            	<br/><br/>
						            </c:forEach>
						            <font style="font-size:10px;color:#ff8800"> Tips:右键此处可编辑分类</font>
								 </section>
								<menu type="context" id="mymenu">
									<menuitem label="编辑分类" onclick="javascript:window.location.href='/Koala/editArticleLabelRequest'" icon="/images/refresh-icon.png"></menuitem>
								</menu>
			          </div>
			        </div>
			     </div>
   			</div>
   			
		</div>
		<!-- 左侧边栏end -->
		
		<!-- 编辑区域start -->
		<div style="width: 74%; margin-left:2%;min-height:450px;float:left;">
			<div id="title" style="margin-top:10px;">
				<font style="font-family:'楷体';font-size:30px;color:#ffffff">标题：${Article.title}</font>
			</div>
			<br/>
			<div id="cnblogs_post_body" style="background: rgba(0, 0, 0, 0.3);">
			    <font style="font-family:'楷体';font-size:15px;color:#ffffff">${Article.content}</font>
			    <br/><br/>
			</div> 
			<div>				
				<div style="color:#ffffff;font-size:15px;float:right；margin-right:20px;">
					posted @ 
					<fmt:formatDate type="date" value="${Article.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>  
						${Article.username} 阅读(${Article.browsers}) 评论(${Article.comments})  
					<c:if test="${user != null}">
						<c:if test="${user.id == Article.userId}">
							<a href="/Koala/editArticle?articleId=${Article.id}"><font style="font-size:15px;color:#ffffff;margin-left:20px">编辑</font></a>
						</c:if>
					</c:if>
				</div>
			</div>
			<br/><br/>
		</div>
		<!-- 编辑区域end -->
	</div>
	<div class="layui-footer footer footer-doc"
		style="text-align: center; margin-top:20px;width: 100%; height: 100px; BACKGROUND-COLOR: transparent;clear:both">
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
	<script src="/Koala/js/cnblog_wuchao_v3.js" charset="utf-8"></script>
</body>
</html>