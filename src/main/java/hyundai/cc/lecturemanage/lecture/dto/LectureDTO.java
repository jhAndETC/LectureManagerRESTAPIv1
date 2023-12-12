package hyundai.cc.lecturemanage.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    //가져올때 쓴다
    private Long lectureId;
    private String title;
    private String lecturerId;
    private String lecturerName;
//    private String lecturerIntroduction;
    private String centerName;
    private String lectureTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private int lectureCount;
    private double price;
    private String description;
    private Long categoryId;
}
