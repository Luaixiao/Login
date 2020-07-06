package com.org.service;

import com.org.pojo.User;

/**新建接口
 * 获取数据库表对象实体类
 * 
 * @author Administrator
 *
 */
public interface LoginService {
	//校验用户登录信息
	User CheckLoginService(String user_name,String user_password);
	//校验用户Cookie信息
	User CheckUseridService(String user_id);

}
