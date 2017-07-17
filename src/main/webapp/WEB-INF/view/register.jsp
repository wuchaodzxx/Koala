<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
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
	<script type="text/javascript">
	function reloadVaryfiCode(){
		document.getElementById("varifyCodeID").src="/Koala/login/getVerifyCode?"+  new Date().getTime();
		}
</script>
</head>
<body class='layui-bg-black'>
	<div class='layui-main' style="width: 400px; height: 150px"></div>
	<div class='layui-main' style="width: 400px">
		<form class="layui-form" style="BACKGROUND-COLOR: transparent" action="/Koala/register">
			<table class="" style="BACKGROUND-COLOR: transparent">
				<tbody>
					<tr>
						<td>
						</td>
						<td>
							<font style="color:#FFFF37">
								<c:if test="${not empty formResult}">
 								 	${formResult} 
 								</c:if>
							</font>
							<div style="height: 10px"></div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="layui-form-label">
								<font color="#FFFFFF" style="font-family:SimHei">账号</font>
							</label>
						</td>
						<td>
							<div class="layui-input-inline">
								<input name="username" lay-verify="required" autocomplete="off"
									class="layui-input" type="tel" >
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="layui-form-label">
								<font color="#FFFFFF" style="font-family:SimHei">昵称</font>
							</label>
						</td>
						<td>
							<div class="layui-input-inline">
								<input name="nickname" lay-verify="required" autocomplete="off"
									class="layui-input" type="tel" >
							</div>
						</td>
						<td></td>
					</tr>						
					<tr>
						<td><div  style="height: 10px"></div></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="layui-form-label">
								<font color="#FFFFFF" style="font-family:SimHei">密码</font>
							</label>
						</td>
						<td>
							<div class="layui-input-inline">
								<input id="password" name="password" lay-verify="required" autocomplete="off"
									class="layui-input" type="password">
							</div>
						</td>

						<td></td>
					</tr>
<tr>
						<td><div  style="height: 10px"></div></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="layui-form-label">
								<font color="#FFFFFF" style="font-family:SimHei">密码确认</font>
							</label>
						</td>
						<td>
							<div class="layui-input-inline">
								<input id="passwordConfirm" name="passwordConfirm" lay-verify="required" autocomplete="off"
									class="layui-input" type="password">
							</div>
						</td>

						<td></td>
					</tr>					
					<tr>
						<td><div  style="height: 10px"></div></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="layui-form-label">
								<font color="#FFFFFF" style="font-family:SimHei">验证码</font>
							</label>
						</td>
						<td>
							<div class="layui-input-inline">
								<input name="varifycode" lay-verify="required" autocomplete="off"
									class="layui-input" type="tel">
							</div>
						</td>
						<td>
							<div style="margin-left:10px">
								<img id="varifyCodeID" onclick="reloadVaryfiCode()" src="/Koala/login/getVerifyCode" alt="" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<input id="passwordMD5" style="display:none"/>
			<div class='layui-main' style="width: 400px; height: 30px"></div>
			<div class='layui-main' style="width: 400px; height: 30px">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>

		</form>
	</div>


	<script src="/Koala/layui/layui.js" charset="utf-8"></script>
	<script src="/Koala/js/Md5Util.js" charset="utf-8"></script>
	<script>
		layui
				.use(
						[ 'form', 'layedit', 'laydate' ],
						function() {
							var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

							//监听提交
							form.on('submit(demo1)', function(data) {
								//密码和确认密码校验
								if(data.field.password!=data.field.passwordConfirm){
									layer.msg("两次密码不一致");
									return false;
								}
								var password = hex_md5(data.field.password,32);
								document.getElementById("password").value = password;
								document.getElementById("passwordConfirm").value = password;
								return true;
							});

						});
	</script>
<script type="text/javascript" color="255,255,255" opacity='0.7' zIndex="-2" count="200" src="/Koala/js/canvas-nest.js"></script>		
</body>
</html>