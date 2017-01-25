package cn.org.mingframework.service.system;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import cn.org.mingframework.model.entity.system.database.EntityDescription;
import cn.org.mingframework.model.entity.system.database.FieldDescription;

//@Service
public class EntityScan {
	private String packageName = "cn/org/mingframework/model/entity";

	//@Autowired
	//private EntityService<EntityDescription> entityService;
	
	//@Autowired
	//private BaseService entityDescriptionService;
	
	@PostConstruct
	public void scan() throws IOException, ClassNotFoundException{
		System.out.println("This will be executed when app is up.");
		//resetEntities();
		
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

	}
	
	private List<EntityDescription> loadEntitiesFromDB(){
		return null;//Lists.newArrayList(entityService.findAll());
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
			getFields(entity);
			
			entities.add(entity);
		}
		
		return entities;
	}
	
	private void getFields(EntityDescription entity) throws SecurityException, ClassNotFoundException{
		List<FieldDescription> fieldDescriptions = new ArrayList<FieldDescription>();
		Field[] fields = Class.forName(entity.getClassName()).getFields();
		for(Field field : fields){
			FieldDescription fieldDescription = new FieldDescription();
			fieldDescription.setEntity(entity);
			fieldDescription.setFieldName(field.getName());
			
			fieldDescriptions.add(fieldDescription);
		}
		
		entity.setFieldDescription(fieldDescriptions);
		
	}
}
