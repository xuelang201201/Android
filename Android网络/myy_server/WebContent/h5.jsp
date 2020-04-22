<%@page import="com.tarena.myyserver.entity.Page"%>
<%@page import="com.tarena.myyserver.util.DaoFactory"%>
<%@page import="com.tarena.myyserver.dao.PageDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<link href="css/jquery.fullPage.css" rel="stylesheet" type="text/css">
<script src="js/jquery.1.8.3.min.js"></script>
<script src="js/jquery.fullPage.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#pages').fullpage();
	});

	function appendpage(text){
		$('#pages').append(text);
		$('#pages').fullpage();
	}
	function updateImage(path){
		$('.section img').attr('src',path);
	}
</script>
<style>
body {
	padding: 0;
	margin: 0;
}
.section{background-size:cover;}
</style>
</head>
<body>
	<div id="pages">
		<%
		int id=Integer.parseInt(request.getParameter("id"));
		PageDao dao=(PageDao)DaoFactory.getInstance("pageDao");
		Page p=dao.findById(id);
		%>
		<%=p.getContent()%>
	</div>
</body>
</html>