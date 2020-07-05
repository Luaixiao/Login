package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//������������ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡ������Ϣ
		//����������Ϣ
		//��Ӧ������
		response.getWriter().write("����ӭ��¼��");
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<h3>��ӭ"+request.getParameter("user_name")+ "���ʹ���ϵͳ</h3>");
		response.getWriter().write("</body>");
		response.getWriter().write("</head>");
		response.getWriter().write("</html>");
	}

}
