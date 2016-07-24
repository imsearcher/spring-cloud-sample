/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package cloud.simple.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cloud.simple.model.User;
import cloud.simple.service.UserService;



@RestController
public class UserController {
		
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users")
	public ResponseEntity<List<User>> readUserInfo(HttpSession httpSession){
		List<User> users=userService.readUserInfo();		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	@RequestMapping(value="/setMsg")
	public ResponseEntity<String> setMessage(@RequestParam("msg") String msg,HttpServletRequest request, HttpSession httpSession){
		
		httpSession.setAttribute("message", msg);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@RequestMapping(value="/getMsg")
	public ResponseEntity<String> getMessage(HttpServletRequest request, HttpSession httpSession){
		
		String msg =  (String) httpSession.getAttribute("message");
		return new ResponseEntity<String>("your message:"+msg,HttpStatus.OK);
	}
}
