package hyundai.cc.articlemanage.article.service;

import hyundai.cc.articlemanage.article.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    public List<ArticleDTO> getAllArticleList() throws Exception;
}
