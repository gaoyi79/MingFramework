package cn.org.mingframework.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.org.mingframework.model.entity.system.User;

//@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	public User findByUserName(String userName);
}
