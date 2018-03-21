package com.whx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.whx.entities.TUser;
import com.whx.service.UserService;

@Controller
public class TestController {

	@Autowired
    private UserService userService;
	
    /**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/test")
    public ModelAndView testMVC(){
        System.out.println("testMVC()");
        List<TUser> list = userService.getAllUsers();
        System.out.println("list.size():"+list.size());
        ModelAndView modelAndView = new ModelAndView("/test");
        modelAndView.addObject("info", "whx"); 
        return modelAndView;
    }
}