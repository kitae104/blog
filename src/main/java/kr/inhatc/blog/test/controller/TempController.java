package kr.inhatc.blog.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TempController
{
		
	@GetMapping("/temp/home")
	public String tempHome() {
		log.debug("tempHome()");
		return "temp/home";
	}

}
