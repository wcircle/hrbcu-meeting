<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<link rel="shortcut icon" type="image/x-icon" href="/indexs/images/icon32.png">
	<link rel="icon" type="image/x-icon" href="/indexs/images/icon32.png">
	<%@ include file="/backAdmin/common/include.jsp" %>
	<script type="text/javascript" src="/indexs/js/main.js"></script>
	<title>继续教育学院会务中心</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
</head>
<body>
	<form id="register-form" action="/index/register" method="post" onreset='formReset(this);' class="form form-horizontal">
		<div class="row cl">
			<div class="form-label col-xs-2 col-sm-2"></div>
			<div class="form-label col-xs-3 col-sm-3 " style="font-size: 14px;"><span class="c-red">*</span>您的姓名</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="text" class="input-text size-S radius" id="userName" name="userName" placeholder="您的姓名" />
			</div>
			<div class="formControls col-xs-3 col-sm-3"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-2 col-sm-2"></div>
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>手机号：</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="text" class="input-text size-S radius" placeholder="手机号" id="phone" name="phone" maxlength="11">
			</div>
			<div class="formControls col-xs-3 col-sm-3"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-2 col-sm-2"></div>
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>登录密码：</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="password" class="input-text size-S radius" placeholder="登录密码" id="password" name="password" >
				<div style="font-size:12px"><span class="c-red">*</span>密码为6-12位，字母、数字或者下划线组成</div>
			</div>
			<div class="formControls col-xs-3 col-sm-3"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-2 col-sm-2"></div>
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>确认登录密码：</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="password" class="input-text size-S radius" placeholder="确认登录密码" id="confirmPassword" name="confirmPassword" maxlength="11">
			</div>
			<div class="formControls col-xs-3 col-sm-3"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-2 col-sm-2"></div>
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>性别：</div>
			<div class="formControls col-xs-4 col-sm-4">
				<div class="radio-box">
					<input type="radio" value="1" id="radio-4" name="gender" checked />
					<label for="radio-4">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" value="2" id="radio-5" name="gender" />
					<label for="radio-5">女</label>
				</div>
			</div>
			<div class="formControls col-xs-3 col-sm-3"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-2 col-sm-2"></div>
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>身份证号码：</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="text" class="input-text size-S radius" placeholder="身份证号码" id="idNo" name="idNo" minlength="18">
			</div>
			<div class="formControls col-xs-3 col-sm-3"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-4 col-sm-4"></div>
			<div class="formControls col-xs-6 col-sm-6">
				<input class="btn btn-primary size-S radius" type="submit" value="&nbsp;&nbsp;注册&nbsp;&nbsp;">
				<button onClick="closeIframe();" class="btn btn-default size-S radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
			<div class="formControls col-xs-4 col-sm-4"></div>
		</div>
	</form>
<script type="text/javascript">

	function closeIframe() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}

	/*前端表单校验*/
	$("#register-form").validate({
		rules:{
			userName:{
				required:true
			},
			phone:{
				required:true,
				digits:true,
				maxlength:11,
			},
			password:{
				required:true,
				rangelength:[6,12]
			},
			confirmPassword:{
				required:true,
				rangelength:[6,12],
				equalTo:"#password"
			},
			gender:{
				required:true,
			},
			idNo:{
				required:true,
				maxlength:18,
			}
		},
		messages:{
		},
		submitHandler:function(form){
			layer.load(1);
			$.ajax({
				url:$("#register-form").attr("action"),
				type:"POST",
				dataType: "json",
				data:$("#register-form").serialize(),
				async: true,
				beforeSend: function () {
					var phone = $("#phone").val();
					if(!checkNumber(phone)){
						layer.msg("请输入正确的手机号码",{icon:0,time:2000});
						layer.closeAll('loading');
						return false;
					}
					var password = $("#password").val();
					//验证密码是否为数字、字母、下划线
					var reg = /^[\w]{6,12}$/;//这个是正则表达式
					if(!password.match(reg)){
						layer.msg('登录密码格式不正确！',{icon:0,time:2000});
						$("#password").focus();
						layer.closeAll('loading');
						return false;
					}

					var confirmPassword = $("#confirmPassword").val();
					//验证密码是否为数字、字母、下划线
					var reg = /^[\w]{6,12}$/;//这个是正则表达式
					if(!password.match(reg)){
						layer.msg('确认密码格式不正确！',{icon:0,time:2000});
						$("#password").focus();
						layer.closeAll('loading');
						return false;
					}
				},
				success: function(msg) {
					layer.closeAll('loading');
					var data = eval(msg); //数据
					if(data.status == 0){
						layer.msg(data.msg,{icon:0,time:2000});
						return false;
					}else{
						layer.msg("注册成功,请登录",{icon:0,time:2000});
						setTimeout(function () {
							closeIframe();
						},2000);
					}

				},error: function(XMLHttpRequest,textStatus,errorThrown,data) {
					layer.closeAll('loading');
					layer.msg("error:"+errorThrown+"   "+"textStatus:"+textStatus);

				}
			});


		}
	});

</script>
</body>
</html>