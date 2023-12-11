package hyundai.cc.lecturemanage.lecture.mapper;

import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.lecturemanage.lecturer.dto.LecturerDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LectureTestMapper {
    public void createLecture(LectureCreateDTO lec);//
    public void deleteLecture(Long lectureID);//
    public void updateLecture(@Param("id") Long lectureId, @Param("lecture") LectureCreateDTO user);//
    public List<LectureDTO> getLectureList();//
    public LectureDTO getLectureDetail(Long lectureId);//
    public int getTotalCount(Criteria cri);//
    public List<LectureDTO> getLecturesByPage(Criteria cri);//
    public LecturerDTO findLecturerByEmail(String lectureremail);//
    public Long findCategoryIdByName(String categoryName);//
    public Long findCenterIdByName(String centerName);//
    public LecturerDTO  getLectureLecturer(Long lectureId);


}
