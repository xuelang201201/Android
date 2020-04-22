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

import entity.Emp;
/** 处理updateEmp请求，更新员工信息 */
public class UpdateEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		//编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//获取请求参数  name salary age gender id
		String name=request.getParameter("name");
		String salary=request.getParameter("salary");
		String age=request.getParameter("age");
		String gender=request.getParameter("gender");
		String id=request.getParameter("id");
		Emp e=new Emp(Integer.parseInt(id), name, Double.parseDouble(salary), Integer.parseInt(age), gender);
		//执行jdbc  update语句
		EmpService service=new EmpServiceImpl();
		try {
			service.updateEmp(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//重定向到listEmp
		response.sendRedirect("listEmp");
	}
}



