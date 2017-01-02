package cn.org.mingframework.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.org.mingframework.dao.interfaces.BasicDao;

@Repository
public class BasicDaoImpl<T> implements BasicDao {
	private Class entityClass;
	
	@PersistenceContext(unitName="emf")
	private EntityManager em;
	//@Autowired
	//private CrudRepository<T, Long> repository;

	public long count() {
		return 0;
	}

	public <T> List<T> findAll() {
		return null;
	}

	public <T> T findById(Long id) {
		return null;
	}

	public <T> T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

}
