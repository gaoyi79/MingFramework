package cn.org.mingframework.controller.additional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	Page<Article> findAllByDownloaded(int downloaded, Pageable page);
}
