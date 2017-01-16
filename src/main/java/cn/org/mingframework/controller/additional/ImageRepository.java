package cn.org.mingframework.controller.additional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long>{
	
	 //@Query("select e from Image e where e.articleId = :articleid")
	 List<Image> findAllByArticleId(/*@Param("articleid") */String articleId);
}
