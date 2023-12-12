package hyundai.cc.usermanage.user.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Getter
public class UserUpdateRequestDTO {


//    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    private String password;

    @NotEmpty(message = "이름을 입력해주세요")
    @Size(max = 100, message = "이름은 최대 100자까지 가능합니다")
    private String username;

    @NotEmpty(message = "닉네임을 입력해주세요")
    @Size(max = 50, message = "닉네임은 최대 50자까지 가능합니다")
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣]+$", message = "특수문자 제외 50자까지 가능합니다")
    private String nickname;
    @Size(max = 1000, message = "자기소개는 최대 1000자까지 가능합니다")
    private String introduction;
    //생성 요청 DTO (Controller)
}
