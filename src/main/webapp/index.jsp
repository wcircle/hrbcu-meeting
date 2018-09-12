<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    <title>继续教育学院会务中心</title>
    <script type="text/javascript">
        if(${userVo != null}){
            if(${userVo.userType == 1}){
                window.location.href = "<%=basePath%>index/index.html";
            }else{
                window.location.href = "<%=basePath%>backAdmin/index.html";
            }
        }else {
            window.location.href = "<%=basePath%>index/index.html";
        }
    </script>
</head>
<body>
</body>
</html>
