package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.firstzone.myapp.emp.EmpDTO;
import org.firstzone.myapp.emp.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	EmpService eService;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login.do")
	public void loginDisplay() {
		logger.debug("login.do 요청(debug)");
		logger.debug("login.do 요청(info)");
		logger.warn("login.do 요청(warn)");
		logger.error("login.do 요청(error)");
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.do";
	}
	
	@PostMapping("/login.do")
	public String loginCheck(@RequestParam("email") String email, 
			               @RequestParam("pswd") String phone,
			               HttpSession session,
			               HttpServletRequest request) {
		//mybatis로 변경했음(JDBC에서는 -1: 존재하지 않는 ID -2: password 오류)
		EmpDTO emp = eService.loginCheck(email, phone);
		if(emp==null || emp.getEmployee_id()==-1) {
			session.setAttribute("loginResult", "존재하지 않는 ID입니다.");
			return "redirect:login.do";
		} else if(emp.getEmployee_id()==-2) {
			session.setAttribute("loginResult", "password 오류입니다.");
			return "redirect:login.do";
		} else {
			//로그인 성공
			session.setAttribute("loginResult", "login 성공");
			session.setAttribute("emp", emp);
			
			String lastRequest = (String)session.getAttribute("lastRequest");
			String goPage;
			if(lastRequest==null) {
				//처음부터 로그인 요청한 경우
				goPage="../index.do";
			} else {
				//로그인 없이 다른 페이지를 요청한 경우
				int length = request.getContextPath().length();
				goPage = lastRequest.substring(length);
				String queryString = (String)session.getAttribute("queryString");
				if(queryString!=null) goPage = goPage + "?" + queryString;
			}
			return "redirect:" + goPage;
		}
	}
}
