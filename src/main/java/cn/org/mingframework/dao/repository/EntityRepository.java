package cn.org.mingframework.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EntityRepository<T> extends PagingAndSortingRepository<T, Long> {
	
}
