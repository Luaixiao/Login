package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.pojo.User;
import com.org.service.LoginService;
import com.org.service.impl.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet
 * 
 * Java Web 请求乱码问题解决：
 * 1.使用String进行数据重新编码 //username = new String(username.getBytes("iso8859-1"),"utf-8"); 
 * 2.使用公共配置 
 * 		Get方式： 
 * 		步骤一：
 * 			//设置请求编码格式 request.setCharacterEncoding("utf-8"); 
 * 		步骤二：
 * 			在tomcat目录下的conf目录中修改service.xml文件: 在Connector标签中添加属性 useBodyEncodingForURI = "true" 
 * 		Post方式： 
 * 			//post方式 设置请求编码格式 
 * 			request.setCharacterEncoding("utf-8");
 * 
 * Servlet流程总结：
 * 		浏览器发起请求到服务器(请求);
 * 		服务器接受浏览器的请求，进行解析，创建request对象存储请求数据;
 * 		服务器调用对应的servlet进行请求处理，并将request对象作为实参传递给Servlet的方法;
 * 		Servlet方法执行进行请求处理;
 * 			设置请求编码格式
 * 			设置响应编码格式
 * 			获取请求信息
 * 			处理请求信息
 * 				创建业务层对象
 * 				调用业务层对象的方法
 * 			响应处理结果
 * 
 * 数据流转流程：
 * 		浏览器-->服务器-->数据库-->服务器-->浏览器
 * 
 * 请求转发学习：
 * 		作用：实现多个Servlet联动操作处理请求，避免代码冗余，让Servlet职责更加明确.
 * 		使用：request.getRequestDispatcher("要转发的地址").forward(request, response);
 * 			地址：相对路径，直接写Servlet的别名即可.
 * 		特点：一次请求，浏览器地址栏信息不改变.
 * 		注意：请求转发后，直接return即可.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//post方式 设置请求编码格式
    	request.setCharacterEncoding("utf-8");
    	
    	//设置响应编码格式
    	response.setContentType("text/html;charset=utf-8");
    	//获取请求信息
    	String username = request.getParameter("user_name");
		/*
		 * //解决中文乱码问题 -- 转编码格式 //使用String对数据进行重新编码 缺点：当有很多变量时，需要对变量进行一一的重新编码
		 */
    	//username = new String(username.getBytes("iso8859-1"),"utf-8");  
    	
    	
    	String password = request.getParameter("user_password");
    	System.out.println(username+":"+password);
    	//处理请求信息
    		//获取业务层对象
    	    LoginService ls = (LoginService) new LoginServiceImpl();
    		User u = ls.CheckLoginService(username, password);
    		System.out.println(u);
    	//响应处理结果
    		if(u != null) {
    			response.getWriter().write("登陆成功");
    		}
    		else {
    			//使用请求转发
    			request.getRequestDispatcher("PageServlet").forward(request, response);
    			return;
    		}   	
    }
}
