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
 * Session����ѧϰ��
 * 		���ⱳ����
 * 			һ���û��Ĳ�ͬ����������ݹ�����ô�죿
 * 		�����
 * 			ʹ��session����
 * 		ԭ��
 * 			�û���һ�η��ʷ��������������ᴴ��һ��session��������û���������session�����JSESSIONIDʹ��Cookie�������浽������У���֤
 * 			�û������������ܹ���ȡ��ͬһ��session����Ҳ��֤�˲�ͬ�����ܹ���ȡ�����������
 * 		�ص㣺
 * 			�洢�ڷ�������
 * 			���������д���
 * 			����Cookie����
 * 			һ���Ự
 * 			Ĭ�ϴ洢ʱ����30����
 * 		���ã�
 * 			�����һ���û���ͬ����������ݹ�������
 * 		ʹ�ã�
 * 			����session����/��ȡsession����
 * 				HttpSession hs = request.getSession();
 * 				���������ӵ��session�ı�ʶ����Ҳ����JSESSIONID,�򷵻ض�Ӧ��session����
 * 				���������û��session�ı�ʶ����Ҳ����JSESSIONID,�򴴽��µ�session���󣬲�����JSESSIONID��Ϊ��cookie���ݴ洢���������
 * 				���session����ʧЧ �ˣ�Ҳ�����´���һ��sesison���󣬲�����JSEESIONID�洢��������ڴ���
 * 			����session�洢ʱ��
 * 				hs.setMaxInactiveInterval(5);
 * 				ע�⣺��ָ����ʱ���ڣ�session����û�б�ʹ�ú����٣����ʹ���������¼�ʱ.
 * 			����sessionǿ��ʧЧ��
 * 				hs.invalidate();
 * 			�洢�ͻ�ȡ���ݣ�
 * 				�洢��hs.setAttrbute(String name,Object value);
 * 				��ȡ��hs.getAttrbute(String name)�����ص�����ΪObject
 * 			ע�⣺
 * 				�洢�Ķ�����ȡ���Ķ��������ڲ�ͬ�������У����Ǵ洢Ҫ����ȥ��ִ��.
 * 		ʹ��ʱ����
 * 			һ���û��ڵ�¼web��Ŀʱ���Ὣ�û��ĸ�����Ϣ�洢��session�У������û�����������ʹ��.
 * 		�ܽ᣺
 * 			session�����һ���û��Ĳ�ͬ��������ݹ������⣬ֻҪ��JSESSIONID��ʧЧ��session����ʧЧŶ���������,�û������������ڴ���ʱ����
 * 			��ȡ��ͬһ��session����
 * 		������
 * 			һ�λỰ
 * 			��JSESSIONID��session����ʧЧ�������������Ϊ������Ŀ��.
 * 		sessionʧЧ����
 * 			���û������е�JSESSIONID�ͺ�̨��ȡ����session�����id���бȶԣ����һ����sessionû��ʧЧ�������һ����֤��sessionʧЧ��.
 * 			�ض��򵽵�¼ҳ�棬���µ�¼.
 * 		ע��
 * 			JSEESIONID�洢��Cookie����ʱ�洢�ռ��У�������رռ�ʧЧ��
 * 
 * �����ҳ���û�����ʾΪnull�����⣺
 * 		ԭ��
 * 			��Ϊ���û���¼��ʹ���ض�����ʾ��ҳ�棬�������󣬶��û�����Ϣ�ڵ�һ�������У��ڶ���������û���û����ݣ�������ʾΪnull��
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
    			Cookie ck = new Cookie("user_id",u.getUser_id()+"");
    			//����Cookie����Ч��
    			ck.setMaxAge(3*24*3600);
    			//������Ч·��
    			ck.setPath("./LoginServlet");
    			//���Cookie��Ϣ
    			response.addCookie(ck);
    			
    			//�����ݴ洢��session������
    			//����session����
    			HttpSession hs = request.getSession();
    			//�洢�û���Ϣ��session 
    			hs.setAttribute("username", u);
    			//����ת��
    			//request.getRequestDispatcher("MainServlet").forward(request, response);
    			
    			//�ض�����ҳ��
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
