package kr.inhatc.blog.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member
{
	private int id;
	private String username;
	private String password; 
	private String email;	
}
