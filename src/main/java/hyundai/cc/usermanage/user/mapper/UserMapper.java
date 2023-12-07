package hyundai.cc.usermanage.user.mapper;


import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.RoleVO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public interface UserMapper {


    public void createUser(UserCreateDTO user);
    public ArrayList<UserDTO> getUserList();
    public ArrayList<UserDTO> getUsersByPage(Criteria cri);
    public UserDTO getUserDetail(String userId);
    public UserDTO updateUser(String userId,UserCreateDTO user);
    public String deleteUser(String userId);
    public int getTotalCount(Criteria cri);

}
