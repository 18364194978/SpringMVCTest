<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>MVC主页</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/ext/examples/portal/portal.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/ext/examples/shared/example.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/forestry-style.css"  />
	    <script type="text/javascript" src="${contextPath}/static/ext/examples/shared/include-ext.js"></script>
	    <script type="text/javascript" src="${contextPath}/static/ext/locale/ext-lang-zh_CN.js"></script>
	    <script type="text/javascript" src="${contextPath}/static/ext/examples/shared/options-toolbar.js"></script>
	    <script type="text/javascript" src="${contextPath}/static/ext/examples/shared/examples.js"></script>
	    <script type="text/javascript">
			console.log('888888888')
            var role_ids = '${SESSION_ROLE_IDS}';
            console.log(role_ids,'1111')
			<%--var lastLoginTime = '${SESSION_SYS_USER.lastLoginTime}';--%>
	    	var appBaseUri = '${contextPath}';
	    	var appName = '配网整定计算';
	        Ext.Loader.setPath('Ext.app', '${contextPath}/static/ext/examples/portal/classes');
	        Ext.Loader.setPath('Ext.ux', '${contextPath}/static/ext/examples/ux');
	        Ext.Loader.setPath('Forestry.app', '${contextPath}/static/ext/examples/portal');//此处注意，加载的是..examples/portal路径下所有开头定义为
            //'Forestry.app'的组件（如：Ext.define('Forestry.app.manage.AccountManage',{}），若不为'Forestry.app'，则未加载，在使用时会报错
	    </script>
	    <script type="text/javascript" src="${contextPath}/static/ext/examples/portal/portal.js"></script>
		<%--<script type="text/javascript" src="${contextPath}/static/ext/examples/portal/UUID.js"></script>--%>
		<%--<script type="text/javascript" src="${contextPath}/static/ext/examples/portal/commonField.js"></script>--%>
	    <%--<script type="text/javascript">--%>
	        <%--Ext.require([--%>
	            <%--'Ext.layout.container.*',--%>
	            <%--'Ext.resizer.Splitter',--%>
	            <%--'Ext.fx.target.Element',--%>
	            <%--'Ext.fx.target.Component',--%>
	            <%--'Ext.window.Window',--%>
	            <%--'Ext.app.Portlet',--%>
	            <%--'Ext.app.PortalColumn',--%>
	            <%--'Ext.app.PortalPanel',--%>
	            <%--'Ext.app.Portlet',--%>
	            <%--'Ext.app.PortalDropZone'--%>
	        <%--]);--%>
	    <%--</script>--%>
	</head>
	<body>
		<span id="app-msg" style="display:none;"></span>
	    <form id="history-form" class="x-hide-display">
	        <input type="hidden" id="x-history-field" />
	        <iframe id="x-history-frame"></iframe>
			<iframe id="download" name="download" height="0px" width="0px"></iframe>
	    </form>
	</body>
</html>
