package hyundai.cc.usermanage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserCreateDTO {
    private String id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private boolean enable;
    //DB에 넣기 위함

}
