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
 * Java Web ����������������
 * 1.ʹ��String�����������±��� //username = new String(username.getBytes("iso8859-1"),"utf-8"); 
 * 2.ʹ�ù������� 
 * 		Get��ʽ�� 
 * 		����һ��
 * 			//������������ʽ request.setCharacterEncoding("utf-8"); 
 * 		�������
 * 			��tomcatĿ¼�µ�confĿ¼���޸�service.xml�ļ�: ��Connector��ǩ��������� useBodyEncodingForURI = "true" 
 * 		Post��ʽ�� 
 * 			//post��ʽ ������������ʽ 
 * 			request.setCharacterEncoding("utf-8");
 * 
 * Servlet�����ܽ᣺
 * 		������������󵽷�����(����);
 * 		��������������������󣬽��н���������request����洢��������;
 * 		���������ö�Ӧ��servlet��������������request������Ϊʵ�δ��ݸ�Servlet�ķ���;
 * 		Servlet����ִ�н���������;
 * 			������������ʽ
 * 			������Ӧ�����ʽ
 * 			��ȡ������Ϣ
 * 			����������Ϣ
 * 				����ҵ������
 * 				����ҵ������ķ���
 * 			��Ӧ������
 * 
 * ������ת���̣�
 * 		�����-->������-->���ݿ�-->������-->�����
 * 
 * ����ת��ѧϰ��
 * 		���ã�ʵ�ֶ��Servlet���������������󣬱���������࣬��Servletְ�������ȷ.
 * 		ʹ�ã�request.getRequestDispatcher("Ҫת���ĵ�ַ").forward(request, response);
 * 			��ַ�����·����ֱ��дServlet�ı�������.
 * 		�ص㣺һ�������������ַ����Ϣ���ı�.
 * 		ע�⣺����ת����ֱ��return����.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//post��ʽ ������������ʽ
    	request.setCharacterEncoding("utf-8");
    	
    	//������Ӧ�����ʽ
    	response.setContentType("text/html;charset=utf-8");
    	//��ȡ������Ϣ
    	String username = request.getParameter("user_name");
		/*
		 * //��������������� -- ת�����ʽ //ʹ��String�����ݽ������±��� ȱ�㣺���кܶ����ʱ����Ҫ�Ա�������һһ�����±���
		 */
    	//username = new String(username.getBytes("iso8859-1"),"utf-8");  
    	
    	
    	String password = request.getParameter("user_password");
    	System.out.println(username+":"+password);
    	//����������Ϣ
    		//��ȡҵ������
    	    LoginService ls = (LoginService) new LoginServiceImpl();
    		User u = ls.CheckLoginService(username, password);
    		System.out.println(u);
    	//��Ӧ������
    		if(u != null) {
    			response.getWriter().write("��½�ɹ�");
    		}
    		else {
    			//ʹ������ת��
    			request.getRequestDispatcher("PageServlet").forward(request, response);
    			return;
    		}   	
    }
}
