package hyundai.cc.articlemanage.article.service;

import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.domain.ArticleCriteria;

import java.util.List;

public interface ArticleService {
    public List<ArticleDTO> getAllArticleList() throws Exception;
    public List<ArticleDTO> getArticleListByLectureWithPagination(ArticleCriteria articleCriteria) throws Exception;
    public ArticleDTO getArticleDetail(long articleId) throws Exception;
}
