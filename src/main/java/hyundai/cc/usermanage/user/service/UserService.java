package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserCreateRequestDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface UserService {
    public UserDTO createUser(UserCreateRequestDTO user);
    public List<UserDTO> getUserList() ;
    public UserDTO getUserDetail(String UserId);
    public UserDTO updateUser(String userId,UserCreateRequestDTO user);
    public UserDTO deleteUser(String userId);
    public List<UserDTO> getUsersByPage(Criteria cri);
    public int getTotal(Criteria cri);

}
