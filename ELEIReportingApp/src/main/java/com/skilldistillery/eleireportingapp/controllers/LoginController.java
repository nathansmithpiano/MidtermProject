package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.eleireportingapp.data.UserDAO;
import com.skilldistillery.eleireportingapp.entities.User;

@Controller
public class LoginController {
	
	@Autowired
	UserDAO dao;
	
	//USING
	
	@RequestMapping("login.do")
	public ModelAndView displayLogin(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			mv.addObject("loggedInUser", loggedInUser);
			mv.setViewName("TestUserLogin");
		} else {
			mv.setViewName("login");
		}
		return mv;
	}
	
	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public String submitLogin(User user, HttpSession session) {
		User u = dao.validateLogin(user);
		if (u == null) {
			return "redirect:login.do";
		} else {
			session.setAttribute("loggedInUser", u);
		}
		return "TestUserLogin";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		
		return "home.do";
	}
	
	//NOT USING
	
//	@RequestMapping("beginLogin.do")
//	public ModelAndView displayLogin(HttpSession session) {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("user", new User());
//		if (session.getAttribute("user") != null) {
//			mv.setViewName("redirect:tryLogin.do");
//		} else {
//			mv.addObject("userCommandObject", new User());
//			mv.setViewName("login");
//		}
//		return mv;
//	}
//	
//	@RequestMapping(path = "tryLogin.do", method = RequestMethod.POST)
//	public String submitLogin(User user, HttpSession session) {
//		User u = dao.findByUserName(user.getUsername());
//		System.out.println("*****************************************" + u);
//		if (u == null) {
//			System.out.println("****************** U IS NULL");
//			return "redirect:beginLogin.do";
//		}
//		session.setAttribute("user", u);
//
//		return "users.do";
//	}
//	
//	@RequestMapping("logout.do")
//	public String logout(HttpSession session) {
//		session.removeAttribute("user");
//		
//		return "redirect:index.do";
//	}

}
