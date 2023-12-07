package hyundai.cc.usermanage.user.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserCreateRequestDTO {
    private String password;
    private String username;
    private String nickname;
    //생성 요청 DTO (Controller)
}
