package cn.org.mingframework.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.org.mingframework.model.entity.system.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
