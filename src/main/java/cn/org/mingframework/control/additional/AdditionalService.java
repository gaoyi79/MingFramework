package cn.org.mingframework.control.additional;

import java.util.List;

public interface AdditionalService {
	public List<Article> listArticle(int page);
	public List<Image> listImage(String articleid);
}
