package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.org.bean.User;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应编码格式
		response.setContentType("text/html; charset=utf-8");
		//获取请求信息
		HttpSession hs = request.getSession();
		User u = (User) hs.getAttribute("username");
		//处理请求信息
		
		
		//响应处理结果
		response.getWriter().write("《欢迎登录》");
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<table border = '1px'>");
		response.getWriter().write("<tr>");
		response.getWriter().write("<td>用户名</td>");
		response.getWriter().write("<td>" + u.getUser_name() + "</td>");
		response.getWriter().write("</tr>");
		response.getWriter().write("<tr>");
		response.getWriter().write("<td>密码</td>");
		response.getWriter().write("<td>" + u.getUser_password() + "</td>");
		response.getWriter().write("</tr>");
		response.getWriter().write("</table>");
		response.getWriter().write("</body>");
		response.getWriter().write("</head>");
		response.getWriter().write("</html>");
		
		
	}

}
