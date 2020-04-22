package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import service.EmpServiceImpl;
import util.DBUtil;

import dao.EmpDao;
import dao.EmpDaoImpl;
/** 处理delEmp?id=1 删除请求  删除员工 */
public class DelEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		//编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//1. 获取请求参数   id
		String id=request.getParameter("id");
		//2. 执行jdbc 删除员工数据
		EmpService service=new EmpServiceImpl();
		try {
			service.removeEmp(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3. 重定向到listEmp
		response.sendRedirect("listEmp");
	}
}


