package cn.org.mingframework.dao.interfaces;

import java.util.List;

public interface BasicDao {
	long count();
	<T> List<T> findAll();
	<T> T findById(Long id);
	<T> T save(T entity);
	<T> void delete(T entity);
	
	void setEntityClass(Class entityClass);
}
