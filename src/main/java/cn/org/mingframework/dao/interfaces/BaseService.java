package cn.org.mingframework.dao.interfaces;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class BaseService<T extends Serializable, ID extends Serializable> {
	@Autowired
	private PagingAndSortingRepository<T, ID> repository;

	public T save(T entity){
		return repository.save(entity);
	}
}
