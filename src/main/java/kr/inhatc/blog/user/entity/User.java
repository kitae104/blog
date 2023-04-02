package kr.inhatc.blog.user.entity;

import jakarta.persistence.*;
import kr.inhatc.blog.member.constant.Role;
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
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)  // 문자열 형태로 사용
    @Column(nullable = false, length = 20, columnDefinition = "varchar(20) default 'USER'")
    private Role role;
}
