package cn.org.mingframework.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.mingframework.model.entity.system.User;

@Controller
public class BasicController {

	@RequestMapping(value = "/list/{entityname}")
	public ModelAndView showView(@PathVariable ( "entityname" ) String entityName) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("list");
		List<User> users = new ArrayList<User>();
		for(int i = 0;i<=10;i++){
			User user = new User();
			user.setUserName("user"+i);
			
			users.add(user);
		}
		mv.addObject("users", users);
		mv.addObject("entityName", entityName);
		return mv;
	}

	@RequestMapping(value = {"/new/{entityname}", "/modify/{entityname}"})
	public ModelAndView newEntity(@PathVariable ( "entityname" ) String entityName) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("new");
		List<User> users = new ArrayList<User>();
		for(int i = 0;i<=10;i++){
			User user = new User();
			user.setUserName("user"+i);
			
			users.add(user);
		}
		mv.addObject("users", users);
		mv.addObject("entityName", entityName);
		return mv;
	}
	
	@RequestMapping(value = "/main*")
	public String mainView(Model model){
		return "main";
	}
}
