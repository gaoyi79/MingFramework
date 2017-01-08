package cn.org.mingframework.control.additional;

import java.util.List;

import org.springframework.data.domain.Page;

public interface AdditionalService {
	public Page<Article> listArticle(int page);
	public List<Image> listImage(String articleid);
}
