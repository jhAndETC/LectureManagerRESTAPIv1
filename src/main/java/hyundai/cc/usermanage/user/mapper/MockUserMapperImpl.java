package hyundai.cc.usermanage.user.mapper;

import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.RoleVO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
@Repository
public class MockUserMapperImpl implements UserMapper{
    @Override
    public UserDTO CreateUser(UserCreateDTO user) {
        String id= user.getId();
        String email=user.getEmail();
        String password= user.getPassword();
        String username=user.getUsername();
        String nickname=user.getNickname();
        LocalDate createDate=user.getCreateDate();
        boolean enable=true;
        Set<RoleVO> roleList=user.getRoleList();


        return new UserDTO(
                id,
                email,
                password,
                username,
                nickname,
                createDate,
                enable,
                roleList);
    }

    @Override
    public ArrayList<UserDTO> getUsersByPage(Criteria cri) {
        return null;
    }

    @Override
    public UserDTO getUserDetail(UserCreateDTO user) {
        return null;
    }

    @Override
    public UserDTO UpdateUser(UserCreateDTO user) {
        return null;
    }

    @Override
    public String DeleteUser(String userId) {
        return null;
    }

    @Override
    public int getTotalCount(Criteria cri) {
        return 0;
    }

}
