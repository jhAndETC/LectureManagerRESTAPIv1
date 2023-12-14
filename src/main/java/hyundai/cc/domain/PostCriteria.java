package hyundai.cc.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class PostCriteria {
    private String userId;
    private Integer cursor;
    private Integer amount;

    @Builder
    public PostCriteria(String userId, Integer cursor, Integer amount){
        this.userId = userId;
        this.cursor = cursor;
        this.amount = amount;
    }
}
