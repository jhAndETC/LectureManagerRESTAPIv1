package hyundai.cc.articlemanage.article.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ArticleCreateRequestDTO implements Serializable {
    private String title;
    private String content;
}
