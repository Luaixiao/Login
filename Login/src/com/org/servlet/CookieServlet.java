package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 * Coockie信息校验
 * 		判断请求中是否携带正确的Coockie信息
 * 		如果有，则校验Coockie信息是否正确
 * 			如果校验正确则直接响应主页面给用户
 * 			如果校验不正确则响应登录页面给用户
 * 		没有则请求转发给登录页面 * 
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取请求信息
			//获取Coockie信息
				Cookie [] ck = request.getCookies();	
		//处理请求信息
				if(ck != null) {
					
				}else {
				//响应请求信息
					//请求转发
					request.getRequestDispatcher("LoginServlet").forward(request, response);
				}
	}
}
