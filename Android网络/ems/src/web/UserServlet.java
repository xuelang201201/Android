package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import service.UserService;
import service.UserServiceImpl;

import entity.User;
import exception.NameAlreadyUseException;
import exception.NameOrPwdErrorException;
/** 用于处理用户相关的请求 */
public class UserServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException {
		//编码同一处理 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uri=request.getRequestURI();
		uri=uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		//手动分发请求 
		if(uri.equals("regist")){
			regist(request, response);
		}else if(uri.equals("login")){
			login(request, response);
		}else if(uri.equals("getCode")){
			getCode(request, response);
		}
	}
	
	/** 生成验证码图片 */
	public void getCode(HttpServletRequest request, 
			HttpServletResponse resp)
		throws IOException, ServletException{
		//定义响应的内容类型
		resp.setContentType("image/jpeg");
		//绘制验证码图片
		BufferedImage image=
				new BufferedImage(130, 50, 
						BufferedImage.TYPE_INT_RGB);
		//获取用于绘制该图片的画笔对象
		Graphics g=image.getGraphics();
		//绘制背景
		g.setColor(randomColor());
		g.fillRect(0, 0, 130, 50);   
		//绘制文字
		String strs="ABCDEFGHJKMNPQRSTWXY83";
		String str="";
		Random r=new Random();
		for(int i=0; i<5; i++){
			str+=strs.charAt(r.nextInt(strs.length()));
		}
		//把字符串存入session
		HttpSession session=request.getSession();
		session.setAttribute("code", str);
		g.setColor(randomColor());
		g.setFont(new Font("黑体", Font.ITALIC, 45));
		g.drawString(str, 15, 30);
		//把该图片压缩成JPEG格式 
		//并且输出到客户端
		OutputStream os=resp.getOutputStream();
		JPEGImageEncoder encoder = 
				JPEGCodec.createJPEGEncoder(os);
		encoder.encode(image);
	}
	
	/** 处理登录请求 */
	public void login(HttpServletRequest request, 
			HttpServletResponse resp)
		throws IOException, ServletException{
		PrintWriter out=resp.getWriter();
		//1  获取两个请求参数
		String name=request.getParameter("loginname");
		String pwd=request.getParameter("password");
		String code=request.getParameter("code");
		//判断验证码是否正确 
		HttpSession session=request.getSession();
		String scode=(String)session.getAttribute("code");
		if(!code.equalsIgnoreCase(scode)){
			out.println("{\"result\":\"error\", \"msg\":\"验证码输入错误,请重新输入\"}");
			out.close();
			return;
		}
		//2  调用业务层执行登录业务
		UserService service=new UserServiceImpl();
		try {
			service.login(name, pwd);
			//3  根据不同的返回值 返回相应json
			out.println("{\"result\":\"ok\"}");
		} catch (NameOrPwdErrorException e) {
			out.println("{\"result\":\"error\", \"msg\":\""+e.getMessage()+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("{\"result\":\"error\", \"msg\":\"系统繁忙   稍后再试\"}");
		}
		out.close();
	}
	
	/** 处理注册请求 */
	public void regist(HttpServletRequest request, 
			HttpServletResponse resp)
		throws IOException, ServletException{
		PrintWriter out=resp.getWriter();
		//1.获取参数  
		String loginname=request.getParameter("loginname");
		String password=request.getParameter("password");
		String realname=request.getParameter("realname");
		String email=request.getParameter("email");
		User user=new User(0, loginname, password, realname, email);
		//2. 调用业务层 执行注册业务
		UserService service=new UserServiceImpl();
		try {
			service.regist(user);
			//3. 根据不同的返回值 返回不同的json
			out.println("{\"result\":\"ok\"}");
		} catch (NameAlreadyUseException e) {
			out.println("{\"result\":\"error\", \"msg\":\""+e.getMessage()+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("{\"result\":\"error\", \"msg\":\"系统繁忙   稍后再试\"}");
		}
		out.close();
	}
	
	public Color randomColor(){
		Random r=new Random();
		return new Color(r.nextInt(255), 
				r.nextInt(255), r.nextInt(255));
	}
	
}


