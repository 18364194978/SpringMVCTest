<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1"/>
    <title>SpringMVC系统</title>
    <link href="static/css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="static/js/jquery-1.11.0.min.js"></script>
    <script language="JavaScript" src="static/js/jquery.base64.js"></script>
    <script language="JavaScript" src="static/js/jquery.cookie.js"></script>
    <script src="static/js/cloud.js" type="text/javascript"></script>
    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            });
            var b = myBrowser();
            if (b == 'IE55' || b == 'IE6' || b == 'IE7' || b == 'IE8' || b == 'IE9') {
                alert("您的浏览器版本过低,可能会影响SVG图形展示，请点击底部【浏览器下载】链接下载安装后再进行访问");
            }
        });
        var contextPath = "${contextPath}";
        console.log(contextPath, '2')
        function login() {
            var $userName = document.getElementById("userName").value;
            var $password = document.getElementById("password").value;
            if ($userName == "") {
                $("#tip").html("请输入用户名");
                return;
            }
            if ($password == "") {
                $("#tip").html("请输入密码");
                return;
            }
            $.ajax({
                dataType: "json",
                url: "${contextPath}/spring/user/checkuser",
                type: "post",
                data: {
                    userName: $userName,
                    password: $password
                },
                complete: function (xmlRequest) {
                    var returninfo = eval("(" + xmlRequest.responseText + ")");
                    console.log(returninfo,'666')
                    if (returninfo.result == 1) {
                        setCookie();
                        document.location.href = "${contextPath}/spring/user/home";
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

        function resetPwd() {
            document.location.href = "${contextPath}/app/user/resetPwd";
        }
        function setCookie() {
            var $userName = document.getElementById("userName").value;
            var $password = document.getElementById("password").value;
            var checked = $("[name='checkbox']:checked");
            if (checked && checked.length > 0 && $userName.length > 0) {
                $.cookie($userName, $.base64.encode($password));
            } else {
                $.cookie($userName, null);
            }
        }

        function getCookie() {
            var $userName = document.getElementById("userName").value;
            var checked = $("[name='checkbox']:checked");
            if (checked && checked.length > 0 && $userName.length > 0) {
                var pwd = $.cookie($userName);
                if (pwd != undefined && pwd != '') {
                    document.getElementById("password").value = $.base64.decode(pwd);
                }
            }
        }
        function myBrowser() {
            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
            var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
            var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
            var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
            if (isIE) {
                var IE5 = IE55 = IE6 = IE7 = IE8 = IE9 = IE10 = IE11 = false;
                var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
                reIE.test(userAgent);
                var fIEVersion = parseFloat(RegExp["$1"]);
                IE55 = fIEVersion == 5.5;
                IE6 = fIEVersion == 6.0;
                IE7 = fIEVersion == 7.0;
                IE8 = fIEVersion == 8.0;
                IE9 = fIEVersion == 9.0;
                IE10 = fIEVersion == 10.0;
                IE11 = fIEVersion == 11.0;
                if (IE55) {
                    return "IE55";
                }
                if (IE6) {
                    return "IE6";
                }
                if (IE7) {
                    return "IE7";
                }
                if (IE8) {
                    return "IE8";
                }
                if (IE9) {
                    return "IE9";
                }
                if (IE10) {
                    return "IE10";
                }
                if (IE11) {
                    return "IE11";
                }
            }//isIE end
            if (isFF) {
                return "FF";
            }
            if (isOpera) {
                return "Opera";
            }
        }//myBrowser() end
    </script>
    <style>
        .inputcss-tip {
            position: absolute;
            width: 200px;
            height: 30px;
            background: transparent;
            border: 0px solid #ffffff;
            color: red;
            font-size: 12px;
            left: 300px;
            top: 270px;
        }
    </style>
</head>
<body style="background-color:#1c77ac; background-image:url(static/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>
<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">

        <ul>

            <li><input name="userName" id="userName" type="text" class="loginuser"
                       onkeydown='if(event.keyCode==13){login();}' onblur="javascript:getCookie();"/></li>
            <li><input name="password" id="password" type="password" class="loginpwd"
                       onkeydown='if(event.keyCode==13){login();}'/></li>
            <li><input style="margin-left: 100px" name="" type="button" class="loginbtn" value="登录"
                       onclick="javascript:login();"/><label><input name="checkbox" type="checkbox" value=""
                                                                    checked="checked"/>记住密码</label><%--<label><a href="#">忘记密码？</a></label>--%>
            </li>
            <%--<li>
                <label><a href="#" onclick="javascript:resetPwd();">忘记密码？</a></label>
                <label style="margin-left: 30px"><a href="#" onclick="javascript:registerUser();">注册新账号</a></label>--%>
            <div id="tip" class="inputcss-tip"></div>
        </ul>


    </div>

</div>
<div class="loginbm">版权所有 2017 | <a href="http://www.tech-roof.com">山东容弗新信息科技有限公司</a> | <a
        href="${contextPath}/static/soft/Firefox.zip">浏览器下载</a></div>
</body>
</html>