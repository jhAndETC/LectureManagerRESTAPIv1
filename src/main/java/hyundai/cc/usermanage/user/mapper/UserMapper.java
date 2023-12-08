package hyundai.cc.usermanage.user.mapper;


import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.RoleVO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserMapper {


    public void createUser(UserCreateDTO user);
    public List<UserDTO> getUserList();
    public List<UserDTO> getUsersByPage(Criteria cri);
    public UserDTO getUserDetail(String userId);
    public void updateUser(@Param("id") String userId, @Param("user") UserCreateDTO user);
    public void deleteUser(String userId);
    public int getTotalCount(Criteria cri);

}
