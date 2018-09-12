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

    <title>继续教育学院会务中心</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <SCRIPT type=text/javascript>
        $(function(){
            topTab("_index",true);
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
            <div class="location"><b class="bg_i ico"></b>您的位置：<a href="/index/index.html">首页</a> &gt; ${meetingNoticeResult.meetingNotice.title}</div>
            <div class=" block2 sub_ct">
                <div style="text-align: center"><b>${meetingNoticeResult.meetingNotice.title}</b></div>
                <div><pre>${meetingNoticeResult.meetingNotice.content}</pre></div>
                <c:if test="${meetingNoticeResult.fileList != null && meetingNoticeResult.fileList.size() > 0}">
                    <div style="margin-top: 10px;">
                        附件：
                        <c:forEach items="${meetingNoticeResult.fileList}" var="fileTemp">
                            <div>
                                <a href="javascript:download('${fileTemp.filePath}')">${fileTemp.fileName}</a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <div class="clear"></div>
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
    function download(filePath) {
        window.location.href = filePath;
    }
</script>
</body>
</html>
