package hyundai.cc.articlemanage.article.dto;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class ArticleDTOMapper {
    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

    public ArticleCreateDTO toArticleCreateDTO(ArticleCreateRequestDTO articleCreateRequestDTO){
        String title = articleCreateRequestDTO.getTitle();
        String content = articleCreateRequestDTO.getContent();
        Date updateDate = currentTimestamp;
        long views = 0;
        long is_notice = 0;
        long fileId = 0;
        String writerId = "018c52de-fe0d-4e23-ab88-723725ba5268";
        long lectureId = 2;

        return new ArticleCreateDTO(
                title,
                content,
                updateDate,
                views,
                is_notice,
                fileId,
                writerId,
                lectureId
        );
    }
}
