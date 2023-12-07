package hyundai.cc.usermanage.user.mapper;


import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.RoleVO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public interface UserMapper {


    public UserDTO CreateUser(UserCreateDTO user);
    public ArrayList<UserDTO> getUsersByPage(Criteria cri);
    public UserDTO getUserDetail(UserCreateDTO user);
    public UserDTO UpdateUser(UserCreateDTO user);
    public String DeleteUser(String userId);
    public int getTotalCount(Criteria cri);

}
