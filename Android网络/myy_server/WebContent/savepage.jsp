<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.tarena.myyserver.util.DaoFactory"%>
<%@page import="com.tarena.myyserver.dao.PageDao"%>
<%@page import="com.tarena.myyserver.entity.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String content=request.getParameter("content");
	Page p=new Page();
	p.setContent(content);

	PageDao dao=(PageDao)DaoFactory.getInstance("pageDao");
	int id=dao.save(p);
	JSONObject obj=new JSONObject();
	obj.put("result", "ok");
	obj.put("genId", id);
%>
<%=obj.toString()%>
