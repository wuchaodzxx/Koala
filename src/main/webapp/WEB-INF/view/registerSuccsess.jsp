<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>注册成功</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="/Koala/resources/images/tagIco/zhuce.png"
	type="image/x-icon" rel="shortcut icon">
<link rel="stylesheet" href="/Koala/layui/css/layui.css"
	media="all">
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
</style>

</head>
<body class='layui-bg-black'>
		<div class='layui-main' style="width: 100%; ">
		<div style="float: right;BACKGROUND-COLOR: transparent">
			<ul class="layui-nav" lay-filter="" style="BACKGROUND-COLOR: transparent">
				<li class="layui-nav-item">
					<a href="/Koala/login">
						<font style="font-weight:bold;">登录</font>
					</a>
				</li>
				<div class="layui-nav" style="margin-left:-20px;display: inline-block;BACKGROUND-COLOR: transparent">
					<div class="layui-inline" style="margin-top: 6px;BACKGROUND-COLOR: transparent">
						<div class="layui-inline" style="width: 40px; height: 40px;BACKGROUND-COLOR: transparent">
							<img style="width: 100%;"
								src="/Koala/resources/images/tagIco/portrait50px.png"
								class="layui-circle">
						</div>
					</div>
				</div>
			</ul>

		</div>
	</div>
	
	<div class='layui-main' style="width: 700px; height: 150px">
		
		<div style="text-align:center;padding-top:100px">
		</div>
		<div style="text-align:center;">
			<img alt="" src="/Koala/resources/images/tagIco/zhucechenggong.png"/>
			<font style="color:#1296DB;vertical-align:middle" size="20px" >注册成功</font>
		</div>

	</div>

	<script src="/Koala/layui/layui.js" charset="utf-8"></script>
	<script src="/Koala/js/Md5Util.js" charset="utf-8"></script>
	
	<script type="text/javascript" color="255,255,255" opacity='0.7' zIndex="-2" count="200" src="/Koala/js/canvas-nest.js"></script>		
</body>
<script type="text/javascript">
window.onload=function(){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.msg('注册成功，现在要去登录吗？', {
			  time: 0 //不自动关闭
			  ,btn: ['好的！', '暂时不需要！']
			  ,yes: function(index){
				  layer.close(index);
				  window.location.href="/Koala/login";
			  }
			});
		});              
}
</script>
</html>