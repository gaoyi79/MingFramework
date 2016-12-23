package cn.org.mingframework.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleControler {
	
	@RequestMapping(value = "/example"/*, method = RequestMethod.GET*/)
	public String list(Model uiModel){
		return "example/list";
	}
	
	@RequestMapping(value = "/main")
	public String main(Model model){
		return "mainView";
	}
}
