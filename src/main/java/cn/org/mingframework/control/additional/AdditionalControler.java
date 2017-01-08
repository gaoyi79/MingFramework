package cn.org.mingframework.control.additional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

@Controller
public class AdditionalControler {

	private static final String IMAGE_PATH = "D:\\Download\\PIC\\Commic\\";
	
	@Autowired
	AdditionalService additionalService;
	
	@RequestMapping(value = "/page")
	public ModelAndView getPage()
	{
		return getPageModelAndView(1);
	}
	
	@RequestMapping(value = "/page/id={pageindex}")
	public ModelAndView getPage(@PathVariable ( "pageindex" ) int pageIndex)
	{
		return getPageModelAndView(pageIndex);
	}
	
	private ModelAndView getPageModelAndView(int pageIndex){
		ModelAndView mv = new ModelAndView();
		Page<Article> articlePage = additionalService.listArticle(pageIndex);
		mv.setViewName("page");
		mv.addObject("articles", Lists.newArrayList(articlePage.iterator()));
		mv.addObject("maxPage", articlePage.getTotalPages());
		mv.addObject("currentPage", articlePage.getNumber());
		return mv;
	}
	
	@RequestMapping(value = "/article/id={articleid}")
	public ModelAndView getArticle(@PathVariable ( "articleid" ) String articleId) throws NamingException
	{
		ModelAndView mv = new ModelAndView();
		List<Image> images = additionalService.listImage(articleId);
		mv.setViewName("article");
		mv.addObject("images", images);
		return mv;
	}
	
	//OK
	@RequestMapping("/showimage/articleid={articleid}&item={item}")
	public void getIcon(@PathVariable("articleid") String articleId,
			@PathVariable("item") int item,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException{
		String patten = new DecimalFormat("0000").format(item);
		File dir = new File(IMAGE_PATH + articleId);
		
		File[]	files = dir.listFiles();
		if(files == null)
			return;
		
		for(File file : files){
			if(file.isFile() && file.getName().subSequence(0, patten.length()).equals(patten)){
				FileInputStream inputStream = new FileInputStream(IMAGE_PATH +  articleId + "\\" + file.getName());
				byte[] data = new byte[(int)file.length()];
				inputStream.read(data);
				inputStream.close();

		        response.setContentType("image/jpg");

		        OutputStream stream = response.getOutputStream();
		        stream.write(data);
		        stream.flush();
		        stream.close();
		        
		        return;
			}
		}
	}
}
