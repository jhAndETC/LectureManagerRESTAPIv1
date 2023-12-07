package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.*;
//import hyundai.cc.usermanage.user.mapper.MockUserMapperImpl;
import hyundai.cc.usermanage.user.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Log
@Service
public class MockUserServiceImpl implements UserService{
    private final UserMapper mapper;

    private final DTOMapper dtoMapper;
    @Autowired
    public MockUserServiceImpl(UserMapper mapper,DTOMapper dtoMapper) {
        this.mapper = mapper; this.dtoMapper=dtoMapper;
    }

    @Override
    public UserDTO createUser(UserCreateRequestDTO user) {
        log.info("create user....  "+user.getUsername());

        UserCreateDTO userCreateDTO = dtoMapper.toUserCreateDTO(user);
        mapper.createUser(userCreateDTO);
//        return mapper.getUserDetail(userCreateDTO.getId());
        return getUserDetail(userCreateDTO.getId());
    }


    @Override
    public ArrayList<UserDTO> getUserList() {
        log.info("get all users....");
//        RoleVO role1 = new RoleVO("1L","ROLE_ADMIN");
//        Set<RoleVO> roleList = new HashSet<>();
//        roleList.add(role1);
//        UserDTO user1=new UserDTO(UUID.randomUUID().toString(),"test@gmail.com","1234","user1","nickname1", LocalDate.now(), false, roleList);
//        ArrayList<UserDTO> users=new ArrayList<>(Arrays.asList(user1));
        return mapper.getUserList();
    }

    @Override
    public UserDTO getUserDetail(String UserId) {
        log.info("get user detail....");

        return mapper.getUserDetail(UserId);
    }

    @Override
    public UserDTO updateUser(String userId,UserCreateDTO user) {
        log.info("update user...." );
       //UserDTO user1=new UserDTO(UserId,user.getEmail(),"5678","user1","nickname2", user.getCreateDate(), false,user.getRoleList());
        return mapper.updateUser(userId,user);
    }

    @Override
    public String deleteUser(String UserId) {
        log.info("delete user...." + UserId);
        String msg="delete " +UserId;
        return msg;
    }

    @Override
    public List<UserDTO> getUsersByPage(Criteria cri) {
        return mapper.getUsersByPage(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        return mapper.getTotalCount(cri);
    }
}
