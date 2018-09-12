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

    <title>继续教育学院会务中心-后台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <SCRIPT type=text/javascript>
        $(function(){
            topTab("_meetingData",true);
        })

    </SCRIPT>

</head>
<body>
    <div class="page">
        <div class="sy_top">
            <div class="sy_top_nav"><img src="/indexs/images/head-logo.png"></div>
        </div>
        <div class="clear bg_x nav">
            <div class="left bg nav_1 ">
                <ul>
                    <li class="nav_li_line" id="_index"><a href="/backAdmin/index.html">会议通知</a></li>
                    <li class="nav_li_line" id="_receive"><a href="/backAdmin/receive.html">回执管理</a></li>
                    <li class="nav_li_line" id="_eat"><a href="/backAdmin/eat.html">就餐管理</a></li>
                    <li class="nav_li_line" id="_sleep"><a href="/backAdmin/sleep.html">住宿管理</a></li>
                    <li class="nav_li_line" id="_meetingData"><a href="/backAdmin/meetingData.html">会议资料</a></li>
                    <li class="nav_li_line" id="_College"><a href="/backAdmin/college.html">学院管理</a></li>
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
                <div class="location"><b class="bg_i ico"></b>您的位置：<a href="/backAdmin/meetingData.html">会议资料管理</a> </div>
                <div class="panel panel-default mt-20" style="width: 450px;float: left;border: 1px solid #0154a0;">
                    <div class="panel-header" style="background: #619dff;color: white">会议回执、会议准备</div>
                    <div class="panel-body">
                        <div>
                            需要参会单位<span class="c-red"><ins>${result.collegeCount}</ins></span>个,已回执<span class="c-red"><ins>${result.receiveCollegeCount}</ins></span>个，未回执<span class="c-red"><ins>${result.noReceiveCollegeCount}</ins></span>个
                        </div>
                        <div>
                            总注册人数<span class="c-red"><ins>${result.totalRegisterCount}</ins></span>个
                        </div>
                        <div>
                            总回执人数<span class="c-red"><ins>${result.totalReceiveCount}</ins></span>个:<a href="/backAdmin/receive.html">回执数据管理</a>
                        </div>
                        <div>
                            今日新增注册<span class="c-red"><ins>${result.todayRegisterCount}</ins></span>人,今日新增回执<span class="c-red"><ins>${result.todayCreateReceiveCount}</ins></span>人
                        </div>
                    </div>
                </div>
                <div class="panel panel-default mt-20" style="width: 450px;float: right;border: 1px solid #0154a0;">
                    <div class="panel-header" style="background: #619dff;color: white">资料打印、数据导出</div>
                    <div class="panel-body">
                        <div>
                            打印花名单：<a href="javascript:exportCollege()"><ins>参会单位数据统计</ins></a>
                        </div>
                        <div>
                            打印用餐名单：<a href="/backAdmin/eat.html"><ins>批量打印</ins></a>，<a href="/backAdmin/eat.html"> <ins>按桌打印</ins></a>
                        </div>
                        <div>
                            导出回执数据：<a href="javascript:exportReceive('','')"><ins>导出全部回执数据</ins></a>，<a href="javascript:exportReceive('1','')"><ins>导出需要接站的名单</ins></a>，<a href="javascript:exportReceive('','1')"><ins>导出需要住宿的名单</ins></a>
                        </div>
                        <div>
                            导出通讯录：<a href="javascript:exportUser()"><ins>导出通讯录</ins></a>
                        </div>
                    </div>
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

        function exportReceive(isSend,isBedRequire) {
            layer.load(1);
            var data = {};
            if(isSend == '' && isBedRequire == ''){
                data = {}
            }else if (isSend == '1'){
                data = {"isSend":"1"};
            }else if (isBedRequire == '1'){
                data = {"isBedRequire":'1'}
            }
            $.ajax({
                type:"POST",
                url:"/backAdmin/exportReceiveList",
                data:data,
                dataType: "json",
                async: true,
                success: function(msg) {
                    layer.closeAll('loading');
                    var data = eval(msg); //数据
                    if(data.status == 0){
                        layer.msg(data.msg,{icon:0,time:2000});
                        return false;
                    }else{
                        window.location.href = data.data;

                    }
                },error: function(XMLHttpRequest,textStatus,errorThrown,data) {
                    layer.closeAll('loading');
                    layer.msg("导出失败![error:"+errorThrown+"   "+"textStatus:"+textStatus+']',{icon:2,time:3000});

                }
            });
        }

        function exportUser() {
            layer.load(1);
            $.ajax({
                type:"POST",
                url:"/backAdmin/exportUserList",
                data:{},
                dataType: "json",
                async: true,
                success: function(msg) {
                    layer.closeAll('loading');
                    var data = eval(msg); //数据
                    if(data.status == 0){
                        layer.msg(data.msg,{icon:0,time:2000});
                        return false;
                    }else{
                        window.location.href = data.data;

                    }
                },error: function(XMLHttpRequest,textStatus,errorThrown,data) {
                    layer.closeAll('loading');
                    layer.msg("导出失败![error:"+errorThrown+"   "+"textStatus:"+textStatus+']',{icon:2,time:3000});

                }
            });
        }
        
        function exportCollege() {
            layer.load(1);
            $.ajax({
                type:"POST",
                url:"/backAdmin/exportCollegeStatistics",
                data:{},
                dataType: "json",
                async: true,
                success: function(msg) {
                    layer.closeAll('loading');
                    var data = eval(msg); //数据
                    if(data.status == 0){
                        layer.msg(data.msg,{icon:0,time:2000});
                        return false;
                    }else{
                        window.location.href = data.data;

                    }
                },error: function(XMLHttpRequest,textStatus,errorThrown,data) {
                    layer.closeAll('loading');
                    layer.msg("导出失败![error:"+errorThrown+"   "+"textStatus:"+textStatus+']',{icon:2,time:3000});

                }
            });
        }
    </script>
</body>
</html>
