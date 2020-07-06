package com.org.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.org.dao.LoginDao;
import com.org.pojo.User;

public class LoginDaoImpl implements LoginDao{

	//�����û����������ȡ�û���Ϣ
	@Override
	public User CheckLoginDao(String user_name, String User_password) {
		// TODO Auto-generated method stub
		//����jar��
		//����JDBC����
			//���Ӷ���
			Connection conn = null;
			PreparedStatement ps = null;
			//���������
			ResultSet rs = null;
		//�������ݴ洢����
			User u = null;
			
			try {
				//��������
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("��ʼ�����������ݿ�");
				//��ȡ����
				conn = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl", "fcbp_fdc", "fcbp_fdc");
				System.out.println("���ݿ����ӳɹ�");
				//����sql����
				String sql = "select * from parm_sys_user where user_name = ? and user_password = ? ";
				//����sql�������
				ps = conn.prepareStatement(sql);
				System.out.println("�ɹ�ִ��sql���");
				//��ռλ����ֵ
				ps.setString(1, user_name);
				ps.setString(2, User_password);
				System.out.println("ռλ����ֵ�ɹ�");
				//ִ��
				rs= ps.executeQuery();
				System.out.println("sql���ִ�гɹ�");
				//����ִ�н��
				while(rs.next()) {
					//���ֶε���������һ��Ҫ�����ݿ��ֶ���ͬ������ᱨ���޷�ת��Ϊ�ڲ���ʾ����
				  u= new User(); u.setUser_id(rs.getString("user_id"));
				  u.setUser_name(rs.getString("user_name"));
				  u.setUser_password(rs.getString("user_password"));
				System.out.println("�û�ID��"+rs.getString("user_id")+"�û����ƣ�"+rs.getString("user_name")+"�û����룺"+rs.getString("user_password"));
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//�ر���Դ
					try {
						if(rs != null)
						    rs.close();
						if(ps != null)
							ps.close();
						if(conn != null)
							conn.close();		
					System.out.println("���ݿ����ӹرճɹ�");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		
			//����
		return u;
	}
	
	//�����û�id��ȡ�û���Ϣ
	@Override
	public User CheckLoginDao(String user_id) {
		//����jar��
				//����JDBC����
					//���Ӷ���
					Connection conn = null;
					PreparedStatement ps = null;
					//���������
					ResultSet rs = null;
				//�������ݴ洢����
					User u = null;
					
					try {
						//��������
						Class.forName("oracle.jdbc.driver.OracleDriver");
						System.out.println("��ʼ�����������ݿ�");
						//��ȡ����
						conn = DriverManager.getConnection("jdbc:oracle:thin:localhost:1521:orcl", "fcbp_fdc", "fcbp_fdc");
						System.out.println("���ݿ����ӳɹ�");
						//����sql����
						String sql = "select * from parm_sys_user where user_id = ? ";
						//����sql�������
						ps = conn.prepareStatement(sql);
						System.out.println("�ɹ�ִ��sql���");
						//��ռλ����ֵ
						ps.setString(1, user_id);
						System.out.println("ռλ����ֵ�ɹ�");
						//ִ��
						rs= ps.executeQuery();
						System.out.println("sql���ִ�гɹ�");
						//����ִ�н��
						while(rs.next()) {
							//���ֶε���������һ��Ҫ�����ݿ��ֶ���ͬ������ᱨ���޷�ת��Ϊ�ڲ���ʾ����
						  u= new User(); u.setUser_id(rs.getString("user_id"));
						  u.setUser_name(rs.getString("user_name"));
						  u.setUser_password(rs.getString("user_password"));
						System.out.println("�û�ID��"+rs.getString("user_id")+"�û����ƣ�"+rs.getString("user_name")+"�û����룺"+rs.getString("user_password"));
						}
						
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						//�ر���Դ
							try {
								if(rs != null)
								    rs.close();
								if(ps != null)
									ps.close();
								if(conn != null)
									conn.close();		
							System.out.println("���ݿ����ӹرճɹ�");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					}
				
					//����
				return u;
	}
	

}
