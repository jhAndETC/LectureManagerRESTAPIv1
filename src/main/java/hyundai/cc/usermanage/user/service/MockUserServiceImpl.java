package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.usermanage.user.dto.*;
import hyundai.cc.exception.UserCreationException;
import hyundai.cc.exception.UserNotFoundException;
import hyundai.cc.usermanage.user.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Log
@Service
public class MockUserServiceImpl implements UserService{
    private final UserMapper mapper;

    private final UserDTOMapper dtoMapper;
    @Autowired
    public MockUserServiceImpl(UserMapper mapper,UserDTOMapper dtoMapper) {
        this.mapper = mapper; this.dtoMapper=dtoMapper;
    }

    @Override
    public UserDTO createUser(UserCreateRequestDTO user){
        UserCreateDTO userCreateDTO = dtoMapper.toUserCreateDTO(user);
        try{
            mapper.createUser(userCreateDTO);
        } catch (DataAccessException ex){
            throw new UserCreationException("Cannot create user");
        }
        return getUserDetail(userCreateDTO.getId());
    }


    @Override
    public List<UserDTO> getUserList() {
        log.info("get all users....");
        return mapper.getUserList();
    }

    @Override
    public UserDTO getUserDetail(String userid) {
        UserDTO user=mapper.getUserDetail(userid);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + userid);
        }
        return user;
    }

    @Override
    public UserDTO updateUser(String userId,UserCreateRequestDTO user) {
        log.info("update user...." );
        UserCreateDTO userCreateDTO = dtoMapper.toUserCreateDTO(user);
        try{
            mapper.updateUser(userId,userCreateDTO);
        } catch (DataAccessException ex){
            throw new UserCreationException("Cannot update user");
        }
        return getUserDetail(userId);
    }

    @Override
    public UserDTO deleteUser(String userid) {
        log.info("delete user...." + userid);
        UserDTO user=getUserDetail(userid);
        mapper.deleteUser(userid);
        return user;
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
