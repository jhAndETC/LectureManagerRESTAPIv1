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

    @NotEmpty(message = "Username should not be empty")
    @Size(max = 100, message = "Username must be at most 100 characters")
    private String username;

    @NotEmpty(message = "Nickname should not be empty")
    @Size(max = 50, message = "Nickname must be at most 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Nickname can only contain letters and numbers")
    private String nickname;
    @Size(max = 1000, message = "Username must be at most 50 characters")
    private String introduction;
    //생성 요청 DTO (Controller)
}
