package hyundai.cc.articlemanage.article.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
public class ArticleCreateRequestDTO implements Serializable {
    @NotEmpty(message = "Title should not be empty")
    @Size(max = 100, message = "Title must be at most 200 characters")
    private String title;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private Date regDate;
    private Date updateDate;
    private long views;
    private long is_notice;
    private MultipartFile[] uploadFile;
    private String writerId;
    private long lectureId;
    // 생성 요청 DTO (Controller)
}
