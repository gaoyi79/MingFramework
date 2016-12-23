package cn.org.mingframework.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/example")
@Controller
public class ExampleControler {
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel){
		return "example/list";
	}
}
