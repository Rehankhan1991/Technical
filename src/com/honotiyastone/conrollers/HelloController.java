package com.honotiyastone.conrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping("/login")
	protected ModelAndView login() {
		ModelAndView obj = new ModelAndView("HelloPage");
		obj.addObject("msg", "hi User'Welcome to our page");

		return obj;
	}

}
