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
            topTab("_sleep",true);
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
                <div class="location"><b class="bg_i ico"></b>您的位置：<a href="/backAdmin/sleep.html">住宿</a> </div>
                <div class=" block2 sub_ct">
                    <form id="search-form" class="text-l form form-horizontal" method="post" action="/backAdmin/getSleepList">
                        <input id="page-no" name="page" type="hidden" value="1">
                        <div class="row cl">
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5" >姓名</div>
                            <div class="formControls col-xs-2 col-sm-2 size-S pr5 pl5">
                                <input class="input-text radius size-S" type="text" name="userName" value="${sleepSearch.userName}" placeholder="姓名" />
                            </div>
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5">代表院校</div>
                            <div class="formControls col-xs-3 col-sm-3 size-S pr5 pl5">
                                <select id="representCollege" name="representCollege" class="select-box radius size-S">
                                    <option value="">未选择</option>
                                    <c:forEach items="${collegeList}" var="college">
                                        <option value="${college.name}" <c:if test="${college.name == sleepSearch.representCollege}">selected</c:if>>${college.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5">住宿地点</div>
                            <div class="formControls col-xs-3 col-sm-3 size-S pr5 pl5">
                                <input class="input-text radius size-S" type="text" name="address" value="${sleepSearch.address}" placeholder="住宿地点" />
                            </div>
                        </div>
                        <div class="row cl">
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5" >房间号</div>
                            <div class="formControls col-xs-2 col-sm-2 size-S pr5 pl5">
                                <input class="input-text radius size-S" type="text" name="roomNo" value="${sleepSearch.roomNo}" placeholder="房间号" />
                            </div>
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5">住宿日期</div>
                            <div class="formControls col-xs-3 col-sm-3 size-S pr5 pl5">
                                <input type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
                                       id="sleepDate" name="sleepDate" value="${sleepSearch.sleepDate}" class="input-text Wdate size-S">
                            </div>
                            <div class="form-label col-xs-1 col-sm-1 size-S pr5 pl5">住宿序号</div>
                            <div class="formControls col-xs-2 col-sm-2 size-S pr5 pl5">
                                第<input class="input-text radius size-S" type="number" name="sleepOrder" value="${sleepSearch.sleepOrder}" placeholder="住宿序号" />晚
                            </div>
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
                                    <a href="javascript:sleepAdd()" style="color: white" data-title="添加住宿" class="btn btn-primary size-S radius" >
                                        <i class="Hui-iconfont" style="line-height: 20px;">&#xe600;</i> 添加住宿
                                    </a>
                                </span>
                                <span class="l" style="margin-right: 10px">
                                    <a href="javascript:;" onclick="deleteSleep()" class="btn btn-danger size-S radius" style="color: white">
                                        <i class="Hui-iconfont" style="line-height: 20px;">&#xe6e2;</i> 批量删除
                                    </a>
                                </span>
                                <span class="l">
                                    <a href="javascript:;" onclick="exportExcel()" class="btn btn-warning size-S radius" style="color: white">
                                        <i class="Hui-iconfont" style="line-height: 20px;">&#xe644;</i> 批量导出
                                    </a>
                                </span>
                                <a class="btn btn-success radius r" style="line-height:1.6em;color: white" href="javascript:location.replace('/backAdmin/sleep.html');" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
                            </th>
                        </tr>
                        <tr class="text-c">
                            <th><input type="checkbox" name="searchAll"/> </th>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>手机号</th>
                            <th>住宿序号</th>
                            <th>地点</th>
                            <th>房间号</th>
                            <th>住宿日期</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sleepList}" var="sleep" varStatus="status">
                            <tr class="text-c">
                                <td><input type="checkbox" name="ids" value="${sleep.sleepId}"></td>
                                <td>${status.index+1}</td>
                                <td>${sleep.userName}</td>
                                <td>${sleep.phone}</td>
                                <td>第${sleep.sleepOrder}晚</td>
                                <td>${sleep.address}</td>
                                <td>${sleep.roomNo}</td>
                                <td>${sleep.sleepDate}</td>
                                <td>
                                    <a title="修改" href="javascript:sleepEdit('${sleep.sleepId}')" data-title="修改" class="ml-5" style="text-decoration:none">
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
        $(document).ready(function() {
            $("#representCollege").select2();

        });

        function sleepAdd() {
            layer.open({
                type: 2,
                title: '添加住宿',
                shadeClose: true,
                shade: 0.8,
                area: ['70%', '80%'],
                content: '/backAdmin/sleepAdd.html'
            });
        }

        function sleepEdit(id) {
            layer.open({
                type: 2,
                title: '修改住宿',
                shadeClose: true,
                shade: 0.8,
                area: ['70%', '80%'],
                content: '/backAdmin/sleepEdit.html/'+id
            });
        }

        //批量删除
        function deleteSleep(){
            layer.confirm("确定要批量删除吗？",function(index){
                var obj=$("input[name=ids]:checked");
                if(obj.length == 0){
                    layer.msg("请勾选需要删除的记录");
                    return;
                }
                //取到对象数组
                var ids='';
                for(var i=0; i<obj.length; i++){
                    ids+=obj[i].value+',';
                }
                layer.load(1);
                $.ajax({
                    type:"POST",
                    url:"/backAdmin/sleepDelete",
                    data:{"ids":ids},
                    dataType: "json",
                    success: function(msg) {
                        layer.closeAll('loading');
                        var data = eval(msg); //数据
                        if(data.status == 0){
                            layer.msg(data.msg,{icon:0,time:2000});
                            return false;
                        }else{
                            layer.msg("删除成功",{icon:0,time:2000});
                            setTimeout(function () {
                                location.replace("/backAdmin/sleep.html");
                            },2000);

                        }
                    },error: function(XMLHttpRequest,textStatus,errorThrown,data) {
                        layer.closeAll('loading');
                        layer.msg("删除失败![error:"+errorThrown+"   "+"textStatus:"+textStatus+']',{icon:2,time:3000});

                    }
                });
            });
        }

        function exportExcel() {
            layer.load(1);
            $.ajax({
                type:"POST",
                url:"/backAdmin/exportSleepList",
                data:$("#search-form").serialize(),
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
