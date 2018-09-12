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
	<script type="text/javascript" src="/backAdmin/js/receive.js"></script>
	<link href="/static/lib/jquery.select2/css/select2.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/static/lib/jquery.select2/js/select2.min.js"></script>
	<script type="text/javascript" src="/static/lib/jquery.select2/js/i18n/zh-CN.js"></script>
	<title>继续教育学院会务中心</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
</head>
<body>
<form id="add-form" action="/backAdmin/receiveAdd" method="post" onreset='formReset(this);' class="form form-horizontal">
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S">选择用户</div>
		<div class="formControls col-xs-4 col-sm-4">
			<select id="userId" name="userId" class="select-box radius" onchange="changeUser()">
				<option value="">未选择</option>
				<c:forEach items="${userList}" var="user">
					<option value="${user.id}">${user.userName} </option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>姓名</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="userName" name="userName" placeholder="您的姓名" disabled/>
		</div>
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>接站送站</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="checkbox" id="isSend" style="margin-top: 0px;" value="1" name="isSend" checked>
			<label for="isSend" style="font-size: 12px;">需要接送站</label>
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>身份证号</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="idNo" name="idNo" placeholder="身份证号" disabled/>
		</div>
		<div id="arriveTypeLabel" class="form-label col-xs-2 col-sm-2 size-S">出发方式</div>
		<div id="arriveTypeForm" class="formControls col-xs-4 col-sm-4">
			<input type="radio" id="arriveType1" name="arriveType" value="1" onclick="changeArriveType(1)" checked/>飞机
			<input type="radio" id="arriveType2" name="arriveType" value="2" style="margin-left: 10px" onclick="changeArriveType(2)"/>火车
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>性别</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="radio" name="gender" value="1" disabled/>男
			<input type="radio" name="gender" value="2" style="margin-left: 10px" disabled/>女
		</div>
		<div id="arriveFlightNoLabel" class="form-label col-xs-2 col-sm-2 size-S">
			航班号
		</div>
		<div id="arriveFlightNoForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="arriveFlightNo" name="arriveFlightNo"  />
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>手机号码</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="userPhone" name="userPhone" placeholder="手机号码" disabled/>
		</div>
		<div id="arriveTimeLabel" class="form-label col-xs-2 col-sm-2 size-S">预计到达</div>
		<div id="arriveTimeForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				   id="arriveTime" name="arriveTime" class="input-text Wdate size-S" style="width:180px;">
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S">微信号码</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="wxNo" name="wxNo" placeholder="微信号码" />
		</div>
		<div id="arriveAirportLabel" class="form-label col-xs-2 col-sm-2 size-S">
			到达机场
		</div>
		<div id="arriveAirportForm" class="formControls col-xs-4 col-sm-4">
			<select id="arriveAirport" name="arriveAirport" class="select-box radius">
				<option value="1" >哈尔滨太平国际机场</option>
			</select>
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>住宿要求</div>
		<div class="formControls col-xs-4 col-sm-4">
			<select id="bedRequire" name="bedRequire" class="select-box radius">
				<option value="1">标准间（2人合住）</option>
				<option value="2">单人间（1人独住）</option>
				<option value="3">不需要安排住宿</option>
			</select>
		</div>
		<div id="returnTypeLabel" class="form-label col-xs-2 col-sm-2 size-S">回程方式</div>
		<div id="returnTypeForm" class="formControls col-xs-4 col-sm-4">
			<input id="returnType1" type="radio" name="returnType" value="1" onclick="changeReturnType(1)" checked/>飞机
			<input id="returnType2" type="radio" name="returnType" value="2" style="margin-left: 10px" onclick="changeReturnType(2)"/>火车
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>代表单位</div>
		<div class="formControls col-xs-4 col-sm-4">
			<select id="representCollege" name="representCollege" class="select-box radius">
				<c:forEach items="${collegeList}" var="college">
					<option value="${college.name}" >${college.name}</option>
				</c:forEach>
			</select>
		</div>
		<div id="returnFlightNoLabel" class="form-label col-xs-2 col-sm-2 size-S">
			航班号
		</div>
		<div id="returnFlightNoForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="returnFlightNo" name="returnFlightNo" />
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>您的职务</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="work" name="work" placeholder="您的职务" />
		</div>
		<div id="returnTimeLabel" class="form-label col-xs-2 col-sm-2 size-S">出发时间</div>
		<div id="returnTimeForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				   id="returnTime" name="returnTime" class="input-text Wdate size-S" style="width:180px;">
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S">备注</div>
		<div class="formControls col-xs-4 col-sm-4">
			<textarea name="remark" id="remark" cols="" rows="" class="textarea radius" style="font-size: 12px;" placeholder="备注"></textarea>
		</div>
		<div id="returnAirportLabel" class="form-label col-xs-2 col-sm-2 size-S">
			回程起飞机场
		</div>
		<div id="returnAirportForm" class="formControls col-xs-4 col-sm-4">
			<select id="returnAirport" name="returnAirport" class="select-box radius">
				<option value="1" <c:if test="${receive.arriveAirport == 1}">selected</c:if>>哈尔滨太平国际机场</option>

			</select>
		</div>
	</div>

	<div class="row cl">
		<div>
			<div class="form-label col-xs-1 col-sm-1 size-S"></div>
			<div class="formControls col-xs-5 col-sm-5">
				注：前面标注 <span class="c-red">*</span> 的为必填项目，请如实填写！
			</div>
			<div class="form-label col-xs-2 col-sm-2 size-S"></div>
			<div class="formControls col-xs-4 col-sm-4">
				<input class="btn btn-primary radius" type="submit" value="提交回执" id="submitA">
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">

	function closeIframe() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	$(document).ready(function() {
		$("#representCollege").select2();
		$("#userId").select2();
	});

	$("#isSend").change(function(event){
		if(event.currentTarget.checked){
			showSendElements();
		}else{
			hideSendElements();
		}
	});

	function changeUser() {
		var userId = $("#userId").val();
		$.ajax({
			url: '/backAdmin/getUserById',
			type: "POST",
			dataType: "json",
			data: {"id":userId},
			async: true,
			success: function (msg) {
				layer.closeAll('loading');
				var data = eval(msg); //数据
				if (data.status == 0) {
					layer.msg(data.msg, {icon: 0, time: 2000});
					return false;
				} else {
					$("#userName").val(data.data.userName);
					$("#idNo").val(data.data.idNo);
					$("#userPhone").val(data.data.phone);
					if(data.data.gender == "1") {
						$("input[name='gender']:eq(0)").attr("checked", 'checked');
					}else{
						$("input[name='gender']:eq(1)").attr("checked", 'checked');
					}
				}

			}, error: function (XMLHttpRequest, textStatus, errorThrown, data) {
				layer.closeAll('loading');
				layer.msg("error:" + errorThrown + "   " + "textStatus:" + textStatus);

			}
		});
	}

	/*前端表单校验*/
	$("#add-form").validate({
		rules:{
			bedRequire:{
				required:true
			},
			representCollege:{
				required:true
			},
			work:{
				required:true
			}
		},
		messages:{
		},
		submitHandler:function(form){
			layer.load(1);
			$.ajax({
				url:$("#add-form").attr("action"),
				type:"POST",
				dataType: "json",
				data:$("#add-form").serialize(),
				async: true,
				success: function(msg) {
					layer.closeAll('loading');
					var data = eval(msg); //数据
					if(data.status == 0){
						layer.msg(data.msg,{icon:0,time:2000});
						return false;
					}else{
						layer.msg("添加成功",{icon:0,time:2000});
						setTimeout(function () {
							location.replace(location.href);
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