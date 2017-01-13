package cn.org.mingframework.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EntityServiceImpl<T> implements EntityRepository<T> {

	@Autowired
	EntityRepository<T> entityRepository;
	
	public Iterable<T> findAll(Sort arg0) {
		return entityRepository.findAll(arg0);
	}

	public Page<T> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(T arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Iterable<? extends T> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<T> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public T findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends T> S save(S arg0) {
		return entityRepository.save(arg0);
	}

	public <S extends T> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
