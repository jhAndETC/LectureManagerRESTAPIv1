package hyundai.cc.lecturemanage.lecture.mapper;

import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public class LectureMapperIml implements LectureMapper{

    public LectureDTO CreateLecture(LectureCreateDTO lectureCreateDTO) {
        String title=lectureCreateDTO.getTitle();
        Long lecturerId=lectureCreateDTO.getLecturerId();
        String lectureTime=lectureCreateDTO.getLectureTime();
        int centerId=lectureCreateDTO.getCenterId();
        String location=lectureCreateDTO.getLocation();
        LocalDate startDate=lectureCreateDTO.getStartDate();
        LocalDate endDate=lectureCreateDTO.getEndDate();
        int lectureCount=lectureCreateDTO.getLectureCount();
        int price=lectureCreateDTO.getPrice();
        String description=lectureCreateDTO.getDescription();
        Long categoryId=lectureCreateDTO.getCategoryId();
        String categoryName=lectureCreateDTO.getCategoryName();


        return new LectureDTO(
                lecturerId,
                categoryId,
                categoryName,
                title,
                lectureTime,
                startDate,
                endDate,
                centerId,
                location,
                lectureCount,
                price,
                description
        );
    }

    @Override
    public LectureDTO ReadLecture(LectureCreateDTO lec) {
        return null;
    }
}