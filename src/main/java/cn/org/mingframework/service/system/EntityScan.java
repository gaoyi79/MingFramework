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
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.org.mingframework.dao.impl.EntityDaoImpl;
import cn.org.mingframework.dao.interfaces.EntityDao;
import cn.org.mingframework.model.entity.system.database.EntityDescription;

@Service
public class EntityScan {
	private String packageName = "cn/org/mingframework/model/entity";
	
	@PersistenceUnit(unitName = "emf")
	EntityManagerFactory emf;
	
	@PostConstruct
	public void scan() throws IOException, ClassNotFoundException{
		System.out.println("This will be executed when app is up.");
		resetEntities();
		
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

	@Transactional
	public void resetEntities() throws IOException, ClassNotFoundException{
		List<EntityDescription> entities = loadEntitiesFromEntity();
		for(EntityDescription entity : entities){
			System.out.print("Entity Name:" + entity.getEntityName());
			System.out.print("; Class Name:" + entity.getClassName());
			System.out.print("; Table Name:" + entity.getTableName());
		}

		EntityDao<EntityDescription> entityDao = new EntityDaoImpl<EntityDescription>(EntityDescription.class);
		entityDao.save(entities);
	}
	
	private List<EntityDescription> loadEntitiesFromDB(){
		Query query = emf.createEntityManager().createNamedQuery("select e from EntityDescription e");
		return query.getResultList();
	}
	
	private List<EntityDescription> loadEntitiesFromEntity() throws IOException, ClassNotFoundException{
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
			
			Table table = Class.forName(className).getAnnotation(Table.class);
			if(null != table){
				entity.setTableName(table.name());
			}
			else{
				entity.setTableName(entity.getEntityName());	
			}
			
			entities.add(entity);
		}
		
		return entities;
	}
}
