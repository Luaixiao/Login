package com.org.service;

import com.org.bean.User;

/**�½��ӿ�
 * ��ȡ���ݿ�����ʵ����
 * 
 * @author Administrator
 *
 */
public interface LoginService {
	//У���û���¼��Ϣ
	User CheckLoginService(String user_name,String user_password);
	//У���û�Cookie��Ϣ
	User CheckUseridService(String user_id);

}
