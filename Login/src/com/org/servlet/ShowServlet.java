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
        //������������ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		response.setContentType("text/html; charset=utf-8");
		//��ȡ������Ϣ
		HttpSession hs = request.getSession();
		User u = (User) hs.getAttribute("username");
		//����������Ϣ
		
		
		//��Ӧ������
		response.getWriter().write("����ӭ��¼��");
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<table border = '1px'>");
		response.getWriter().write("<tr>");
		response.getWriter().write("<td>�û���</td>");
		response.getWriter().write("<td>" + u.getUser_name() + "</td>");
		response.getWriter().write("</tr>");
		response.getWriter().write("<tr>");
		response.getWriter().write("<td>����</td>");
		response.getWriter().write("<td>" + u.getUser_password() + "</td>");
		response.getWriter().write("</tr>");
		response.getWriter().write("</table>");
		response.getWriter().write("</body>");
		response.getWriter().write("</head>");
		response.getWriter().write("</html>");
		
		
	}

}
