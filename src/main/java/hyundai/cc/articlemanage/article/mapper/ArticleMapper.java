package hyundai.cc.articlemanage.article.mapper;

import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.domain.ArticleCriteria;
import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PostCriteria;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    public List<ArticleDTO> getAllArticleList() throws SQLException;
    public List<ArticleDTO> getArticleListByLectureWithPagination(ArticleCriteria articleCriteria) throws SQLException;
    public List<ArticleDTO> getArticleListByLectureWithPaginationFirst(ArticleCriteria articleCriteria) throws SQLException;
    public List<ArticleDTO> getArticleListById(PostCriteria postCriteria) throws SQLException;
    public List<ArticleDTO> getArticleListByIdFirst(PostCriteria postCriteria) throws SQLException;
    public ArticleDTO getArticleDetail(long articleId) throws SQLException;
    public int getTotal(long lectureId) throws SQLException;
    public int getTotalbyId(String userId) throws SQLException;
    public void updateHits(long articleId) throws SQLException;
    public void createArticle(ArticleCreateRequestDTO articleCreateRequestDTO) throws SQLException;
    public void updateArticle(ArticleCreateRequestDTO articleCreateRequestDTO) throws SQLException;
    public void deleteArticle(@Param("articleId") long articleId, @Param("writerId") String writerId) throws SQLException;
}
