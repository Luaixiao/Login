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
 * 
 * request������
 * 		�����һ�������ڵ�Servlet���ݹ�������
 * 
 * 
 * �ض���
 * 		���ⱳ����1.��ǰ����Servlet�޷�����
 * 			     2.ʹ������ת������ɱ������ظ��ύ
 * 		���������
 * 				 �ض���response.sendRedirect("·��");
 *      ����˱��ظ��ύ������͵�ǰServlet�޷����������.
 *      �ص㣺
 *      	1.������������request����
 *      	2.�������ַ����Ϣ�ı�
 *      ʹ��ʱ����
 *      	1.�������б����ݣ��������ֱȽ���Ҫ�������ظ��ύʱ������ʹ���ض���
 *          2.�������Servlet���ܺ��޷��������д�������ʹ���ض���λ�����Դ������Դ.
 *      
 * Coockieѧϰ��
 * 		���ã�����˷��͵Ĳ�ͬ��������ݹ�������
 * 		ʹ�ã�
 * 			//Coockie�Ĵ����ʹ洢
 * 			Coockie ck = new Cookie(String name,String Value);
 * 			//����Coockie(��ѡ)
 * 				//������Ч��
 * 				ck.setMaxAge(int seconds);
 * 				//������Ч·��
 * 				ck.setPath(String uri);
 * 			//��ӦCookie��Ϣ���ͻ���
 * 			response.addCookie(ck);
 * 			//Coockie�Ļ�ȡ
 * 				//��ȡCoockie��Ϣ����
 * 				Coockie [] cks = request.getCoockies();
 * 				//���������ȡCoockie��Ϣ
 * 				if(cks != null){
 * 			    for(Coockie ck:cks){
 * 					String name = ck.getName();
 * 					String value = ck.getValue();
 * 					System.out.println(name+":"+value);
 * 					}
 * 			     }
 * 		ע�⣺
 * 			һ��Cookice����洢һ������.�������ݣ����Դ������Cookie������д洢.
 *  	�ص㣺
 *  		1.������˵����ݴ洢����
 *  		2.�洢�����������ڷ����(Ҳ����������ݸ�Cookie)
 *  		3.��ʱ�洢���洢��������������ڴ��У�������رպ�ʧЧ
 *  		4.��ʱ�洢��������Cookie����Ч�ڣ��洢�ڿͻ��˵�Ӳ���У�����Ч���ڣ�����·��Ҫ������󶼻ḽ������Ϣ.
 *  
 * 
 * 	
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
    			//����Cookie��Ϣ ʵ���������¼
    			
    			
    			//����ת��
    			//request.getRequestDispatcher("MainServlet").forward(request, response);
    			
    			//�ض���
    			response.sendRedirect("./MainServlet");
    			response.getWriter().write("��½�ɹ�");
    		}
    		else {
    			//ʹ��request����ʵ�ֲ�ͬServlet֮���������ת
    			request.setAttribute("str", "�û������������");
    			//ʹ������ת��
    			request.getRequestDispatcher("PageServlet").forward(request, response);
    			System.out.println("��¼ʧ��");
    			return;
    		}   	
    }
}
