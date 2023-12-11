package hyundai.cc.articlemanage.article.mapper;

import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.domain.ArticleCriteria;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    public List<ArticleDTO> getAllArticleList() throws SQLException;
    public List<ArticleDTO> getArticleListByLectureWithPagination(ArticleCriteria articleCriteria) throws SQLException;
    public ArticleDTO getArticleDetail(long articleId) throws SQLException;
}
