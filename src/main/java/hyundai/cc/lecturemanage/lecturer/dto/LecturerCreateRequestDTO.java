package hyundai.cc.lecturemanage.lecturer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerCreateRequestDTO {
    @NotEmpty(message = "강사 이메일을 입력해주세요")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String lecturerEmail;
    private String introduction;

}
