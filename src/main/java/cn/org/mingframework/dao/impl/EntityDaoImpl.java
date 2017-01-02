package cn.org.mingframework.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.org.mingframework.dao.interfaces.EntityDao;

@Transactional
@Repository("entityDao")
public class EntityDaoImpl<T> implements EntityDao<T> {
	//private static final Log LOG = LogFactory.getLog(T.class);
	private Class genericClass;
	private String simpleName;

	@PersistenceContext(unitName="emf")
	private EntityManager entityManager;

	public EntityDaoImpl(Class clazz) {
		genericClass = clazz;
	}
	
	public EntityDaoImpl() {
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return entityManager.
				createQuery("from "+simpleName+" e").getResultList();
	}

	@Transactional(readOnly = true)
	public T findById(Long id) {
		return (T)entityManager.
				createQuery("from "+simpleName+ " e where e.id=:id").
				setParameter("id", id).
				getSingleResult();
	}

	public T save(T entity) {
		return (T) entityManager.merge(entity);
	}

	public void delete(T entity) {
		if(entity != null){
			entityManager.remove(entity);
		}
	}

	public Class getGenericClass() {
		return genericClass;
	}

	public void setGenericClass(Class genericClass) {
		this.genericClass = genericClass;
		this.simpleName = this.genericClass.getSimpleName();
	}

	public void deleteList(List<T> list) {
		for(T item:list){
			delete(item);
		}
	}

	@Transactional(readOnly = true)
	public List<T> findByIdList(List<Long> idList) {
		return entityManager.
				createQuery("from "+simpleName+" e where e.id in (:idlist)").
				setParameter("idlist", idList).
				getResultList();
	}

	public void save(List<T> entityList) {
		for(T entity:entityList){
			this.save(entity);
		}
	}

	private Query queryByCondition(String queryString, Map<String, Object> parameters){
		Query query = entityManager.
				createQuery(queryString);
		
		Iterator entries = parameters.entrySet().iterator();
		while (entries.hasNext()){
		    Map.Entry entry = (Map.Entry) entries.next();
		    query = query.setParameter((String)entry.getKey(), entry.getValue());
		}
		
		return query;
	}
	
	@Transactional(readOnly = true)
	public List<T> findByCondition(String queryString, Map<String, Object> parameters) {
		return queryByCondition(queryString, parameters).getResultList();
	}

	@Transactional(readOnly = true)
	public List<T> findByConditionWithPage(String queryString, Map<String, Object> parameters, int firstResult, int maxResults) {
		return queryByCondition(queryString, parameters).
				setFirstResult(firstResult).
				setMaxResults(maxResults).
				getResultList();
	}

	@Transactional(readOnly = true)
	public int count() {
		return entityManager.
				createQuery("from "+simpleName+" e").
				getResultList().size();
	}
}
