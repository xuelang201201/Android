<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	for (int i = 0; i < 10000; i++) {
%>
		Hello World(<%= i+1 %>)<br/>
<%
	}
%>