package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
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
    private final UserMapper usermapper;

    private final UserDTOMapper userdtoMapper;
    @Autowired
    public MockUserServiceImpl(UserMapper mapper,UserDTOMapper dtoMapper) {
        this.usermapper = mapper; this.userdtoMapper=dtoMapper;
    }

    @Override
    public UserDTO createUser(UserCreateRequestDTO user){
        UserCreateDTO userCreateDTO = userdtoMapper.toUserCreateDTO(user);
        try{
            usermapper.createUser(userCreateDTO);
        } catch (DataAccessException ex){
            throw new UserCreationException("Cannot create user");
        }
        return getUserDetail(userCreateDTO.getId());
    }


    @Override
    public List<UserDTO> getUserList() {
        log.info("get all users....");
        return usermapper.getUserList();
    }

    @Override
    public UserDTO getUserDetail(String userid) {
        UserDTO user=usermapper.getUserDetail(userid);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + userid);
        }
        return user;
    }

    @Override
    public UserDTO updateUser(String userId,UserUpdateRequestDTO user) {
        log.info("update user...." );
        UserDTO userDTO=getUserDetail(userId);
        UserCreateDTO updateUser=userdtoMapper.FromUpdatetoUserCreateDTO(userDTO,user);
        try{
            usermapper.updateUser(userId,updateUser);
        } catch (DataAccessException ex){
            throw new UserCreationException("Cannot update user");
        }
        return getUserDetail(userId);
    }

    @Override
    public UserDTO deleteUser(String userid) {
        log.info("delete user...." + userid);
        UserDTO user=getUserDetail(userid);
        usermapper.deleteUser(userid);
        return user;
    }

    @Override
    public List<UserDTO> getUsersByPage(Criteria cri) {
        return usermapper.getUsersByPage(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        return usermapper.getTotalCount(cri);
    }

    @Override
    public List<LectureDTO> findProgressCourses(String userId) {
        log.info("get user progress course....");
        return usermapper.findProgressCourses(userId);
    }

    @Override
    public List<LectureDTO> findFinishCourses(String userId) {
        log.info("get user progress course....");
        return usermapper.findFinishCourses(userId);
    }

    @Override
    public List<LectureDTO> findLikedCourses(String userId) {
        log.info("get user like course....");
        return usermapper.findLikedCourses(userId);
    }


}
