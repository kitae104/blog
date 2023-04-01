package kr.inhatc.blog.test.controller;


import kr.inhatc.blog.test.entity.Member;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest
{
	
	private static final String TAG = "HttpControllerTest";
	
	@GetMapping("/http/get")  // @RequestParam
	public String getTest(Member m) { 
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail(); 
	}

	@PostMapping("/http/post") // @RequestBody
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
