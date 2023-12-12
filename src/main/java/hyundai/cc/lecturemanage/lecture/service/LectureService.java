package hyundai.cc.lecturemanage.lecture.service;


import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateRequestDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.lecturemanage.lecturer.dto.LecturerDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;

import java.util.List;

public interface LectureService {
    public LectureDTO createLecture(LectureCreateRequestDTO lec);
    public List<LectureDTO> getLectureList();
    public LectureDTO getLectureDetail(Long lectureId);
    public LectureDTO updateLecture(Long lectureId, LectureCreateRequestDTO lec);
    public LectureDTO deleteLecture(Long lectureId);
    public int getTotal(Criteria cri);
    public List<LectureDTO> getLecturesByPage(Criteria cri);
    public LecturerDTO getLectureLecturer(Long lectureId);
    public LecturerDTO findLecturerByEmail(String lecturerEmail);
    public Long findCategoryIdByName(String categoryName);
    public Long findCenterIdByName(String centerName);

}
