package cn.org.mingframework.control.additional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("additionalService")
@Repository
@EnableJpaRepositories("cn.org.mingframework.control.additional")
@ComponentScan(basePackages = { "cn.org.mingframework.control.additional" })
//@EntityScan("my.package.base.*") 
public class AdditionalServiceImpl implements AdditionalService {
	
	//@Autowired
	//private ArticleRepository articleDao;
	
	//@Autowired
	//private ImageRepository imageDao;

	@PersistenceContext(unitName="unitMsSQL")
	public List<Article> listArticle(int page){
		//Pageable p = new PageRequest(page, 20, new Sort(Sort.Direction.ASC, "id"));
		//return (List<Article>) articleDao.findAll(p);
		return null;
	}

	public List<Image> listImage(String articleid){
		//return imageDao.findAllByArticleId(articleid);
		return null;
	}
}
