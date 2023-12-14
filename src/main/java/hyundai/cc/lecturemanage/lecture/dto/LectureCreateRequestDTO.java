package hyundai.cc.lecturemanage.lecture.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
public class LectureCreateRequestDTO {
    @NotBlank(message = "강의 제목을 입력해주세요")
    private String title;

    @NotEmpty(message = "강사 이메일을 입력해주세요")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String lecturerEmail;
    @NotNull(message = "지점명을 입력해주세요")
    private String centerName;

    @NotBlank(message = "강의 시간를 입력해주세요")
    private String lectureTime;

    @NotBlank(message = "강의 장소를 입력해주세요")
    private String location;

    @NotNull(message = "강의 시작 날짜를 입력해주세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "강의 종료 날짜를 입력해주세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Min(value = 1, message = "강의 횟수는 1회 이상이어야 합니다")
    private int lectureCount;

    @Min(value = 0, message = "수강료는 0원 이상으로 입력해주세요")
    private double price;

    @NotBlank(message = "강의 소개를 입력해주세요")
    private String lectureDescription;

    @NotNull(message = "카테고리명을 입력해주세요")
    private String categoryName;

}
