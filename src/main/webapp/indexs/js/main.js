function ellipsis(str, len){
    if ((!len) || str.length <= len) { return str; }
    else { return str.substring(0, len) + "..."; }
}

function topTab(cls, fix){
    if (document.getElementById(cls)) {
        document.getElementById(cls).className = "main_tab_on";
    }
    if (fix) {
        $("#" + cls).append("<div id='top_tab_bug'></div>");
        $('#top_tab_bug').show()
    }
}

function chageCode() {
    $('#codeImage').attr('src','/login/authCode?abc='+Math.random());
    $('#strCode').val("");
}

function login(){
    var phone = $("#phone").val();
    var password = $("#password").val();
    var strCode = $("#strCode").val();
    if(phone == null || "" == phone){       //验证用户名是否为空
        layer.msg('请输入手机号码！',{icon:0,time:2000});
        $("#phone").focus();
        return false;
    }
    if(phone.length != 11){
        layer.msg("请输入11位手机号码",{icon:0,time:2000});
        $("#phone").focus();
        return false;
    }
    if(!checkNumber(phone)){
        layer.msg("请输入正确的手机号码",{icon:0,time:2000});
        $("#phone").focus();
        return false;
    }

    if(password==""){       //验证密码是否为空
        layer.msg('请输入密码！',{icon:0,time:2000});
        $("#password").focus();
        return false;
    }
    //验证密码是否为数字、字母、下划线
    var reg = /^[\w]{6,12}$/;//这个是正则表达式
    if(!password.match(reg)){
        layer.msg('密码格式不正确！',{icon:0,time:2000});
        $("#password").focus();
        return false;
    }

    if(strCode==""){       //验证密码是否为空
        layer.msg('请输入验证码！',{icon:0,time:2000});
        $("#strCode").focus();
        return false;
    }

    $.ajax({
        url: "/login/login",
        type: "post",
        data:{"phone":phone,"password":password,"strCode":strCode},
        dataType: "json",
        async: true,
        success: function(msg) {
            layer.closeAll('loading');
            var data = eval(msg); //数据
            if(data.status == 0){
                layer.msg(data.msg,{icon:0,time:2000});
                $("#phone").focus();
                return false;
            }else{
                var userType = eval(msg).data; //数据
                if(userType == 1) {
                    window.location.href = "/index/index.html";//跳转到主页
                }else if(userType == 2){
                    window.location.href = "/backAdmin/index.html";//跳转到主页
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.closeAll('loading');
            layer.msg('系统异常，请联系管理员！',{icon:0,time:2000});
        }
    });
}

/*
 * 登录退出
 */
function loginOut() {
    layer.confirm('确认要退出吗?',{icon: 3, title:'提示'}, function(index){
        layer.closeAll('dialog');
        layer.closeAll('loading');
        layer.load(1);

        var param="/login/loginOut";

        $.ajax({
            url: param,
            type: "post",
            data:{},
            dataType: "json",
            async: true,
            success: function(msg) {
                var data = eval(msg); //数据
                layer.closeAll('loading');
                if(data.status == "0"){
                    layer.msg(data.msg,{icon:0,time:2000});
                    return false;
                }else{
                    window.location.href = "/index/index.html";//跳转到主页
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                layer.closeAll('loading');
                layer.msg('系统异常，请联系管理员！',{icon:0,time:2000});
            },
            complete: function(XMLHttpRequest, textStatus) {
                layer.closeAll('loading');
                this; // 调用本次AJAX请求时传递的options参数
            }
        });
    });
}

function toRegister() {
    layer.open({
        type: 2,
        title: '注册',
        shadeClose: true,
        shade: 0.8,
        area: ['50%', '70%'],
        content: '/index/register.html' //iframe的url
    });
}

/**
 * 验证字符串是否是数字
 */
function checkNumber(theObj) {
    var reg = /^[0-9]+.?[0-9]*$/;
    if (reg.test(theObj)) {
        return true;
    }
    return false;
}



