package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
/** 接收/showEmp请求， 展现回填的表单页面 */
public class ShowEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		//编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//1. 获取id 参数
		String id=request.getParameter("id");
		//2. 通过id  调用jdbc  查询相应员工的数据
		EmpService service=new EmpServiceImpl();
		Emp e=null;
		try {
			e = service.findById(Integer.parseInt(id));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//3. out.println输出表单页面 
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>修改员工updateEmp.html</title>");
		out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"blueprint/screen.css\"/>");
		out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"blueprint/print.css\"/>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<div  class=\"error\" style=\"margin:10px;\">");
		out.println("	<h3>修改员工</h3>");
		out.println("	<form action=\"updateEmp\"  method=\"post\">");
		out.println("		<input value=\""+e.getId()+"\" class=\"text\"  type=\"hidden\" name=\"id\"/>");
		out.println("		姓名：<input value=\""+e.getName()+"\" class=\"text\"  type=\"text\" name=\"name\"/>");
		out.println("		<br/><br/>");
		out.println("		薪水：<input value=\""+e.getSalary()+"\" class=\"text\"  type=\"text\" name=\"salary\"/>");
		out.println("		<br/><br/>");
		out.println("		年龄：<input value=\""+e.getAge()+"\"  class=\"text\"  type=\"text\" name=\"age\"/>");
		out.println("		<br/><br/>");
		out.println("		性别：");
		out.println("		<input  "+(e.getGender().equals("m")?"checked='checked'":"")+" type=\"radio\" value=\"m\"  name=\"gender\"/>男");
		out.println("		<input  "+(e.getGender().equals("f")?"checked='checked'":"")+"  type=\"radio\" value=\"f\" name=\"gender\"/>女");
		out.println("		<br/><br/>");
		out.println("		<input type=\"submit\" value=\"确认修改\"/>");
		out.println("	</form>");
		out.println("	</div>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}









