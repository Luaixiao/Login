package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * 
 * request作用域：
 * 		解决了一次请求内的Servlet数据共享问题
 * 
 * 
 * 重定向：
 * 		问题背景：1.当前请求Servlet无法处理
 * 			     2.使用请求转发，造成表单数据重复提交
 * 		解决方法：
 * 				 重定向：response.sendRedirect("路径");
 *      解决了表单重复提交的问题和当前Servlet无法处理的问题.
 *      特点：
 *      	1.两次请求，两个request对象
 *      	2.浏览器地址栏信息改变
 *      使用时机：
 *      	1.请求中有表单数据，而数据又比较重要，不能重复提交时，建议使用重定向
 *          2.如果请求被Servlet接受后，无法继续进行处理，建议使用重定向定位到可以处理的资源.
 *      
 * Coockie学习：
 * 		作用：解决了发送的不同请求的数据共享问题
 * 		使用：
 * 			//Coockie的创建和存储
 * 			Coockie ck = new Cookie(String name,String Value);
 * 			//设置Coockie(可选)
 * 				//设置有效期
 * 				ck.setMaxAge(int seconds);
 * 				//设置有效路径
 * 				ck.setPath(String uri);
 * 			//响应Cookie信息给客户端
 * 			response.addCookie(ck);
 * 			//Coockie的获取
 * 				//获取Coockie信息数组
 * 				Coockie [] cks = request.getCoockies();
 * 				//遍历数组获取Coockie信息
 * 				if(cks != null){
 * 			    for(Coockie ck:cks){
 * 					String name = ck.getName();
 * 					String value = ck.getValue();
 * 					System.out.println(name+":"+value);
 * 					}
 * 			     }
 * 		注意：
 * 			一个Cookice对象存储一条数据.多条数据，可以创建多个Cookie对象进行存储.
 *  	特点：
 *  		1.浏览器端的数据存储技术
 *  		2.存储的数据声明在服务段(也就是添加数据给Cookie)
 *  		3.临时存储：存储在浏览器的运行内存中，浏览器关闭后即失效
 *  		4.定时存储：设置了Cookie的有效期，存储在客户端的硬盘中，在有效期内，符合路劲要求的请求都会附带该信息.
 *  
 * Session技术学习：
 * 		问题背景：
 * 			一个用户的不同请求处理的数据共享怎么办？
 * 		解决：
 * 			使用session技术
 * 		原理：
 * 			用户第一次访问服务器，服务器会创建一个session对象给此用户，并将该session对象的JSESSIONID使用Cookie技术储存到浏览器中，保证
 * 			用户的其他请求能够获取到同一个session对象，也保证了不同请求能够获取到共享的数据
 * 		特点：
 * 			存储在服务器端
 * 			服务器进行创建
 * 			依赖Cookie技术
 * 			一个会话
 * 			默认存储时间是30分钟
 * 		作用：
 * 			解决了一个用户不同请求处理的数据共享问题
 * 		使用：
 * 			创建session对象/获取session对象
 * 				HttpSession hs = request.getSession();
 * 				如果请求中拥有session的标识符，也就是JSESSIONID,则返回对应的session对象
 * 				如果请求中没有session的标识符，也就是JSESSIONID,则创建新的session对象，并将其JSESSIONID作为从cookie数据存储到浏览器中
 * 				如果session对象失效 了，也会重新创建一个sesison对象，并将其JSEESIONID存储在浏览器内存中
 * 			设置session存储时间
 * 				hs.setMaxInactiveInterval(5);
 * 				注意：在指定的时间内，session对象没有被使用和销毁，如果使用了则重新计时.
 * 			设置session强制失效：
 * 				hs.invalidate();
 * 			存储和获取数据：
 * 				存储：hs.setAttrbute(String name,Object value);
 * 				获取：hs.getAttrbute(String name)；返回的数据为Object
 * 			注意：
 * 				存储的动作和取出的动作发生在不同的请求中，但是存储要先于去除执行.
 * 		使用时机：
 * 			一般用户在登录web项目时，会将用户的个人信息存储到session中，供该用户的其他请求使用.
 * 		总结：
 * 			session解决了一个用户的不同请求的数据共享问题，只要在JSESSIONID不失效和session对象不失效哦啊的情况下,用户的任意请求在处理时都能
 * 			获取到同一个session对象。
 * 		作用域：
 * 			一次会话
 * 			在JSESSIONID和session对象不失效的情况下作用域为整个项目内.
 * 		session失效处理：
 * 			将用户请求中的JSESSIONID和后台获取到的session对象的id进行比对，如果一致则session没有失效，如果不一致则证明session失效了.
 * 			重定向到登录页面，重新登录.
 * 		注意
 * 			JSEESIONID存储在Cookie的临时存储空间中，浏览器关闭即失效。
 * 
 * 解决主页面用户名显示为null的问题：
 * 		原因：
 * 			因为在用户登录后，使用重定向显示主页面，两次请求，而用户的信息在第一次请求中，第二次请求中没有用户数据，所以显示为null。
 * 		
 * 			
 * 	
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
    			//创建Cookie信息 实现三天免登录
    			Cookie ck = new Cookie("user_id",u.getUser_id()+"");
    			//设置Cookie的有效期
    			ck.setMaxAge(3*24*3600);
    			//设置有效路径
    			ck.setPath("./LoginServlet");
    			//添加Cookie信息
    			response.addCookie(ck);
    			
    			//将数据存储到session对象中
    			//创建session对象
    			HttpSession hs = request.getSession();
    			//存储用户信息到session 
    			hs.setAttribute("username", u);
    			//请求转发
    			//request.getRequestDispatcher("MainServlet").forward(request, response);
    			
    			//重定向到主页面
    			response.sendRedirect("./MainServlet");
    			response.getWriter().write("登陆成功");
    		}
    		else {
    			//使用request对象实现不同Servlet之间的数据流转
    			request.setAttribute("str", "用户名或密码错误");
    			//使用请求转发
    			request.getRequestDispatcher("PageServlet").forward(request, response);
    			System.out.println("登录失败");
    			return;
    		}   	
    }
}
