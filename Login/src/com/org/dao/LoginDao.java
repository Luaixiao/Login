 package com.org.dao;

import com.org.pojo.User;

public interface LoginDao {
	//�����û����������ȡ�û���Ϣ
	User CheckLoginDao(String user_name,String User_password);
	//�����û�id��ȡ�û���Ϣ
	User CheckLoginDao(String user_id);

}
