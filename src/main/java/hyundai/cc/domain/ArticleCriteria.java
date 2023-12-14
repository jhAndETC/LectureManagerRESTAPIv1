package hyundai.cc.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleCriteria {
    private Long Id;
    private Integer cursor;
    private Integer amount;

    @Builder
    public ArticleCriteria(Long Id, Integer cursor, Integer amount){
        this.Id = Id;
        this.cursor = cursor;
        this.amount = amount;
    }
}
