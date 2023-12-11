package hyundai.cc.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleCriteria {
    private Long lectureId;
    private int offset;
    private int limit;

    @Builder
    public ArticleCriteria(Long lectureId, int offset, int limit){
        this.lectureId = lectureId;
        this.offset = offset;
        this.limit = limit;
    }
}
