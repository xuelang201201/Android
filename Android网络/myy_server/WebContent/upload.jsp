<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.tarena.myyserver.util.StringUtils"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String targetFilePath="";
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload sfu = new ServletFileUpload(factory);
	List<FileItem> items=sfu.parseRequest(request);
	for(int i=0; i<items.size(); i++){
		FileItem item=items.get(i);
		if(!item.isFormField()){
			targetFilePath="images/"+StringUtils.genPicPath()+".jpg";
			item.write(new File(application.getRealPath(targetFilePath)));		
		}
	}
	
	JSONObject res=new JSONObject();
	res.put("result", "ok");
	res.put("path", targetFilePath);
%>
<%=res.toString()%>