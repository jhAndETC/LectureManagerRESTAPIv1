package hyundai.cc.lecturemanage.lecture.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class LectureCreateDTO {
    private String title;
    private String lecturerId;
    // user id가 아님
    private String lectureTime;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private int lectureCount;
    private double price;
    private String description;
    private Long categoryId;

}
