package kr.inhatc.blog.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesetController
{
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello Spring Test</h1>";
	}
}
