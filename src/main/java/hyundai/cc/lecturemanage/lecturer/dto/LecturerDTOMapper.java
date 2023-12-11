package hyundai.cc.lecturemanage.lecturer.dto;

import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;

public class LecturerDTOMapper {
    public LecturerResponseDTO toLecturerResponseDTO(LecturerDTO lecturerDTO){
        return new LecturerResponseDTO(
                lecturerDTO.getLecturerName(),
                lecturerDTO.getLecturerIntroduction());

    }
}
