package cn.skq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping(value="/welcome.do")
	public ModelAndView welcome(){
		ModelAndView mmv = new ModelAndView();
		mmv.setViewName("welcome");
		return mmv;
	}
	
	@RequestMapping(value="/vueTest.do")
	public ModelAndView vueTest(){
		ModelAndView mmv = new ModelAndView();
		mmv.setViewName("vueTest");
		return mmv;
	}

}
