package hyundai.cc.usermanage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String username;
    private String nickname;
    private LocalDateTime createDate;
    //사용자에게 보여지는 DTO (Controller)
}
