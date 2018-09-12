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
<form id="edit-form" action="/backAdmin/receiveEdit" method="post" onreset='formReset(this);' class="form form-horizontal">
	<input type="hidden" name="id" value="${receive.id}">
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>姓名</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="userName" name="userName" value="${user.userName}" placeholder="您的姓名" disabled />
		</div>
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>接站送站</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="checkbox" id="isSend" style="margin-top: 0px;" value="1" <c:if test="${receive.isSend == 1 || receive.id == null || receive.id == ''}">checked</c:if> name="isSend">
			<label for="isSend" style="font-size: 12px;">需要接送站</label>
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>身份证号</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="idNo" name="idNo" placeholder="身份证号" value="${user.idNo}" disabled/>
		</div>
		<div id="arriveTypeLabel" class="form-label col-xs-2 col-sm-2 size-S">出发方式</div>
		<div id="arriveTypeForm" class="formControls col-xs-4 col-sm-4">
			<input type="radio" id="arriveType1" name="arriveType" value="1" <c:if test="${receive.arriveType == 1|| receive.id == null || receive.id == ''}">checked</c:if> onclick="changeArriveType(1)"/>飞机
			<input type="radio" id="arriveType2" name="arriveType" value="2" <c:if test="${receive.arriveType == 2}">checked</c:if> onclick="changeArriveType(2)" style="margin-left: 10px"/>火车
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>性别</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="radio" name="gender" value="1" disabled <c:if test="${user.gender == 1}">checked</c:if>/>男
			<input type="radio" name="gender" value="2" style="margin-left: 10px" disabled <c:if test="${user.gender == 2}">checked</c:if>/>女
		</div>
		<div id="arriveFlightNoLabel" class="form-label col-xs-2 col-sm-2 size-S">
			<c:choose>
				<c:when test="${receive.arriveType == 2}">出发车次</c:when>
				<c:otherwise>航班号</c:otherwise>
			</c:choose>
		</div>
		<div id="arriveFlightNoForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="arriveFlightNo" name="arriveFlightNo" value="${receive.arriveFlightNo}" />
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>手机号码</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="userPhone" name="userPhone" placeholder="手机号码" value="${user.phone}" disabled/>
		</div>
		<div id="arriveTimeLabel" class="form-label col-xs-2 col-sm-2 size-S">预计到达</div>
		<div id="arriveTimeForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				   id="arriveTime" name="arriveTime" value="${receive.arriveTime}" class="input-text Wdate size-S" style="width:180px;">
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S">微信号码</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="wxNo" name="wxNo" value="${receive.wxNo}" placeholder="微信号码" />
		</div>
		<div id="arriveAirportLabel" class="form-label col-xs-2 col-sm-2 size-S">
			<c:choose>
				<c:when test="${receive.arriveType == 2}">到达车站</c:when>
				<c:otherwise>到达机场</c:otherwise>
			</c:choose>
		</div>
		<div id="arriveAirportForm" class="formControls col-xs-4 col-sm-4">
			<select id="arriveAirport" name="arriveAirport" class="select-box radius">
				<c:choose>
					<c:when test="${receive.arriveType == 2}">
						<option value="2" <c:if test="${receive.arriveAirport == 2}">selected</c:if>>哈尔滨站</option>
						<option value="3" <c:if test="${receive.arriveAirport == 3}">selected</c:if>>哈尔滨西站</option>
						<option value="4" <c:if test="${receive.arriveAirport == 4}">selected</c:if>>哈尔滨东站</option>
						<option value="5" <c:if test="${receive.arriveAirport == 5}">selected</c:if>>哈尔滨北站</option>
					</c:when>
					<c:otherwise>
						<option value="1" <c:if test="${receive.arriveAirport == 1}">selected</c:if>>哈尔滨太平国际机场</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>住宿要求</div>
		<div class="formControls col-xs-4 col-sm-4">
			<select id="bedRequire" name="bedRequire" class="select-box radius">
				<option value="1" <c:if test="${receive.bedRequire == 1}">selected</c:if>>标准间（2人合住）</option>
				<option value="2" <c:if test="${receive.bedRequire == 2}">selected</c:if>>单人间（1人独住）</option>
				<option value="3" <c:if test="${receive.bedRequire == 3}">selected</c:if>>不需要安排住宿</option>
			</select>
		</div>
		<div id="returnTypeLabel" class="form-label col-xs-2 col-sm-2 size-S">回程方式</div>
		<div id="returnTypeForm" class="formControls col-xs-4 col-sm-4">
			<input id="returnType1" type="radio" name="returnType" <c:if test="${receive.returnType == 1|| receive.id == null || receive.id == ''}">checked</c:if> value="1" onclick="changeReturnType(1)"/>飞机
			<input id="returnType2" type="radio" name="returnType" <c:if test="${receive.returnType == 2}">checked</c:if> value="2" style="margin-left: 10px" onclick="changeReturnType(2)"/>火车
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>代表单位</div>
		<div class="formControls col-xs-4 col-sm-4">
			<select id="representCollege" name="representCollege" class="select-box radius">
				<c:forEach items="${collegeList}" var="college">
					<option value="${college.name}" <c:if test="${college.name == receive.representCollege}">selected</c:if>>${college.name}</option>
				</c:forEach>
			</select>
		</div>
		<div id="returnFlightNoLabel" class="form-label col-xs-2 col-sm-2 size-S">
			<c:choose>
				<c:when test="${receive.returnType == 2}">回程车次</c:when>
				<c:otherwise>航班号</c:otherwise>
			</c:choose>
		</div>
		<div id="returnFlightNoForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="returnFlightNo" name="returnFlightNo" value="${receive.returnFlightNo}"/>
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>您的职务</div>
		<div class="formControls col-xs-4 col-sm-4">
			<input type="text" class="input-text size-S radius" id="work" name="work" value="${receive.work}" placeholder="您的职务" />
		</div>
		<div id="returnTimeLabel" class="form-label col-xs-2 col-sm-2 size-S">出发时间</div>
		<div id="returnTimeForm" class="formControls col-xs-4 col-sm-4">
			<input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				   id="returnTime" name="returnTime" value="${receive.returnTime}" class="input-text Wdate size-S" style="width:180px;">
		</div>
	</div>
	<div class="row cl">
		<div class="form-label col-xs-2 col-sm-2 size-S">备注</div>
		<div class="formControls col-xs-4 col-sm-4">
			<textarea name="remark" id="remark" cols="" rows="" class="textarea radius" style="font-size: 12px;" placeholder="备注">${receive.remark}</textarea>
		</div>
		<div id="returnAirportLabel" class="form-label col-xs-2 col-sm-2 size-S">
			<c:choose>
				<c:when test="${receive.returnType == 2}">回程车站</c:when>
				<c:otherwise>回程起飞机场</c:otherwise>
			</c:choose>
		</div>
		<div id="returnAirportForm" class="formControls col-xs-4 col-sm-4">
			<select id="returnAirport" name="returnAirport" class="select-box radius">
				<c:choose>
					<c:when test="${receive.returnType == 2}">
						<option value="2" <c:if test="${receive.returnAirport == 2}">selected</c:if>>哈尔滨站</option>
						<option value="3" <c:if test="${receive.arriveAirport == 3}">selected</c:if>>哈尔滨西站</option>
						<option value="4" <c:if test="${receive.arriveAirport == 4}">selected</c:if>>哈尔滨东站</option>
						<option value="5" <c:if test="${receive.arriveAirport == 5}">selected</c:if>>哈尔滨北站</option>
					</c:when>
					<c:otherwise>
						<option value="1" <c:if test="${receive.arriveAirport == 1}">selected</c:if>>哈尔滨太平国际机场</option>
					</c:otherwise>
				</c:choose>
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

		var isSend = '${receive.isSend}';
		if(isSend != '' && isSend != 1){
			hideSendElements();
		}
	});

	$("#isSend").change(function(event){
		if(event.currentTarget.checked){
			showSendElements();
		}else{
			hideSendElements();
		}
	});

	/*前端表单校验*/
	$("#edit-form").validate({
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