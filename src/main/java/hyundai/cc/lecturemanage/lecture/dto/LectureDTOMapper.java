package hyundai.cc.lecturemanage.lecture.dto;

import hyundai.cc.lecturemanage.lecturer.dto.LecturerDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class LectureDTOMapper {

    public LectureCreateDTO toLectureCreateDTO(LectureCreateRequestDTO lectureCreateRequestDTO, LecturerDTO lecturerDTO,Long categoryId,Long centerId){
        Long lectureId=1L;
        String title=lectureCreateRequestDTO.getTitle();
        String lecturerId=lecturerDTO.getId();
        String lectureTime=lectureCreateRequestDTO.getLectureTime();
        String location=lectureCreateRequestDTO.getLocation();
        LocalDate startDate=lectureCreateRequestDTO.getStartDate();
        LocalDate endDate=lectureCreateRequestDTO.getEndDate();
        int lectureCount=lectureCreateRequestDTO.getLectureCount();
        double price=lectureCreateRequestDTO.getPrice();
        String description=lectureCreateRequestDTO.getLectureDescription();

        return new LectureCreateDTO(
                lectureId,
                title,
                lecturerId,
                lectureTime,
                centerId,
                location,
                startDate,
                endDate,
                lectureCount,
                price,
                description,
                categoryId

        );
    }
    public LectureResponseDTO toLectureResponseDTO(LectureDTO lectureDTO){
        Long lectureId=lectureDTO.getLectureId();
        String title=lectureDTO.getTitle();
        String lecturerEmail=lectureDTO.getLecturerEmail();
        String lecturerName=lectureDTO.getLecturerName();
//        String lecturerIntroduction=LectureDTO.getLecturerIntroduction();
        String centerName=lectureDTO.getCenterName();
        String lectureTime= lectureDTO.getLectureTime();
        LocalDate startDate=lectureDTO.getStartDate();
        LocalDate endDate=lectureDTO.getEndDate();
        String location=lectureDTO.getLocation();
        int lectureCount=lectureDTO.getLectureCount();
        double price=lectureDTO.getPrice();
        String lectureDescription=lectureDTO.getDescription();
        String categoryName=lectureDTO.getCategoryName();
        return new LectureResponseDTO(
                lectureId,
                title,
                lecturerEmail,
                lecturerName,
                centerName,
                lectureTime,
                startDate,
                endDate,
                location,
                lectureCount,
                price,
                lectureDescription,
                categoryName);

    }
}
