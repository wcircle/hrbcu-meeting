<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<%@ include file="/backAdmin/common/include.jsp" %>
	<link href="/static/lib/jquery.select2/css/select2.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/static/lib/jquery.select2/js/select2.min.js"></script>
	<script type="text/javascript" src="/static/lib/jquery.select2/js/i18n/zh-CN.js"></script>
	<title>继续教育学院会务中心</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
</head>
<body>
	<form id="edit-form" action="/backAdmin/sleepEdit" method="post" onreset='formReset(this);' class="form form-horizontal">
		<input type="hidden" name="id" value="${sleep.id}">
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>用户信息</div>
			<div class="formControls col-xs-6 col-sm-6">
				姓名：${sleep.userName} 手机号：${sleep.phone}
			</div>
			<div class="form-label col-xs-1 col-sm-1"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>住宿序号</div>
			<div class="formControls col-xs-4 col-sm-4">
				第<input type="number" class="input-text size-S radius" id="sleepOrder" name="sleepOrder" value="${sleep.sleepOrder}" placeholder="住宿序号" />晚
			</div>
			<div class="form-label col-xs-1 col-sm-1"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>住宿日期</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
					   id="sleepDate" name="sleepDate" class="input-text Wdate size-S" style="width:180px;" value="${sleep.sleepDate}">

			</div>
			<div class="form-label col-xs-1 col-sm-1"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>住宿地点</div>
			<div class="formControls col-xs-6 col-sm-6">
				<textarea id="address" name="address" class="textarea radius" style="font-size: 12px;" placeholder="住宿地点">${sleep.address}</textarea>
			</div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>房间号</div>
			<div class="formControls col-xs-4 col-sm-4">
				<input type="text" class="input-text size-S radius" id="roomNo" name="roomNo" value="${sleep.roomNo}" placeholder="房间号" />
			</div>
			<div class="form-label col-xs-1 col-sm-1"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-4 col-sm-4"></div>
			<div class="formControls col-xs-6 col-sm-6">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">
				<button onClick="closeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
<script type="text/javascript">
	function closeIframe() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}

	/*前端表单校验*/
	$("#edit-form").validate({
		rules:{
			sleepOrder:{
				required:true
			},
			address:{
				required:true
			},
			roomNo:{
				required:true
			},
			sleepDate:{
				required:true
			},
			receiveId:{
				required:true
			}
		},
		messages:{
		},
		submitHandler:function(form){
			layer.load(1);
			$.ajax({
				url:$("#edit-form").attr("action"),
				type:"POST",
				dataType: "json",
				data:$("#edit-form").serialize(),
				async: true,
				success: function(msg) {
					layer.closeAll('loading');
					var data = eval(msg); //数据
					if(data.status == 0){
						layer.msg(data.msg,{icon:0,time:2000});
						return false;
					}else{
						layer.msg("修改成功",{icon:0,time:2000});
						setTimeout(function () {
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.location.replace(parent.location.href);
							parent.layer.close(index); //再执行关闭
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