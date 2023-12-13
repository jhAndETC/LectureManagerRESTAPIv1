package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.usermanage.user.dto.UserCreateRequestDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import hyundai.cc.usermanage.user.dto.UserUpdateRequestDTO;

import java.util.List;

public interface UserService {
    public UserDTO createUser(UserCreateRequestDTO user);
    public List<UserDTO> getUserList() ;
    public String getUuidByEmail(String email);
    public UserDTO getUserDetail(String UserId);
    public UserDTO updateUser(String userId, UserUpdateRequestDTO user);
    public void deleteUser(String userId);
    public List<UserDTO> getUsersByPage(Criteria cri);
    public int getTotal(Criteria cri);
    public int getProgressCount(String userId,Criteria cri);
    public int getFinishCount(String userId,Criteria cri);
    public int getLikedCount(String userId,Criteria cri);

    //강의 목록 조회
    public List<LectureDTO> findProgressCourses(String userId,Criteria cri);
    public List<LectureDTO> findFinishCourses(String userId,Criteria cri);
    public List<LectureDTO> findLikedCourses(String userId,Criteria cri);


}
