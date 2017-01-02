package cn.org.mingframework.control.additional;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
	
	 //@Query("select e from Image e where e.articleId = :articleid")
	 List<Image> findAllByArticleId(/*@Param("articleid") */String articleId);
}
