package hyundai.cc.lecturemanage.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class LectureDTO {
    private Long lecturerId;
    private Long categoryId;
    private String categoryName;
    private String title;
    private String lectureTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private int centerId;
    private String location;
    private int lectureCount;
    private int price;
    private String description;
}
