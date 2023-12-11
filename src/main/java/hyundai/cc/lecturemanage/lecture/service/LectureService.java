package hyundai.cc.lecturemanage.lecture.service;


import hyundai.cc.domain.Criteria;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;

import java.util.List;

public interface LectureService {
    public LectureDTO createLecture(LectureCreateDTO lec);
    public List<LectureDTO> getLectureList();
    public LectureDTO getLectureDetail(Long lectureId);
    public LectureDTO updateLecture(Long lectureId, LectureCreateDTO lec);
    public String deleteLecture(Long lectureId);
    public int getTotal(Criteria cri);
    public List<LectureDTO> getLecturesByPage(Criteria cri);



}
