<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <LINK rel="Bookmark" href="/favicon.ico">
    <link rel="shortcut icon" type="image/x-icon" href="/indexs/images/icon32.png">
    <link rel="icon" type="image/x-icon" href="/indexs/images/icon32.png">
    <%@ include file="/backAdmin/common/include.jsp" %>
    <link href="/indexs/css/index.css" rel="stylesheet" type="text/css" />
    <link href="/indexs/css/main.css" rel="stylesheet" type="text/css" />
    <link href="/indexs/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/indexs/js/main.js"></script>
    <link href="/static/lib/jquery.select2/css/select2.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/static/lib/jquery.select2/js/select2.min.js"></script>
    <script type="text/javascript" src="/static/lib/jquery.select2/js/i18n/zh-CN.js"></script>

    <title>继续教育学院会务中心</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <SCRIPT type=text/javascript>
        $(function(){
            topTab("_reg",true);
        })

    </SCRIPT>

</head>
<body onload="loadCode()">
<div class="page">
    <div class="sy_top">
        <div class="sy_top_nav"><img src="/indexs/images/head-logo.png"></div>
    </div>
    <div class="clear bg_x nav">
        <div class="left bg nav_1 ">
            <ul>
                <li class="nav_li_line" id="_index"><a href="/index/index.html">会务首页</a></li>
                <li class="nav_li_line" id="_reg"><a href="/receiveManage/receive.html">会议回执</a></li>
            </ul>
            <div class="right bg  nav_2"></div>
        </div>
    </div>
    <div class="bg_x top_notice">
    </div>

    <div class="index_bd">
        <div class="i_col_l">
            <div class="sub_menu">
                <div class="block2" id="login">
                    <c:choose>
                        <c:when test="${userVo != null}">
                            <h3 id="login_title">登录用户信息</h3>
                            <ul id="user_ul">
                                <li>姓&nbsp;&nbsp;&nbsp;&nbsp;名：${userVo.userName}</li>
                                <li>手&nbsp;&nbsp;&nbsp;&nbsp;机：${userVo.phone}</li>
                                <li>性&nbsp;&nbsp;&nbsp;&nbsp;别：
                                    <c:if test="${userVo.gender == 1}">男</c:if>
                                    <c:if test="${userVo.gender == 2}">女</c:if>
                                </li>
                                <input type="button" class="submit" value="用户退出" onclick="loginOut()">
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <h3 id="login_title">已回执用户登录</h3>
                            <ul id="login_ul">
                                <form id="f_login">
                                    <label>手&nbsp;&nbsp;&nbsp;&nbsp;机：
                                        <input type="text" id="phone" name="phone" class="login_ipt" placeholder="手机号码">
                                    </label>
                                    <label>
                                        密&nbsp;&nbsp;&nbsp;&nbsp;码：
                                        <input type="password" id="password" name="password" class="login_ipt" placeholder="密码">
                                    </label>
                                    <label>
                                        验证码：
                                        <input class="login_ipt" type="text" id="strCode" name="strCode" placeholder="验证码"  style="width:100px;">
                                        <img id="codeImage"  onclick="chageCode()"/>
                                    </label>
                                    <label style="float: right">
                                        <a href="#">如果没有账号，请先注册</a>
                                    </label>
                                    <input type="button" class="submit" value="用户登录" onclick="login()">
                                </form>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="i_col_r">
            <div class="location"><b class="bg_i ico"></b>您的位置：<a href="/index/index.html">首页</a> &gt; ${meeting.meetingName}</div>
            <div class="sub_ct block2 sub_li">
                <h3 style="color:black">${meeting.meetingName}</h3>
                <form id="add-form" action="/receiveManage/receiveSubmit" method="post" onreset='formReset(this);' class="form form-horizontal">
                    <input type="hidden" name="id" value="${receive.id}">
                    <div class="row cl">
                        <div class="form-label col-xs-2 col-sm-2 size-S"><span class="c-red">*</span>您的姓名</div>
                        <div class="formControls col-xs-4 col-sm-4">
                            <input type="text" class="input-text size-S radius" id="userName" name="userName" value="${userVo.userName}" placeholder="您的姓名" disabled/>
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
                            <input type="text" class="input-text size-S radius" id="idNo" name="idNo" value="${userVo.idNo}" placeholder="身份证号" disabled/>
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
                            <input type="radio" name="gender" value="1" <c:if test="${userVo.gender == 1}">checked</c:if> disabled/>男
                            <input type="radio" name="gender" value="2" <c:if test="${userVo.gender == 2}">checked</c:if> style="margin-left: 10px" disabled/>女
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
                            <input type="text" class="input-text size-S radius" id="userPhone" name="userPhone" value="${userVo.phone}" placeholder="手机号码" disabled/>
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
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear bg_x foot">
        哈尔滨商业大学 继续教育学院会务中心 版权所有<br>
        联系我们：0451-84604822
    </div>

