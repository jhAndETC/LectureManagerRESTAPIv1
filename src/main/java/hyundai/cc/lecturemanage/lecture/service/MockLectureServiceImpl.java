package hyundai.cc.lecturemanage.lecture.service;

import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.lecturemanage.lecture.mapper.LectureMapper;
import hyundai.cc.lecturemanage.lecture.mapper.LectureMapperIml;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Log
@Service
public class MockLectureServiceImpl implements LectureService{
    private final LectureMapper Mapper;
    public MockLectureServiceImpl(LectureMapperIml Mapper) {
        this.Mapper = Mapper;
    }
    @Override
    public LectureDTO createLecture(LectureCreateDTO lec) {
        log.info("create lecture....  "+lec.getTitle());
        return Mapper.CreateLecture(lec);
    }



    @Override
    public ArrayList<LectureDTO> getLectureList() {
        log.info("get all lecture....");
        LectureDTO tmp=new LectureDTO(34565L,554467L,"무역센터점","연필로 시작하는 어반스케치 2교시", "매주 목 11:35-13:05", LocalDate.of(2023, 12, 7), LocalDate.of(2024, 12, 15),7,"지상 11층 3 스타일H",11,150000,"test1");
        LectureDTO tmp2=new LectureDTO(34566L,554467L,"무역센터점","연필로 시작하는 어반스케치 2교시", "매주 목 11:35-13:05", LocalDate.of(2023, 12, 7), LocalDate.of(2024, 12, 15),7,"지상 11층 3 스타일H",11,150000,"test2");
        ArrayList<LectureDTO> lectures = new ArrayList<LectureDTO>(Arrays.asList(tmp,tmp2));
        return lectures;
    }

    @Override
    public LectureDTO getLectureDetail(Long lectureId) {
        log.info("get lecture detail....");
        LectureDTO tmp2=new LectureDTO(34566L,554467L,"무역센터점","연필로 시작하는 어반스케치 2교시", "매주 목 11:35-13:05", LocalDate.of(2023, 12, 7), LocalDate.of(2024, 12, 15),7,"지상 11층 3 스타일H",11,150000,"test2");
        return tmp2;
    }

    @Override
    public LectureDTO updateLecture(Long lectureId, LectureCreateDTO lec) {
        log.info("update lecture...." + lectureId);
        LectureDTO update=new LectureDTO(
                lectureId,
                lec.getCategoryId(),
                lec.getCategoryName(),
                lec.getTitle(),
                lec.getLectureTime(),
                lec.getStartDate(),
                lec.getEndDate(),
                lec.getCenterId(),
                lec.getLocation(),
                lec.getLectureCount(),
                lec.getPrice(),
                lec.getDescription()
                );
        return update;
    }

    @Override
    public String deleteLecture(Long lectureId) {
        log.info("delete lecture...." + lectureId);
        String msg="delete " +lectureId;
        return msg;
    }
}
