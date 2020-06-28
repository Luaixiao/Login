package com.org.service;

import com.org.pojo.User;

/**新建接口
 * 获取数据库表对象实体类
 * 
 * @author Administrator
 *
 */
public interface LoginService {
	User CheckLoginService(String user_name,String user_password);

}
