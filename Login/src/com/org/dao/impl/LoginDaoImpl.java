package com.org.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.org.dao.LoginDao;
import com.org.pojo.User;

public class LoginDaoImpl implements LoginDao{

	//根据用户名和密码获取用户信息
	@Override
	public User CheckLoginDao(String user_name, String User_password) {
		// TODO Auto-generated method stub
		//导入jar包
		//创建JDBC对象
			//连接对象
			Connection conn = null;
			PreparedStatement ps = null;
			//结果集对象
			ResultSet rs = null;
		//声明数据存储对象
			User u = null;
			
			try {
				//加载驱动
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("开始尝试连接数据库");
				//获取连接
				conn = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl", "fcbp_fdc", "fcbp_fdc");
				System.out.println("数据库连接成功");
				//创建sql命令
				String sql = "select * from parm_sys_user where user_name = ? and user_password = ? ";
				//创建sql命令对象
				ps = conn.prepareStatement(sql);
				System.out.println("成功执行sql语句");
				//给占位符赋值
				ps.setString(1, user_name);
				ps.setString(2, User_password);
				System.out.println("占位符赋值成功");
				//执行
				rs= ps.executeQuery();
				System.out.println("sql语句执行成功");
				//遍历执行结果
				while(rs.next()) {
					//各字段的数据类型一定要和数据库字段相同，否则会报错：无法转换为内部表示错误
				  u= new User(); u.setUser_id(rs.getString("user_id"));
				  u.setUser_name(rs.getString("user_name"));
				  u.setUser_password(rs.getString("user_password"));
				System.out.println("用户ID："+rs.getString("user_id")+"用户名称："+rs.getString("user_name")+"用户密码："+rs.getString("user_password"));
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//关闭资源
					try {
						if(rs != null)
						    rs.close();
						if(ps != null)
							ps.close();
						if(conn != null)
							conn.close();		
					System.out.println("数据库连接关闭成功");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		
			//返回
		return u;
	}
	
	//根据用户id获取用户信息
	@Override
	public User CheckLoginDao(String user_id) {
		//导入jar包
				//创建JDBC对象
					//连接对象
					Connection conn = null;
					PreparedStatement ps = null;
					//结果集对象
					ResultSet rs = null;
				//声明数据存储对象
					User u = null;
					
					try {
						//加载驱动
						Class.forName("oracle.jdbc.driver.OracleDriver");
						System.out.println("开始尝试连接数据库");
						//获取连接
						conn = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl", "fcbp_fdc", "fcbp_fdc");
						System.out.println("数据库连接成功");
						//创建sql命令
						String sql = "select * from parm_sys_user where user_id = ? ";
						//创建sql命令对象
						ps = conn.prepareStatement(sql);
						System.out.println("成功执行sql语句");
						//给占位符赋值
						ps.setString(1, user_id);
						System.out.println("占位符赋值成功");
						//执行
						rs= ps.executeQuery();
						System.out.println("sql语句执行成功");
						//遍历执行结果
						while(rs.next()) {
							//各字段的数据类型一定要和数据库字段相同，否则会报错：无法转换为内部表示错误
						  u= new User(); u.setUser_id(rs.getString("user_id"));
						  u.setUser_name(rs.getString("user_name"));
						  u.setUser_password(rs.getString("user_password"));
						System.out.println("用户ID："+rs.getString("user_id")+"用户名称："+rs.getString("user_name")+"用户密码："+rs.getString("user_password"));
						}
						
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						//关闭资源
							try {
								if(rs != null)
								    rs.close();
								if(ps != null)
									ps.close();
								if(conn != null)
									conn.close();		
							System.out.println("数据库连接关闭成功");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					}
				
					//返回
				return u;
	}
	

}
