package hyundai.cc.lecturemanage.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Getter
@Setter
@AllArgsConstructor
public class LectureCreateDTO {
    private Long lectureId;
    private String title;
    private String lecturerId;
    private String lectureTime;
    private Long centerId;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private int lectureCount;
    private double price;
    private String description;
    private Long categoryId;

}
