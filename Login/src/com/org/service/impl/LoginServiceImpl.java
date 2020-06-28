package com.org.service.impl;

import com.org.dao.LoginDao;
import com.org.dao.impl.LoginDaoImpl;
import com.org.pojo.User;
import com.org.service.LoginService;

public class LoginServiceImpl implements LoginService{

	//创建Dao层对象
	LoginDao ld = new LoginDaoImpl();
	@Override
	public User CheckLoginService(String user_name, String user_password) {
		// TODO Auto-generated method stub
		return ld.CheckLoginDao(user_name, user_password);
	}
	

}
