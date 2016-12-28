<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>


<%
	String context = request.getContextPath();
	request.setAttribute("context", context);
	
%>

<!-- HTML头 开始 -->
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

</head>



<body>
	<div id="wrapper">
		<!-- 左侧导航栏 开始-->
		<div  style="float:left;width: 100px;height:600px;border-style:solid;border-width:2pt;">
			<t:insertAttribute name="menu" />
		</div>
		<!-- 左侧导航栏 结束-->
		<div id="page-wrapper" >
			<!-- 页面头 开始 -->
			<div  style="margin-left:120px;width: 80%;height:100px;border-style:solid;border-width:2pt;">
				<t:insertAttribute name="header" />
			</div>
			<!-- 页面头 结束 -->
			<!--  IFRAME布局 开始 -->
			<div style="margin-left:120px;margin-top:120px;width: 80%;height:100px;border-style:solid;border-width:2pt;">
				<t:insertAttribute name="body" />
			</div>
			<!--  IFRAME布局 结束 -->
			<!-- 页面尾 开始 -->
			<div style="margin-left:120px;margin-top:200px;width: 80%;height:50px;border-style:solid;border-width:2pt;">
				<t:insertAttribute name="footer" />
			</div>
			<!-- 页面尾 结束 -->
		</div>
	</div>

</body>

</html>