package cn.org.mingframework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.mingframework.dao.interfaces.UserService;
import cn.org.mingframework.dao.repository.UserRepository;
import cn.org.mingframework.model.entity.system.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
