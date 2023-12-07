package hyundai.cc.usermanage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO {
    String role_id;
    String authority;
    //private Set<UserDTO> users;
}
