package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.bean.User;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//设置相应编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取请求信息
			//获取session对象
			User u = (User) request.getSession().getAttribute("username");
		//处理请求信息
		//相应处理结果
		response.getWriter().write("《欢迎登录》");
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<h3>欢迎"+u.getUser_name()+ "访问管理系统</h3>");
		response.getWriter().write("</body>");
		response.getWriter().write("</head>");
		response.getWriter().write("</html>");
	}

}
