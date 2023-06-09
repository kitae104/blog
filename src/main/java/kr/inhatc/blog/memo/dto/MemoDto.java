package kr.inhatc.blog.memo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class MemoDto {

    private Long id;
    @NotBlank(message = "사용자명은 필수 입력 값입니다.")
    private String userName;
    
    @NotNull(message = "내용 입력은 필수 입력 값입니다.")
    private String contents;

    private LocalDateTime modifiedBy;
}
