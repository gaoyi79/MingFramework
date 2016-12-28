<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String context = request.getContextPath();
	request.setAttribute("context", context);
%>
<style>
.spanClass {
	color: white;
}
</style>

<div style="width: 200px;">

	<li><a href="${context}/one" target='commonFrame'> 
		 <span> 第一页</span>
	</a></li>

	<li><a href="${context}/two" target='commonFrame'> 
		 <span> 第二页</span>
	</a></li>

</div>

