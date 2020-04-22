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

/** ����listEmp�������ɱ��ҳ����� */
public class ListEmpServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 1. ʹ��JDBC��ѯ����Ա������ List<Emp>
		List<Emp> emps = new ArrayList<Emp>();
		EmpService service = new EmpServiceImpl();
		//��ȡ�������  
		//��ʼ��Ŀ���±�index
		//����ѯ������  pagesize
		try {
			emps = service.findAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 2. ʹ��out.println���json����
		// ��emps ת��json�����ַ���
		String json = parseJSON(emps);
		out.println("{\"result\":\"ok\",  \"data\": "+json+" }");
		out.close();
	}

	/**
	 * ��emps�����е����� ת����json�ַ���
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
