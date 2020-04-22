<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.tarena.myyserver.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="com.tarena.myyserver.util.DaoFactory"%>
<%@page import="com.tarena.myyserver.dao.PageDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	PageDao dao=(PageDao)DaoFactory.getInstance("pageDao");
	List<Page> pages=dao.findAll();
	JSONArray ary=JSONArray.fromObject(pages);
%>    
    
<%=ary.toString()%>