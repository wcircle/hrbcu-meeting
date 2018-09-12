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
    <link href="/indexs/css/index.css" rel="stylesheet" type="text/css" />
    <link href="/indexs/css/main.css" rel="stylesheet" type="text/css" />
    <link href="/indexs/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/indexs/js/main.js"></script>
    <%@ include file="/backAdmin/common/include.jsp" %>
    <title>继续教育学院会务中心-后台管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <SCRIPT type=text/javascript>
        $(function(){
            topTab("_College",true);
        })

    </SCRIPT>

</head>
<body>
    <div class="page">
        <div class="sy_top">
            <div class="sy_top_nav"><img src="/indexs/images/head-logo.png"></div>
        </div>
        <div class="clear bg_x nav">
            <div class="left bg nav_1">
                <ul>
                    <li class="nav_li_line" id="_index"><a href="/backAdmin/index.html">会议通知</a></li>
                    <li class="nav_li_line" id="_receive"><a href="/backAdmin/receive.html">回执管理</a></li>
                    <li class="nav_li_line" id="_eat"><a href="/backAdmin/eat.html">就餐管理</a></li>
                    <li class="nav_li_line" id="_sleep"><a href="/backAdmin/sleep.html">住宿管理</a></li>
                    <li class="nav_li_line" id="_meetingData"><a href="/backAdmin/meetingData.html">会议资料</a></li>
                    <li class="nav_li_line" id="_College"><a href="/backAdmin/college.html">学院管理</a></li>
                </ul>
                <div class="right bg nav_2"></div>
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
                <div class="location"><b class="bg_i ico"></b>您的位置：<a href="/backAdmin/college.html">学院管理</a> </div>
                <div class="block2 sub_ct">
                    <form id="search-form" class="text-l form form-horizontal" method="post" action="/backAdmin/getCollegeList">
                        <input id="page-no" name="page" type="hidden" value="1">
                        <div class="row cl">
                            <div class="form-label col-xs-2 col-sm-3 size-S pr5 pl5" >学院名称</div>
                            <div class="formControls col-xs-4 col-sm-4 size-S pr5 pl5">
                                <input class="input-text radius size-S" type="text" name="collegeName" value="${collegeSearch.collegeName}" placeholder="学院名称" />
                            </div>
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5"></div>
                            <div class="formControls col-xs-2 col-sm-2">
                                <button type="submit" class="btn btn-success size-S radius" style="margin: auto">
                                    <i class="Hui-iconfont" style="line-height: 20px;"></i> 搜索
                                </button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-border table-bordered table-hover table-bg table-sort mt-20">
                        <thead>
                        <tr>
                            <th scope="col" colspan="14">
                                <span class="l" style="margin-right: 10px">
                                    <a href="javascript:collegeAdd()" data-title="添加通知" class="btn btn-primary size-S radius" style="color:white;">
                                        <i class="Hui-iconfont" style="line-height: 20px;">&#xe600;</i> 添加学院
                                    </a>
                                </span>
                                <a class="btn btn-success radius r" style="line-height:1.6em;color: white" href="javascript:location.replace('/backAdmin/college.html');" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
                            </th>
                        </tr>
                        <tr class="text-c">
                            <th>序号</th>
                            <th>学院名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${collegeList}" var="college" varStatus="status">
                                <tr class="text-c">
                                    <td>${status.index+1}</td>
                                    <td>${college.name}</td>
                                    <td>
                                        <a title="修改" href="javascript:collegeEdit('${college.id}')" data-title="修改" class="ml-5" style="text-decoration:none">
                                            <i class="Hui-iconfont">&#xe6df;</i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div id="pagination" class="text-c padding-top-10"></div>
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
        laypage({
            cont: 'pagination',
            pages: ${page.totalPageNum},
            curr: ${page.currentPageNum},
            skip: true,
            jump: function(e, first){ //触发分页后的回调
                if(!first){ //一定要加此判断，否则初始时会无限刷新
                    $('#page-no').val(e.curr);
                    $('#search-form').submit();
                }
            }
        });

        function collegeAdd(){
            layer.open({
                type: 2,
                title: '添加学院',
                shadeClose: true,
                shade: 0.8,
                area: ['50%', '40%'],
                content: '/backAdmin/collegeAdd.html'
            });
        }

        function collegeEdit(id){
            layer.open({
                type: 2,
                title: '修改学院',
                shadeClose: true,
                shade: 0.8,
                area: ['50%', '40%'],
                content: '/backAdmin/collegeEdit.html/'+id
            });
        }

    </script>
</body>
</html>
