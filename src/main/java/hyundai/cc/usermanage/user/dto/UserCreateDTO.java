package hyundai.cc.usermanage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class UserCreateDTO {
    private String id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private LocalDate createDate;
    private boolean enable;
    private Set<RoleVO> roleList;

}
