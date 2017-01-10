package cn.org.mingframework.service.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class EntityScan {
	private String packageName = "cn.org.mingframework.model.entity";
	
	@PostConstruct
	public void scan() throws IOException{
		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		Resource[] resources = rpr.getResources("classpath*:cn/org/mingframework/model/entity/**/*.class");
		
		for(Resource resource : resources){
			String className = resource.getURL().getPath();
			className = className.split("(classes/)|(!/)")[1];
			className = className.replace("/", ".").replace(".class", "");
			System.out.println(resource.getURL().getPath()+"   "+ className);
		}
		
		/*
		ClassPathResource res = new ClassPathResource("cn.org.mingframework.model.entity");
		List files = new ArrayList();
		files.
		System.out.println("Scan all class from package("+this.packageName+")");
		*/
	}
	
	@PreDestroy
	public void destroy(){
		//System.out.println("-----------------------------------------");
	}
}