</div>
<script type="text/javascript">

    $("#isSend").change(function(event){
        if(event.currentTarget.checked){
            showSendElements();
        }else{
            hideSendElements();
        }
    });

    function hideSendElements(){
        //隐藏
        $("#arriveTypeLabel").hide();
        $("#arriveTypeForm").hide();
        $("#arriveFlightNoLabel").hide();
        $("#arriveFlightNoForm").hide();
        $("#arriveTimeLabel").hide();
        $("#arriveTimeForm").hide();
        $("#arriveAirportLabel").hide();
        $("#arriveAirportForm").hide();
        $("#returnTypeLabel").hide();
        $("#returnTypeForm").hide();
        $("#returnFlightNoLabel").hide();
        $("#returnFlightNoForm").hide();
        $("#returnTimeLabel").hide();
        $("#returnTimeForm").hide();
        $("#returnAirportLabel").hide();
        $("#returnAirportForm").hide();
        //清空数据
        $("#arriveType1").attr("checked","checked");
        $("#arriveType2").removeAttr("checked");
        $("#arriveFlightNo").val("");
        $("#arriveTime").val("");
        $("#arriveAirport").get(0).selectedIndex=0;
        $("#returnType1").attr("checked","checked");
        $("#returnType2").removeAttr("checked");
        $("#returnFlightNo").val("");
        $("#returnTime").val("");
        $("#returnAirport").get(0).selectedIndex=0;
    }

    function showSendElements(){
        $("#arriveTypeLabel").show();
        $("#arriveTypeForm").show();
        $("#arriveFlightNoLabel").show();
        $("#arriveFlightNoForm").show();
        $("#arriveTimeLabel").show();
        $("#arriveTimeForm").show();
        $("#arriveAirportLabel").show();
        $("#arriveAirportForm").show();
        $("#returnTypeLabel").show();
        $("#returnTypeForm").show();
        $("#returnFlightNoLabel").show();
        $("#returnFlightNoForm").show();
        $("#returnTimeLabel").show();
        $("#returnTimeForm").show();
        $("#returnAirportLabel").show();
        $("#returnAirportForm").show();
    }

    /**
     *
     * @param type
     * @param code
     */
    function changeArriveType(type){
        if(type == 1){
            $("#arriveFlightNoLabel").text("航班号");
            $("#arriveAirportLabel").text("到达机场");
            $("#arriveAirport").html('<option value="1">哈尔滨太平国际机场</option>');
        }else{
            $("#arriveFlightNoLabel").text("出发车次");
            $("#arriveAirportLabel").text("到达车站");
            var str= '<option value="2" >哈尔滨站</option>'
                    +'<option value="3" >哈尔滨西站</option>'
                    +'<option value="4" >哈尔滨东站</option>'
                    +'<option value="5" >哈尔滨北站</option>';
            $("#arriveAirport").html(str);
        }

        $("#arriveFlightNo").val("");
        $("#arriveTime").val("");
        $("#arriveAirport").get(0).selectedIndex=0;
    }

    /**
     *
     * @param type
     * @param code
     */
    function changeReturnType(type){
        if(type == 1){
            $("#returnFlightNoLabel").text("航班号");
            $("#returnAirportLabel").text("回程起飞机场");
            $("#returnAirport").html('<option value="1">哈尔滨太平国际机场</option>');
        }else{
            $("#returnFlightNoLabel").text("回程车次");
            $("#returnAirportLabel").text("回程车站");
            var str= '<option value="2" >哈尔滨站</option>'
                    +'<option value="3" >哈尔滨西站</option>'
                    +'<option value="4" >哈尔滨东站</option>'
                    +'<option value="5" >哈尔滨北站</option>';
            $("#returnAirport").html(str);
        }
        $("#returnFlightNo").val("");
        $("#returnTime").val("");
        $("#returnAirport").get(0).selectedIndex=0;
    }

    $(document).ready(function () {
        $("#representCollege").select2();

        var isSend = '${receive.isSend}';
        if(isSend != '' && isSend != 1){
            hideSendElements();
        }
    });

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
            layer.confirm("确定提交回执吗？",function(index) {
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
                            layer.msg("提交成功", {icon: 0, time: 2000});
                            setTimeout(function () {
                                location.replace(location.href);
                            }, 2000);
                        }

                    }, error: function (XMLHttpRequest, textStatus, errorThrown, data) {
                        layer.closeAll('loading');
                        layer.msg("error:" + errorThrown + "   " + "textStatus:" + textStatus);

                    }
                });

            });
        }
    });

    function loadCode() {
        $('#codeImage').attr('src','/login/authCode?abc='+Math.random());
        $('#strCode').val("");
    }
</script>
</body>
</html>
