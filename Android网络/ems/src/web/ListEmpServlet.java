package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

/** 处理listEmp请求，生成表格页面输出 */
public class ListEmpServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 1. 使用JDBC查询所有员工数据 List<Emp>
		List<Emp> emps = new ArrayList<Emp>();
		EmpService service = new EmpServiceImpl();
		//获取请求参数  
		//起始条目的下标index
		//向后查询多少条  pagesize
		try {
			emps = service.findAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 2. 使用out.println输出json数据
		// 把emps 转成json数组字符串
		String json = parseJSON(emps);
		out.println("{\"result\":\"ok\",  \"data\": "+json+" }");
		out.close();
	}

	/**
	 * 把emps集合中的数据 转换成json字符串
	 * @param emps
	 * @return 
	 * [{id:xx, name:, salary:, age:, gender:},{},{}]
	 */
	private String parseJSON(List<Emp> emps) {
		StringBuffer sb=new StringBuffer();
		sb.append("[ ");
		for(int i=0; i<emps.size(); i++){
			Emp e=emps.get(i);
			sb.append("{\"id\":"+e.getId()+", \"name\":\""+e.getName()+"\", \"salary\":"+e.getSalary()+", \"age\":"+e.getAge()+", \"gender\":\""+e.getGender()+"\"},");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
