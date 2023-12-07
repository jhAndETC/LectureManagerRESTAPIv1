package hyundai.cc.lecturemanage.lecture.mapper;

import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;

public interface LectureMapper {
    public LectureDTO CreateLecture(LectureCreateDTO lec);
    public LectureDTO ReadLecture(LectureCreateDTO lec);





}
