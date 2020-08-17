package com.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//设置相应编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取请求信息
		String name = "张三";
		//处理请求
			//创建Session对象
			HttpSession hs = request.getSession();
			//存储数据
			hs.setAttribute("username", name);
		//设置session的存储时间
			//hs.setMaxInactiveInterval(5);
		//设置session强制失效
			//hs.invalidate();
		System.out.println(hs.getId());
		//响应处理结果
		response.getWriter().write("响应session结果");
		
	}

}
