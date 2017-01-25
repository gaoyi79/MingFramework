package cn.org.mingframework.dao.interfaces;

import cn.org.mingframework.model.entity.system.User;

public interface UserService {
	public User findByUserName(String userName);
}
