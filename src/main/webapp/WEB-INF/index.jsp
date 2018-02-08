<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1" />
        <title>配网整定计算</title>
        <script language="JavaScript" src="static/js/jquery-1.11.0.min.js"></script>
        <script language="javascript">
        var contextPath = "${contextPath}";
        console.log(contextPath)
        </script>
        <%--<script language="JavaScript" type="text/javascript" src="./plugs/js/jquery-2.1.1.js"></script>--%>
    </head>


<%--<script type="text/javascript" src="/plugs/js/jquery-2.1.1.js"></script>--%>

<body>
<form id="userForm" action="/user/find" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password">
    <button type="button" onclick="login()">登录</button>
</form>
</body>
<script language="javascript">

    function login() {
        $.ajax({
            type: 'post',
            url:"/sfbm/user/find",
//            url: $('#userForm').attr('action'),
            data: $('#userForm').serialize(),
            dataType: 'json',
            success: function(data){
                if (data.result = "success"){
                    alert("登录成功");
                    location.href='/user/success';
                }else{
                    alert("用户名或者密码错误！");
                }
            }
        });
    }


</script>
</html>
