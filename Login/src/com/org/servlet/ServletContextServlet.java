package com.org.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletContextServlet
 */
@WebServlet("/ServletContextServlet")
public class ServletContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request,HttpServletResponse response ) throws ServletException,IOException{
    	//��ȡServletContext����
    		//��һ�ַ�ʽ(���÷���)
    		ServletContext sc = this.getServletContext();
    		//�ڶ��ַ�ʽ
    		ServletContext sc2 = this.getServletConfig().getServletContext();
    		//�����ַ�ʽ(���÷���)
    		ServletContext sc3 = request.getSession().getServletContext();
       //ʹ��ServletContext����������ݹ���
    		
    }
}
