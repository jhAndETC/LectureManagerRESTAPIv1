package hyundai.cc.lecturemanage.lecture.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class LectureCreateDTO {
    private Long lecturerId;
    private Long categoryId;
    private String categoryName;
    @NotNull(message = "Title must not be null")
    @NotEmpty(message = "Title must not be empty")
    private String title;
    private String lectureTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private int centerId;
    private String location;
    private int lectureCount;
    @NotNull(message = "Price must not be null")
    private int price;
    private String description;

}
