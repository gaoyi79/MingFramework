package cn.org.mingframework.service.system;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class EntityScan {
	private String packageName = "cn.org.mingframework.model.entity";
	
	@PostConstruct
	public void scan(){
		System.out.println("Scan all class from package("+this.packageName+")");
	}
	
	@PreDestroy
	public void destroy(){
		//System.out.println("-----------------------------------------");
	}
}
