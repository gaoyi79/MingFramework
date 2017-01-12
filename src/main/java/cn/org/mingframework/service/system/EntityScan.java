package cn.org.mingframework.service.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.org.mingframework.model.entity.system.database.EntityDescription;

@Service
public class EntityScan {
	private String packageName = "cn.org.mingframework.model.entity";
	
	@PersistenceContext(unitName = "emf")
	private EntityManager em;
	
	@PostConstruct
	public void scan() throws IOException{
		System.out.println("This will be loaded when app is start.");
		
		//ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		//Resource[] resources = rpr.getResources("classpath*: "+ packageName + "/**/*.class");
		/*
		for(Resource resource : resources){
			String className = resource.getURL().getPath();
			className = className.split("(classes/)|(!/)")[1];
			className = className.replace("/", ".").replace(".class", "");
			System.out.println(resource.getURL().getPath()+"   "+ className);
		}
		*/
		/*
		ClassPathResource res = new ClassPathResource("cn.org.mingframework.model.entity");
		List files = new ArrayList();
		files.
		System.out.println("Scan all class from package("+this.packageName+")");
		*/
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("This will be loaded when app is shutdown.");
	}
	
	private List<EntityDescription> findAllEntity() throws IOException{
		Query query = em.createQuery("select e from EntityDescription e");
		List<EntityDescription> entities = query.getResultList();
		List<EntityDescription> localEntities = findAllEntityLocal();
		for(EntityDescription entity : entities){
			
		}
		
		return entities;
	}
	
	private List<EntityDescription> findAllEntityLocal() throws IOException{
		List<EntityDescription> entities = new ArrayList<EntityDescription>();
		
		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		Resource[] resources = rpr.getResources("classpath*: "+ packageName + "/**/*.class");

		for(Resource resource : resources){
			String className = resource.getURL().getPath();
			className = className.split("(classes/)|(!/)")[1];
			className = className.replace("/", ".").replace(".class", "");
			EntityDescription entity = new EntityDescription();
			entity.setClassName(className);
			entity.setEntityName(resource.getFilename().replace(".class", ""));
			
			entities.add(entity);
		}
		
		return entities;
	}
}
