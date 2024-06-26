package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.exception.DuplicateEmailException;
import hyundai.cc.exception.DuplicateNicknameException;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.usermanage.user.dto.*;
import hyundai.cc.exception.UserCreationException;
import hyundai.cc.exception.UserNotFoundException;
import hyundai.cc.usermanage.user.mapper.UserMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("UK_NICKNAME")) {
                throw new DuplicateNicknameException("이미 존재하는 닉네임입니다.");
            } else if (ex.getMessage().contains("UK_EMAIL")) {
                throw new DuplicateEmailException("이미 가입한 이메일입니다.");
            } else {
                throw new RuntimeException("Unique constraint violation: " + ex.getMessage());
            }
        }
        usermapper.createRole(userCreateDTO.getId());
        return getUserDetail(userCreateDTO.getId());
    }


    @Override
    public List<UserDTO> getUserList() {
        log.info("get all users....");
        return usermapper.getUserList();
    }

    @Override
    public String getUuidByEmail(String email) {
        return usermapper.selectUuidByEmail(email);
    }

    @Override
    public UserDTO getUserDetail(String userid) {
        UserDTO user=usermapper.getUserDetail(userid);
        if (user == null) {
            throw new UserNotFoundException("존재하지 않는 회원입니다");
        }
        return user;
    }

    @Override
    public UserDTO updateUser(String userId,UserUpdateRequestDTO user) {
        UserDTO userDTO=getUserDetail(userId);
        UserCreateDTO updateUser=userdtoMapper.FromUpdatetoUserCreateDTO(userDTO,user);
        try{
            usermapper.updateUser(userId,updateUser);
        } catch (DataAccessException ex){
            throw new UserCreationException("회원 정보를 업데이트할 수 없습니다.");
        }
        return getUserDetail(userId);
    }

    @Override
    public void deleteUser(String userid) {
        UserDTO user=getUserDetail(userid);
        usermapper.deleteUser(userid);
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
    public int getProgressCount(String userId,Criteria cri) {
        return usermapper.getProgressCount(userId,cri);
    }
    @Override
    public int getFinishCount(String userId,Criteria cri) {
        return usermapper.getFinishCount(userId,cri);
    }
    @Override
    public int getLikedCount(String userId,Criteria cri) {
        return usermapper.getLikedCount(userId,cri);
    }


    @Override
    public List<LectureDTO> findProgressCourses(String userId,Criteria cri) {
        return usermapper.findProgressCourses(userId,cri);
    }

    @Override
    public List<LectureDTO> findFinishCourses(String userId,Criteria cri) {
        return usermapper.findFinishCourses(userId,cri);
    }

    @Override
    public List<LectureDTO> findLikedCourses(String userId,Criteria cri) {
        return usermapper.findLikedCourses(userId,cri);
    }


}
