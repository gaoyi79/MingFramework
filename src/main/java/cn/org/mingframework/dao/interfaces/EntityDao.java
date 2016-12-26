package cn.org.mingframework.dao.interfaces;

import java.util.List;
import java.util.Map;

public interface EntityDao<T> {
	void setGenericClass(Class clazz);
	int count();
	List<T> findAll();
	List<T> findByIdList(List<Long> idList);
	T findById(Long id);
	
	List<T> findByCondition(String queryString,Map<String, Object> parameters);
	List<T> findByConditionWithPage(String queryString,Map<String, Object> parameters, int firstResult, int maxResults);
	
	T save(T entity);
	void save(List<T> entityList);
	
	void delete(T entity);
	void deleteList(List<T> list);
}
