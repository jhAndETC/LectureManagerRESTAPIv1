//package hyundai.cc.lecturemanage.lecture.service;
//
//import hyundai.cc.domain.Criteria;
//import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
//import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
//import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
//import hyundai.cc.lecturemanage.lecture.mapper.LectureMapper;
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Log
//@Service
//public class MockLectureServiceImpl implements LectureService{
//
//    private final LectureMapper lectureMapper;
//
//    private final LectureDTOMapper lectureDTOMapper;
//    @Autowired
//    public MockLectureServiceImpl(LectureMapper lectureMapper,LectureDTOMapper lectureDTOMapper) {
//        this.lectureMapper = lectureMapper; this.lectureDTOMapper=lectureDTOMapper;
//    }
//
//    @Override
//    public LectureDTO createLecture(LectureCreateDTO lec) {
//        return null;
//    }
//
//    @Override
//    public ArrayList<LectureDTO> getLectureList() {
//        return null;
//    }
//
//    @Override
//    public LectureDTO getLectureDetail(Long lectureId) {
//        return null;
//    }
//
//    @Override
//    public LectureDTO updateLecture(Long lectureId, LectureCreateDTO lec) {
//        return null;
//    }
//
//    @Override
//    public String deleteLecture(Long lectureId) {
//        return null;
//    }
//
//    @Override
//    public int getTotal(Criteria cri) {
//        return lectureMapper.getTotalCount(cri);
//    }
//
//    @Override
//    public List<LectureDTO> getLecturesByPage(Criteria cri) {
//        return lectureMapper.getLecturesByPage(cri);
//    }
//}
