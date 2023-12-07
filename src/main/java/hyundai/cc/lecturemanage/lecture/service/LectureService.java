package hyundai.cc.lecturemanage.lecture.service;


import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;

import java.util.ArrayList;

public interface LectureService {
    public LectureDTO createLecture(LectureCreateDTO lec);
    public ArrayList<LectureDTO> getLectureList();
    public LectureDTO getLectureDetail(Long lectureId);
    public LectureDTO updateLecture(Long lectureId, LectureCreateDTO lec);
    public String deleteLecture(Long lectureId);

}
