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
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Lecturer ID is required")
    private String lecturerId;

    @NotBlank(message = "Lecture time is required")
    private String lectureTime;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Min(value = 1, message = "Lecture count should be at least 1")
    private int lectureCount;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

}
