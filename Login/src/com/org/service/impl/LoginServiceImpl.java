package com.org.service.impl;

import com.org.dao.LoginDao;
import com.org.dao.impl.LoginDaoImpl;
import com.org.pojo.User;
import com.org.service.LoginService;

public class LoginServiceImpl implements LoginService{

	//����Dao�����
	LoginDao ld = new LoginDaoImpl();
	//�����û���������У���û���¼��Ϣ
	@Override
	public User CheckLoginService(String user_name, String user_password) {
		// TODO Auto-generated method stub
		return ld.CheckLoginDao(user_name, user_password);
	}
	
	//�����û�idУ���û�Cookie��Ϣ
	@Override
	public User CheckUseridService(String user_id) {
		// TODO Auto-generated method stub
		return ld.CheckLoginDao(user_id);
	}
	

}
