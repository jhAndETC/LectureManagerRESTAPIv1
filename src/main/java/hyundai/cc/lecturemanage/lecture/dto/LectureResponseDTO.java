package hyundai.cc.lecturemanage.lecture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponseDTO {

    private Long id;
    private String title;
    private String lecturerEmail;
    private String lecturerName;
//    private String lecturerIntroduction;
    private String centerName;
    private String lectureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String location;
    private int lectureCount;
    private double price;
    private String lectureDescription;
    private String categoryName;
    //사용자에게 보여지는 정보
}
