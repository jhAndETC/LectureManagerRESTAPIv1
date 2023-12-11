package hyundai.cc.articlemanage.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ArticleCreateDTO {
    private String title;
    private String content;
    private Date updateDate;
    private long views;
    private long is_notice;
    private long fileId;
    private String writerId;
    private long lectureId;
    // DB에 넣기 위함
}
