package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.RoleVO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
//import hyundai.cc.usermanage.user.mapper.MockUserMapperImpl;
import hyundai.cc.usermanage.user.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Log
@Service
public class MockUserServiceImpl implements UserService{
    private final UserMapper Mapper;
    @Autowired
    public MockUserServiceImpl(UserMapper Mapper) {
        this.Mapper = Mapper;
    }

    @Override
    public UserDTO createUser(UserCreateDTO user) {
        log.info("create user....  "+user.getUsername());
        //return Mapper.CreateUser(user);
        String uuid= UUID.randomUUID().toString();
        user.setId(uuid);
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return Mapper.CreateUser(user);
    }

    @Override
    public ArrayList<UserDTO> getUserList() {
        log.info("get all users....");
        RoleVO role1 = new RoleVO("1L","ROLE_ADMIN");
        Set<RoleVO> roleList = new HashSet<>();
        roleList.add(role1);
        UserDTO user1=new UserDTO(UUID.randomUUID().toString(),"test@gmail.com","1234","user1","nickname1", LocalDate.now(), false, roleList);
        ArrayList<UserDTO> users=new ArrayList<>(Arrays.asList(user1));
        return users;
    }

    @Override
    public UserDTO getUserDetail(String UserId) {
        log.info("get user detail....");
        RoleVO role1 = new RoleVO("1L","ROLE_ADMIN");
        Set<RoleVO> roleList = new HashSet<>();
        roleList.add(role1);
        UserDTO user1=new UserDTO(UserId,"test@gmail.com","1234","user1","nickname1", LocalDate.now(), false,roleList);
        return user1;
    }

    @Override
    public UserDTO updateUser(String UserId, UserCreateDTO user) {
        log.info("update user...." + UserId);
        UserDTO user1=new UserDTO(UserId,user.getEmail(),"5678","user1","nickname2", user.getCreateDate(), false,user.getRoleList());
        return null;
    }

    @Override
    public String deleteUser(String UserId) {
        log.info("delete user...." + UserId);
        String msg="delete " +UserId;
        return msg;
    }

    @Override
    public List<UserDTO> getUsersByPage(Criteria cri) {
        return Mapper.getUsersByPage(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        return Mapper.getTotalCount(cri);
    }
}
