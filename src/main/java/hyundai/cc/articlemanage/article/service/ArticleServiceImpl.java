package hyundai.cc.articlemanage.article.service;

import hyundai.cc.articlemanage.article.dto.ArticleDTOMapper;
import hyundai.cc.articlemanage.article.mapper.ArticleMapper;
import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleMapper articleMapper;
    private final ArticleDTOMapper dtoMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleDTOMapper dtoMapper) {
        this.articleMapper = articleMapper;
        this.dtoMapper=dtoMapper;
    }

    @Override
    public List<ArticleDTO> getAllArticleList() throws Exception {
        try {
            log.info(articleMapper.getAllArticleList().toString());
            return articleMapper.getAllArticleList();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}
