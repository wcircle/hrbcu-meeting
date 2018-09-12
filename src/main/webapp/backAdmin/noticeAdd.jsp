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
	<link href="/static/lib/jquery-upload/uploadfile.css" rel="stylesheet">
	<script src="/static/lib/jquery-upload/jquery.uploadfile.js"></script>
	<script src="/backAdmin/js/uploadFile.js"></script>
	<title>继续教育学院会务中心</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
</head>
<body>
	<form id="add-form" action="/backAdmin/noticeAdd" method="post" onreset='formReset(this);' class="form form-horizontal">
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>通知标题</div>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text size-S radius" id="title" name="title" placeholder="通知标题" />
			</div>
			<div class="form-label col-xs-1 col-sm-1"></div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;"><span class="c-red">*</span>通知内容</div>
			<div class="formControls col-xs-8 col-sm-8">
				<textarea id="content" name="content" class="radius" rows="10" style="width:100%;font-size: 12px;" placeholder="通知内容"></textarea>
			</div>
		</div>
		<div class="row cl">
			<div class="form-label col-xs-3 col-sm-3" style="font-size: 14px;">上传附件</div>
			<div id="uploadDiv" class="formControls col-xs-6 col-sm-6">
				<div id="fileUpLoader">上传附件</div>
			</div>
		</div>

		<div class="row cl">
			<div class="form-label col-xs-4 col-sm-4"></div>
			<div class="formControls col-xs-6 col-sm-6">
				<input class="btn btn-primary radius" type="button" onclick="noticeAdd()" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">
				<button onClick="closeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
			<div class="formControls col-xs-4 col-sm-4"></div>
		</div>
	</form>
<script type="text/javascript">

	function closeIframe() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	$(document).ready(function() {

		$("#add-form").validate({
			rules:{
				title: {
					required: true
				},
				content: {
					required: true
				}

			},
			messages:{
			}});

	});

	//添加提交
	function noticeAdd() {
		if (!$("#add-form").validate().element($("#title"))
				|| !$("#add-form").validate().element($("#content"))) {
			return;
		}
		layer.load(1);
		$.ajax({
			url: $("#add-form").attr("action"),
			type: "POST",
			dataType: "json",
			data: $("#add-form").serialize(),
			async: true,
			success: function (msg) {
				layer.closeAll('loading');
				var data = eval(msg); //数据
				if (data.status == 0) {
					layer.msg(data.msg, {icon: 0, time: 2000});
					return false;
				} else {
					layer.msg("添加成功", {icon: 0, time: 2000});
					setTimeout(function () {
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.location.replace(parent.location.href);
						parent.layer.close(index); //再执行关闭
					}, 2000);
				}

			}, error: function (XMLHttpRequest, textStatus, errorThrown, data) {
				layer.closeAll('loading');
				layer.msg("error:" + errorThrown + "   " + "textStatus:" + textStatus);

			}
		});
	}

</script>
</body>
</html>