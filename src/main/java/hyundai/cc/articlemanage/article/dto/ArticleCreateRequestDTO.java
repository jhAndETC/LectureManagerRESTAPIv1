package hyundai.cc.articlemanage.article.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Getter
public class ArticleCreateRequestDTO implements Serializable {
    @NotEmpty(message = "Title should not be empty")
    @Size(max = 100, message = "Title must be at most 200 characters")
    private String title;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    // 생성 요청 DTO (Controller)
}
