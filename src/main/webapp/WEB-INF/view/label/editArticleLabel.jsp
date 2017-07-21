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
<link href="/Koala/resources/images/tagIco/bianji.png" type="image/x-icon"
	rel="shortcut icon">
<link rel="stylesheet" href="/Koala/layui/css/layui.css" media="all">
<link href="/Koala/bs3/dpl.css" rel="stylesheet">
<link href="/Koala/bs3/bui.css" rel="stylesheet">
<link href="/Koala/zui/css/zui.min.css" rel="stylesheet">
<!-- jQuery (ZUI中的Javascript组件依赖于jQuery) -->
<script src="/Koala/js/jquery-3.2.1.min.js"></script>
<!-- ZUI Javascript组件 -->
<script src="/Koala/zui/js/zui.min.js"></script>
<style>
a.hover-color:hover {
	color: red;
}
.contentDiv{color:#000;border:0px solid #393d49;} /* 默认的样式 */
.contentDiv:hover{color:#000;border:1px solid #169fe6;} /* 鼠标经过时的样式 */
.touming{background: rgba(0, 0, 0, 0);}/* 设置透明 */
</style>
<title>编辑分类</title>
<script src="/Koala/layui/layui.js" charset="utf-8"></script>
<script src="/Koala/js/jquery-3.2.1.min.js" charset="utf-8"></script>
<script src="/Koala/ckeditor_full/ckeditor.js" charset="utf-8"></script>
<script>

</script>
</head>
<body class='layui-bg-black'>
	<div class='layui-main touming' style="width: 100%; height: 80px;">
		<div style="float: right;background: rgba(0, 0, 0, 0);">
			<ul class="layui-nav touming" lay-filter="">
				<li class="layui-nav-item"><a href="/Koala/${user.username}/home">主页</a></li>
				<li class="layui-nav-item "><a href="/Koala/newArticle">新博客</a></li>
				<li class="layui-nav-item"><a href="/Koala/manager">管理</a></li>
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
		
		<!-- 左侧边栏satrt -->
		<div style="width: 20%; margin-left:2%;float:left;">
			<div class="row" style="width: 100%;">
	      		<div class="span12" style="width: 100%;">
			        <div class="panel panel-primary">
			          <div class="panel-header clearfix">
			            <h3 class="pull-left">当前分类</h3>
			          </div>
			          <div class="panel-body" style="background-color:#f2f2f2">
			           		     <section contextmenu="mymenu">
									<c:forEach var="articleLabel" items="${IarticleLabelList}">
						            	<a href="/Koala/${user.username}/browserByLabel?articleLabelId=${articleLabel.id}">
											<font style="font-size:15px;">${articleLabel.name}(${articleLabel.articleNum})</font>
										</a>
						            	<br/><br/>
						            </c:forEach>
								 </section>
			          </div>
			        </div>
			     </div>
   			</div>
   			
		</div>
		<!-- 左侧边栏end -->
		
		<!-- 编辑区域start -->
		<div style="width: 70%; margin-left:5%;float:left">
			<c:forEach var="articleLabel" items="${IarticleLabelList}">
				<div>
					<font style="font-size:15px;color:#ffffff;float:left">${articleLabel.name}(${articleLabel.articleNum})</font>
					<button style="float:right;margin-left:15px;" class="btn btn-danger " type="button" onclick="deleteLable(${articleLabel.id});">删除</button>
					<button style="float:right;margin-left:15px;" class="btn btn-warning" type="button" onclick="changeLable(${articleLabel.id});">修改</button>
				</div>
            	<br/><hr><br/>
            </c:forEach>
            <button style="float:right;margin-left:15px;" class="btn btn-primary" type="button" onclick="addLable();">添加</button>
		</div>
		<!-- 编辑区域end -->
	</div>
	<div class="layui-footer footer footer-doc"
		style="min-height: 100%; text-align: center; width: 100%; height: 100px; BACKGROUND-COLOR: transparent;clear:both">
		<br/><br/>
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
<script>
deleteLable = function(labelId){
	var arguments={"editType":"delete","articleLabelId":labelId};
	 if(confirm("删除该分类会导致分类下所有内容都被删除，确定要执行？")){
		 submit(arguments);
	 }
}
changeLable = function(labelId){
	var newLabelName=prompt("请输入要修改的名字");
	if(newLabelName!=null && newLabelName!=""){
		var arguments={"editType":"change","articleLabelId":labelId,"newArticleLabelName":newLabelName};
		submit(arguments);
	}
}
addLable = function(){
	var newLabelName=prompt("请输入新标签的名字");
	if(newLabelName!=null && newLabelName!=""){
		var arguments={"editType":"add","articleLabelName":newLabelName};
		submit(arguments);
	}
	
}


submit=function(arguments){
	//alert(arguments);
	$.post("/Koala/editArticleLabel",
	  arguments,
	  function(data,status){
		  if(status=="success"){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('提交成功！', {
						  time: 0 //不自动关闭
						  ,btn: ['确定']
						  ,yes: function(index){
							  layer.close(index);
							  window.location.href="/Koala/editArticleLabelRequest";
						  }
						});
					}); 
		  }else{
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.msg('对不起，提交失败。失败原因：'+data);
					}); 
		  }
	});
	
}
	
</script>
</html>