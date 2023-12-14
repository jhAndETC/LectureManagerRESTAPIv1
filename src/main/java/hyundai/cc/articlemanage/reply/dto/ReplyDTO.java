package hyundai.cc.articlemanage.reply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hyundai.cc.usermanage.user.dto.UserDTO;
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
    private UserDTO userDTO;
    private LocalDateTime replyDate;
    private LocalDateTime updatereplydate;

    //DB 접근
}
