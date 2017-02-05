package cn.org.mingframework.controller.additional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import com.google.common.collect.Lists;

import cn.org.mingframework.system.spring.MingModelAndView;

@Controller
public class AdditionalControler {

	private static final String IMAGE_PATH = "D:\\Download\\PIC\\Commic\\";
	
	@Autowired
	AdditionalService additionalService;
	
	@RequestMapping(value = "/page")
	public ModelAndView getPage(HttpServletRequest request)
	{
		System.out.println(getDevice(request));
		return getPageModelAndView(1, request).addObject("request", request);
	}
	
	@RequestMapping(value = "/page/id={pageindex}")
	public ModelAndView getPage(@PathVariable ( "pageindex" ) int pageIndex,HttpServletRequest request)
	{
		System.out.println(getDevice(request));
		return getPageModelAndView(pageIndex, request).addObject("request", request);
	}
	
	private ModelAndView getPageModelAndView(int pageIndex, HttpServletRequest request){
		MingModelAndView mv = new MingModelAndView();
		Page<Article> articlePage = additionalService.listArticle(pageIndex);
		if( isMobile(request))
			mv.setViewName("mobil/page");
		else
			mv.setViewName("page");
		mv.addObject("articles", Lists.newArrayList(articlePage.iterator()));
		mv.addObject("maxPage", articlePage.getTotalPages() + 1);
		mv.addObject("currentPage", articlePage.getNumber() + 1);
		return mv;
	}
	
	@RequestMapping(value = "/article/id={articleid}")
	public ModelAndView getArticle(@PathVariable ( "articleid" ) String articleId,HttpServletRequest request) throws NamingException
	{
		ModelAndView mv = new ModelAndView();
		Article article = additionalService.findArticleByArticleId(articleId);
		List<Image> images = additionalService.listImage(articleId);
		Collections.sort(images, new Comparator<Image>(){

			public int compare(Image o1, Image o2) {
				if(o1.getItem()>o2.getItem()){
					return 1;
				}else{
					return -1;
				}
			}});
		mv.setViewName("article");
		mv.addObject("article", article);
		mv.addObject("images", images);
		mv.addObject("request", request);
		mv.addObject("device", getDevice(request));
		System.out.println(getDevice(request));
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
	
	private String getDevice(HttpServletRequest request){
		Device device = DeviceUtils.getCurrentDevice(request);
		return device.toString()+ "  -  "+device.getDevicePlatform().name();
	}
	
	private Boolean isMobile(HttpServletRequest request){
		Device device = DeviceUtils.getCurrentDevice(request);
		return device.isMobile()||device.isTablet();
	}
}
