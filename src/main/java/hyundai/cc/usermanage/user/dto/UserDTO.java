package hyundai.cc.usermanage.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    //private String email;
    private String password;
    private String username;
    private String nickname;
    private LocalDateTime createDate;
    private boolean enable;
    //private Set<RoleVO> roleList;
    //DB에서 가져오기 위함

}
