package hyundai.cc.lecturemanage.lecturer.dto;

import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import org.springframework.stereotype.Component;

@Component
public class LecturerDTOMapper {
    public LecturerResponseDTO toLecturerResponseDTO(LecturerDTO lecturerDTO){
        return new LecturerResponseDTO(
                lecturerDTO.getUserName(),
                lecturerDTO.getIntroduction());

    }
}
