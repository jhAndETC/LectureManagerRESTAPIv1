package hyundai.cc.lecturemanage.catalog.user.dto.lecture;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class LectureCreateDTO {
    @NotNull(message = "Title must not be null")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Price must not be null")
    private Integer price;
}
