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
 * Coockie��ϢУ��
 * 		�ж��������Ƿ�Я����ȷ��Coockie��Ϣ
 * 		����У���У��Coockie��Ϣ�Ƿ���ȷ
 * 			���У����ȷ��ֱ����Ӧ��ҳ����û�
 * 			���У�鲻��ȷ����Ӧ��¼ҳ����û�
 * 		û��������ת������¼ҳ�� * 
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
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
			//��ȡCoockie��Ϣ
				Cookie [] ck = request.getCookies();	
		//����������Ϣ
				if(ck != null) {
					
				}else {
				//��Ӧ������Ϣ
					//����ת��
					request.getRequestDispatcher("LoginServlet").forward(request, response);
				}
	}
}
