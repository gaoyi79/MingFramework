package cn.org.mingframework.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.org.mingframework.model.entity.system.database.EntityDescription;

@Repository
public interface EntityDescriptionRepository extends PagingAndSortingRepository<EntityDescription, Long> {

}
