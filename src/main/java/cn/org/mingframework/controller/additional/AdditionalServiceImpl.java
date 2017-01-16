package cn.org.mingframework.controller.additional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class AdditionalServiceImpl implements AdditionalService {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	private ImageRepository imageRepository;
	
	public Page<Article> listArticle(int page) {
		return articleRepository.findAllByDownloaded(1, new PageRequest(page - 1, 20, Sort.Direction.DESC, "createDate"));
	}

	public List<Image> listImage(String articleid) {
		return (List<Image>) imageRepository.findAllByArticleId(articleid);
	}
}