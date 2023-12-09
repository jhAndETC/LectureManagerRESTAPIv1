package hyundai.cc.lecturemanage.lecture.service;

import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.lecturemanage.lecture.mapper.LectureMapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Log
@Service
public class MockLectureServiceImpl implements LectureService{

    @Override
    public LectureDTO createLecture(LectureCreateDTO lec) {
        return null;
    }

    @Override
    public ArrayList<LectureDTO> getLectureList() {
        return null;
    }

    @Override
    public LectureDTO getLectureDetail(Long lectureId) {
        return null;
    }

    @Override
    public LectureDTO updateLecture(Long lectureId, LectureCreateDTO lec) {
        return null;
    }

    @Override
    public String deleteLecture(Long lectureId) {
        return null;
    }
}
