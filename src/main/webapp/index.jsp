<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<title>SpringMVC系统</title>
	<link href="static/css/loginstyle.css" rel="stylesheet" type="text/css"/>
	<script language="JavaScript" src="static/js/jquery-1.11.0.min.js"></script>
	<script language="JavaScript" src="static/js/jquery.base64.js"></script>
	<script language="JavaScript" src="static/js/jquery.cookie.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Lambent Login Form Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
		<script type="application/x-javascript">
            var contextPath = "${contextPath}";
            console.log(contextPath, '2')
			addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); }
            function login() {
                console.log('111111')
                var userName = document.getElementById("userName").value;
                var password = document.getElementById("password").value;
                if (userName == "") {
                    return;
                }
                if (password == "") {
                    return;
                }
                $.ajax({
                    dataType: "json",
                    url: "${contextPath}/del",
                    type: "post",
                    data: {
                        userName: userName,
                        password: password
                    },
                    complete: function (xmlRequest) {
                        var returninfo = eval("(" + xmlRequest.responseText + ")");
                        if (returninfo.result == 1) {
                            setCookie();
                            document.location.href = "${contextPath}/dnw/user/home";
                        } else if (returninfo.result == -1) {
                            alert("用户名有误或已被禁用");
                        } else if (returninfo.result == -2) {
                            alert("密码错误");
                        } else {
                            alert("服务器错误");
                        }
                    }
                });
            }
            function setCookie() {
                var userName = document.getElementById("userName").value;
                var password = document.getElementById("password").value;
                var checked = $("[name='checkbox']:checked");
                if (checked && checked.length > 0 && userName.length > 0) {
                    $.cookie(userName, $.base64.encode(password));
                } else {
                    $.cookie(userName, null);
                }
            }
            function getCookie() {
                var userName = document.getElementById("userName").value;
                var checked = $("[name='checkbox']:checked");
                if (checked && checked.length > 0 && userName.length > 0) {
                    var pwd = $.cookie(userName);
                    if (pwd != undefined && pwd != '') {
                        document.getElementById("password").value = $.base64.decode(pwd);
                    }
                }
            }
		</script>
	<style>

	</style>
</head>
<body>
	<h1>SpringMVC测试系统</h1>
	<div class="main-agileinfo">
		<h2>用户登录</h2>
		<form action="#" method="post">
			<input type="text" name="name" class="name" id="userName" placeholder="Usename" required="" onkeydown='if(event.keyCode==13){login();}' onblur="javascript:getCookie();">
			<input type="password" name="password" id="password" class="password" placeholder="Password" required="" onkeydown='if(event.keyCode==13){login();}'>
			<ul>
				<li>
					<input type="checkbox" id="brand1" name="checkbox" value="">
					<label for="brand1"><span></span>记得我</label>
				</li>
			</ul>
            <a href="#">忘记密码?
</a><br>
			<div class="clear"></div>
			<input type="submit" value="Login" onclick="javascript:login();">
		</form>
	</div>
	<div class="footer-w3l">
		<p class="agile"> &copy; 2017 xxxxxxxxxxxxx</p>
	</div>
</body>
</html>