<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.tarena.myyserver.entity.PageModule"%>
<%@page import="java.util.List"%>
<%@page import="com.tarena.myyserver.util.DaoFactory"%>
<%@page import="com.tarena.myyserver.dao.PageModuleDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	PageModuleDao dao=(PageModuleDao)DaoFactory.getInstance("pageModuleDao");
	List<PageModule> modules=dao.findAll();
	JSONArray ary=JSONArray.fromObject(modules);
%>    
    
<%=ary.toString()%>