<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>资料修改</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="/Koala/resources/images/tagIco/zhuce.png"
	type="image/x-icon" rel="shortcut icon">
<link rel="stylesheet" href="/Koala/layui/css/layui.css"
	media="all">
<link href="/Koala/zui/css/zui.min.css" rel="stylesheet">
<!-- jQuery (ZUI中的Javascript组件依赖于jQuery) -->
<script src="/Koala/js/jquery-3.2.1.min.js"></script>
<!-- ZUI Javascript组件 -->
<script src="/Koala/zui/js/zui.min.js"></script>
<style>
body {
	text-align: center
}
.div_form {
	margin: 0 auto;
	width: 600px;
	height: 200px;
}

.div_formIn {
	margin: 150px 0px 0px 0px;
}
td  
{  
 white-space: nowrap;  
}  
</style>
	<script type="text/javascript">
	function reloadVaryfiCode(){
		document.getElementById("varifyCodeID").src="/Koala/login/getVerifyCode?"+  new Date().getTime();
		}
</script>
</head>
<body class='layui-bg-black'>
	<div class='layui-main' style="width: 100%; height: 80px">
		<div style="float: right">
			<ul class="layui-nav" lay-filter="" >
				<li class="layui-nav-item"><a href="/Koala/${user.username}/home">主页</a></li>
					<!-- <li class="layui-nav-item "><a href="/Koala/changePasswordRequest">修改密码</a></li> -->
				<li class="layui-nav-item layui-this" ><a href="/Koala/changeUserInfo">个人资料</a></li>
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
	<div class='layui-main' style="width: 400px; height: 10px"></div>
	<div class='layui-main' style="width: 100%;min-height:400px;">
		<div style="float:left">
			<ul class="layui-nav layui-nav-tree" lay-filter="demo">
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">个人资料查询</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:window.location.href='/Koala/changeUserInfo'">基本信息</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">个人资料修改</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;">头像上传</a></dd>
			      <dd><a href="javascript:;">基本资料修改</a></dd>
			      <dd><a href="/Koala/changePasswordRequest">密码修改</a></dd>
			    </dl>
			  </li>
			</ul>
		</div>	 
		<!-- 显示区start -->
		<div style="float:left;margin-left:40px;width:70%;"> 
			<!-- 基本信息 -->
			<div style="margin-left:20%;width:400px;">
				<div  class="">
					<div class="panel panel-primary">
					  <div class="panel-heading">
					   用户基本信息
					  </div>
					  <div class="panel-body">
					   	  <font>用户名：${user.username}</font><hr>
					   	  <font>用户昵称：${user.nickname}</font><hr>
					   	  <font>用户ID：${user.id}</font><hr>
					   	  <font>
					   	  		注册时间：<fmt:formatDate type="date" value="${user.createdate}" pattern="yyyy-MM-dd"/>
					   	  </font><hr>
					   	  <font>
								最后修改时间：<fmt:formatDate type="date" value="${user.modifydate}" pattern="yyyy-MM-dd"/>
						  </font>
					  </div>
					</div>
				</div>
			</div>
		
			<!-- 头像上传 -->
			<div>
				<div class="site-demo-upload">
				  <div class="site-demo-upbar">
				    <div class="layui-box layui-upload-button"><form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="/test/upload.json"><input name="file" class="layui-upload-file" id="test" type="file"></form><span class="layui-upload-icon"><i class="layui-icon"></i>上传图片</span></div>
				  </div>
				</div>
			</div>	
			<!-- 基本资料修改 -->
			<div>
			
			</div>		
		</div>
		<!-- 显示区end -->
	</div>
	<div class='layui-main' style="width: 400px; height: 130px"></div>
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

<script src="/Koala/layui/layui.js" charset="utf-8"></script>
<script src="/Koala/js/Md5Util.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('element', function(){
  var element = layui.element(); //导航的hover效果、二级菜单等功能，需要依赖element模块
  
  //监听导航点击
  element.on('nav(demo)', function(elem){
    //console.log(elem)
    
  });
});


layui.use('upload', function(){
  layui.upload({
    url: '' //上传接口
    ,success: function(res){ //上传成功后的回调
      console.log(res)
    }
  });
  
  layui.upload({
    url: '/test/upload.json'
    ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
    ,method: 'get' //上传接口的http类型
    ,success: function(res){
      LAY_demo_upload.src = res.url;
    }
  });
});

</script>
<script type="text/javascript" color="255,255,255" opacity='0.7' zIndex="-2" count="200" src="/Koala/js/canvas-nest.js"></script>		
</body>
</html>