package hyundai.cc.lecturemanage.lecture.mapper;

import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;

import java.util.List;

public interface LectureMapper {
    public LectureDTO CreateLecture(LectureCreateDTO lec);
    public LectureDTO ReadLecture(LectureCreateDTO lec);
    public int getTotalCount(Criteria cri);
    public List<LectureDTO> getLecturesByPage(Criteria cri);





}
