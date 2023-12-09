package hyundai.cc.usermanage.user.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.usermanage.user.dto.UserCreateRequestDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import java.util.List;

public interface UserService {
    public UserDTO createUser(UserCreateRequestDTO user);
    public List<UserDTO> getUserList() ;
    public UserDTO getUserDetail(String UserId);
    public UserDTO updateUser(String userId,UserCreateRequestDTO user);
    public UserDTO deleteUser(String userId);
    public List<UserDTO> getUsersByPage(Criteria cri);
    public int getTotal(Criteria cri);

    //강의 목록 조회
    public List<LectureDTO> findProgressCourses(String UserId);
    public List<LectureDTO> findFinishCourses(String UserId);
    public List<LectureDTO> findLikedCourses(String UserId);


}
