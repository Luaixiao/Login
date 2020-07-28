 package com.org.dao;

import com.org.bean.User;

public interface LoginDao {
	//根据用户名和密码获取用户信息
	User CheckLoginDao(String user_name,String User_password);
	//根据用户id获取用户信息
	User CheckLoginDao(String user_id);

}
