package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 接收/login.do请求， 判断登录是否成功 */
public class LoginServlet extends HttpServlet{
	public void service(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		//1. 获取请求参数name   pwd
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		System.out.println("name:"+name);
		System.out.println("pwd:"+pwd);
		//2. 判断登录是否成功
		//3. 根据判断的结果 输出不同的响应页面
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		if("zhangsan".equals(name) && 
								"123456".equals(pwd)){
			//成功
			out.println("success");
		}else{
			//失败
			out.println("error");
		}	
		out.close();
	}
}


