package cn.org.mingframework.service.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
	private String packageName = "cn/org/mingframework/model/entity";
	
	@PersistenceUnit(unitName = "emf")
	EntityManagerFactory emf;
	
	@PostConstruct
	public void scan() throws IOException{
		System.out.println("This will be executed when app is up.");
		
		/*
		ClassPathResource res = new ClassPathResource("cn.org.mingframework.model.entity");
		List files = new ArrayList();
		files.
		System.out.println("Scan all class from package("+this.packageName+")");
		*/
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("This will be execute when app is down.");	
	}
	
	public void resetEntities(){
		
	}
	
	private List<EntityDescription> loadEntitiesFromDB(){
		Query query = emf.createEntityManager().createNamedQuery("select e from EntityDescription e");
		return query.getResultList();
	}
	
	private List<EntityDescription> loadEntitiesFromEntity() throws IOException{
		List<EntityDescription> entities = new ArrayList<EntityDescription>();
		
		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		Resource[] resources = rpr.getResources("classpath*:" + packageName + "/**/*.class");
		
		for(Resource resource : resources){
			EntityDescription entity = new EntityDescription();
			String className = resource.getURL().getPath();
			className = className.split("(classes/)|(!/)")[1];
			className = className.replace("/", ".").replace(".class", "");
			
			entity.setClassName(className);
			entity.setEntityName(resource.getFilename().replace(".class", ""));
			
			entities.add(entity);
		}
		
		return entities;
	}
}
