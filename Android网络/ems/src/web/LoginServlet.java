package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** ����/login.do���� �жϵ�¼�Ƿ�ɹ� */
public class LoginServlet extends HttpServlet{
	public void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		//1. ��ȡ�������name   pwd
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		System.out.println("name:"+name);
		System.out.println("pwd:"+pwd);
		//2. �жϵ�¼�Ƿ�ɹ�
		//3. �����жϵĽ�� �����ͬ����Ӧҳ��
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		if("zhangsan".equals(name) && 
								"123456".equals(pwd)){
			//�ɹ�
			out.println("success");
		}else{
			//ʧ��
			out.println("error");
		}	
		out.close();
	}
}


