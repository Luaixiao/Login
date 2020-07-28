package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.bean.User;
import com.org.service.LoginService;
import com.org.service.impl.LoginServiceImpl;

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
				Cookie [] cks = request.getCookies();	
		//����������Ϣ
				if(cks != null) {
					//����Cookie��Ϣ
					for(Cookie c:cks) {
						String user_id = "";
						if("user_id".equals(c.getName())){
							user_id = c.getValue();
						}
						//У��user_id�Ƿ����
						if("".equals(user_id)) {
							//���user_id��������ת��
							request.getRequestDispatcher("LoginServlet").forward(request, response);
							return;
						}else {
							//���user_id������
								//���ݿ���У��user_id�û���Ϣ
								//��ȡҵ������
								LoginService ls = new LoginServiceImpl();
								User u = ls.CheckUseridService(user_id);
								if(u!=null) {
									//�����ݴ洢��session������
									request.getSession().setAttribute("username", u);
									//�ض�����ҳ��
									response.sendRedirect("./MainServlet");
									return;
								}else {
									//����ת������¼ҳ��
									request.getRequestDispatcher("LoginServlet").forward(request, response);
									return;
								}
						}
					}
				}else {
				//��Ӧ������Ϣ
					//����ת��
					request.getRequestDispatcher("LoginServlet").forward(request, response);
					return;
				}
	}
}
