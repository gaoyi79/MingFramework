<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	request.setAttribute("context", context);
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>右侧页面-用于展示框架右侧的内容</title>
</head>
<body>
	<!-- 用于展示所有右侧区域的内容 -->
	<iframe name="commonFrame" id="commonFrame" src="${context}/one"   frameborder="0" width="100%" scrolling="no" marginheight="0" marginwidth="0" onLoad="setIframeHeight()" ></iframe>

	
	<script type="text/javascript"> 
		function getHeight(doc) {
		    doc = doc || document;
		    var body = doc.body, html = doc.documentElement;
		    var height = Math.max( body.scrollHeight, body.offsetHeight, 
		        html.clientHeight, html.scrollHeight, html.offsetHeight );
		    return height;
		}
		
		function setIframeHeight() {
		    var ifrm = document.getElementById('commonFrame');
		    var doc = ifrm.contentDocument? ifrm.contentDocument: 
		        ifrm.contentWindow.document;
		    ifrm.style.visibility = 'hidden';
		    ifrm.style.height = "10px"; // reset to minimal height ...
		    ifrm.style.height = getHeight( doc ) + 60 + "px";
		    ifrm.style.visibility = 'visible';
		}
		
		 window.onresize = function () { 
			 setIframeHeight();	 
     	};
		
	</script> 	
</body>
</html>