package hyundai.cc.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleCriteria {
    private Long lectureId;
    private Integer cursor;
    private Integer amount;

    @Builder
    public ArticleCriteria(Long lectureId, Integer cursor, Integer amount){
        this.lectureId = lectureId;
        this.cursor = cursor;
        this.amount = amount;
    }
}
