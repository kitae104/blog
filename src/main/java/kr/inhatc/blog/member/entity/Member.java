package kr.inhatc.blog.member.entity;


import jakarta.persistence.*;
import kr.inhatc.blog.member.constant.Role;
import kr.inhatc.blog.member.dto.MemberFormDto;
import kr.inhatc.blog.utils.audit.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class Member extends BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;

	private String address;
	
	@Enumerated(EnumType.STRING)  // 문자열 형태로 사용
	@Column(nullable = false, length = 20, columnDefinition = "varchar(20) default 'USER'")	
    private Role role;

	/**
	 *
	 * @param memberFormDto 회원가입 폼
	 * @return 회원가입 폼을 Member 객체로 변환
	 */
	public static Member createMember(MemberFormDto memberFormDto){
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		//String password = passwordEncoder.encode(memberFormDto.getPassword());  // 비밀 번호 암호화
		member.setPassword(memberFormDto.getPassword());
		member.setRole(Role.USER);
		return member;
	}
}
