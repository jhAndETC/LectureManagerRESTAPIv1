package hyundai.cc.articlemanage.article.dto;

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

    private long articleId;
    private String title;
    private String content;
    private Date regdate;
    private Date updateDate;
    private long views;
    private boolean is_notice;
    private long fileId;
    private String writerId;
    private long lectureId;

}
