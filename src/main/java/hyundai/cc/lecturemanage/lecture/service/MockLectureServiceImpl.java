package hyundai.cc.lecturemanage.lecture.service;

import hyundai.cc.domain.Criteria;
import hyundai.cc.exception.LectureNotFoundException;
import hyundai.cc.exception.LecturerNotFoundException;
import hyundai.cc.exception.UserCreationException;
import hyundai.cc.exception.UserNotFoundException;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateRequestDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
import hyundai.cc.lecturemanage.lecture.mapper.LectureTestMapper;
import hyundai.cc.lecturemanage.lecturer.dto.LecturerDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class MockLectureServiceImpl implements LectureService{

    private final LectureTestMapper lectureMapper;

    private final LectureDTOMapper lectureDTOMapper;
    @Autowired
    public MockLectureServiceImpl(LectureTestMapper lectureMapper,LectureDTOMapper lectureDTOMapper) {
        this.lectureMapper = lectureMapper; this.lectureDTOMapper=lectureDTOMapper;
    }

    @Override
    public LectureDTO createLecture(LectureCreateRequestDTO lec) {
        LecturerDTO lecturerDTO=findLecturerByEmail(lec.getLectureremail());
        Long categoryId=findCategoryIdByName(lec.getCategoryname());
        Long centerId=findCenterIdByName(lec.getCentername());
        LectureCreateDTO lectureCreateDTO=lectureDTOMapper.toLectureCreateDTO(lec,lecturerDTO,categoryId,centerId);
        try{
            lectureMapper.createLecture(lectureCreateDTO);
        } catch (DataAccessException ex){
            throw new UserCreationException("Cannot create lecture");
        }
        return getLectureDetail(lectureCreateDTO.getLectureId());
    }

    @Override
    public LectureDTO updateLecture(Long lectureId, LectureCreateRequestDTO lec) {
        LectureDTO lectureDTO=getLectureDetail(lectureId);
        LecturerDTO lecturerDTO=findLecturerByEmail(lec.getLectureremail());
        Long categoryId=findCategoryIdByName(lec.getCategoryname());
        Long centerId=findCenterIdByName(lec.getCentername());
        LectureCreateDTO lectureCreateDTO=lectureDTOMapper.toLectureCreateDTO(lec,lecturerDTO,categoryId,centerId);
        try {
            lectureMapper.updateLecture(lectureId, lectureCreateDTO);
        } catch(DataAccessException ex){
            throw new LectureNotFoundException("Cannot update lecture");
        }
        return getLectureDetail(lectureId);
    }

    @Override
    public List<LectureDTO> getLectureList() {
        return lectureMapper.getLectureList();
    }

    @Override
    public LectureDTO getLectureDetail(Long lectureId) {
        LectureDTO lecture=lectureMapper.getLectureDetail(lectureId);
        if (lecture == null) {
            throw new LectureNotFoundException("Lecture not found with ID: " + lectureId);
        }
        return lecture;

    }
    @Override
    public LecturerDTO findLecturerByEmail(String lectureremail){
        LecturerDTO lecturerDTO=lectureMapper.findLecturerByEmail(lectureremail);
        if (lecturerDTO==null){
            throw new LecturerNotFoundException("Lecturer not found with email: " + lectureremail);
        }
        return lecturerDTO;
    }

    @Override
    public Long findCategoryIdByName(String categoryName) {
        return lectureMapper.findCategoryIdByName(categoryName);
    }

    @Override
    public Long findCenterIdByName(String centerName) {
        return lectureMapper.findCenterIdByName(centerName);
    }



    @Override
    public LectureDTO deleteLecture(Long lectureId) {
        LectureDTO lecture=getLectureDetail(lectureId);
        lectureMapper.deleteLecture(lectureId);
        return lecture;
    }

    @Override
    public int getTotal(Criteria cri) {
        return lectureMapper.getTotalCount(cri);
    }

    @Override
    public List<LectureDTO> getLecturesByPage(Criteria cri) {
        return lectureMapper.getLecturesByPage(cri);
    }

    @Override
    public List<LectureDTO> getLecturesList() {
        return lectureMapper.getLectureList();
    }

    @Override
    public LecturerDTO getLectureLecturer(Long lectureId) {
        return lectureMapper.getLectureLecturer(lectureId);
    }
}
