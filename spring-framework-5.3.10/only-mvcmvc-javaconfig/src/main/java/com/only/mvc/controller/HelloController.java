package com.only.mvc.controller;

import com.only.mvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private HelloService helloService;

	@GetMapping("/hello")
	public String hello(){

		helloService.sayHello();

		return "hello";
	}


}
