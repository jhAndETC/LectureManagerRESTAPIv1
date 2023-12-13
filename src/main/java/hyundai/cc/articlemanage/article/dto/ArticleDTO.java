package hyundai.cc.articlemanage.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hyundai.cc.usermanage.user.dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ArticleDTO implements Serializable {
    private long Id;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date regdate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updateDate;
    private long views;
    private long is_notice;
    private String writerId;
    private UserDTO userDTO;
    private long lectureId;
    private String lectureTitle;

}
