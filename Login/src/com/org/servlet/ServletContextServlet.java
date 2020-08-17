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
    	//获取ServletContext对象
    		//第一种方式(常用方法)
    		ServletContext sc = this.getServletContext();
    		//第二种方式
    		ServletContext sc2 = this.getServletConfig().getServletContext();
    		//第三种方式(常用方法)
    		ServletContext sc3 = request.getSession().getServletContext();
       //使用ServletContext对象完成数据共享
    		
    }
}
