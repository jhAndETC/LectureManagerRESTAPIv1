package hyundai.cc.lecturemanage.lecture.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class LectureDTOMapper {
    public LectureResponseDTO toLectureResponseDTO(LectureDTO LectureDTO){
        Long lectureId=LectureDTO.getLectureId();
        String title=LectureDTO.getTitle();
        String lecturerName=LectureDTO.getLecturerName();
//        String lecturerIntroduction=LectureDTO.getLecturerIntroduction();
        String centerName=LectureDTO.getCenterName();
        String lectureTime= LectureDTO.getLectureTime();
        LocalDate startDate=LectureDTO.getStartDate();
        LocalDate endDate=LectureDTO.getEndDate();
        int lectureCount=LectureDTO.getLectureCount();
        double price=LectureDTO.getPrice();
        String lectureDescription=LectureDTO.getDescription();
        return new LectureResponseDTO(
                lectureId,
                title,
                lecturerName,
                centerName,
                lectureTime,
                startDate,
                endDate,
                lectureCount,
                price,
                lectureDescription);

    }
}
