package hyundai.cc.articlemanage.reply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hyundai.cc.usermanage.user.dto.UserDTO;
import hyundai.cc.usermanage.user.dto.UserResponseDTO;
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
public class ReplyDTO {
    private Long id;
    private Long articleId;
    private Long parentId;
    private String reply;
    private String replyerId;
//    private String replyerNickname;
    private UserResponseDTO userResponseDTO;
    private Long totalRecomments;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime replyDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatereplydate;

    //DB 접근
}
