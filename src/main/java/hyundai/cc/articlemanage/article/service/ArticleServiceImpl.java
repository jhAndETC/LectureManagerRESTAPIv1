package hyundai.cc.articlemanage.article.service;

import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTOMapper;
import hyundai.cc.articlemanage.article.mapper.ArticleMapper;
import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.domain.ArticleCriteria;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public HashMap<String, Object> getArticleListByLectureWithPagination(ArticleCriteria articleCriteria) throws Exception {
        try{
            log.info("articleServiceImpl: " + articleCriteria.toString());
            List<ArticleDTO> articleDTOList;
            if (articleCriteria.getCursor() == null){
                articleDTOList = articleMapper.getArticleListByLectureWithPaginationFirst(articleCriteria);
                log.info("getArticleListByLectureWithPaginationFirst 호출: " + articleDTOList.toString());
            } else {
                articleDTOList = articleMapper.getArticleListByLectureWithPagination(articleCriteria);
                log.info("getArticleListByLectureWithPagination 호출: " + articleDTOList.toString());
            }
            try {
                // List의 마지막 요소를 가져오기
                ArticleDTO lastArticle = articleDTOList.get(articleDTOList.size() - 1);
                // 마지막 Article의 articleId를 가져오기
                long lastArticleId = lastArticle.getId();
                // 출력 또는 다른 용도로 사용
                log.info("가장 마지막 articleId: " + lastArticleId);
                HashMap<String, Object> map = new HashMap<>();
                map.put("articleDTOList", articleDTOList);
                map.put("next", lastArticleId);
                return map;
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public ArticleDTO getArticleDetail(long articleId) throws Exception {
        try{
            return articleMapper.getArticleDetail(articleId);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public int getTotal(long lectureId) throws Exception {
        try{
            return articleMapper.getTotal(lectureId);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateHits(long articleId) throws Exception {
        try{
            articleMapper.updateHits(articleId);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void createArticle(ArticleCreateRequestDTO articleCreateRequestDTO) throws Exception {
        try{
            articleMapper.createArticle(articleCreateRequestDTO);
        } catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @Override
    public void updateArticle(ArticleCreateRequestDTO articleCreateRequestDTO) throws Exception {
        try{
            articleMapper.updateArticle(articleCreateRequestDTO);
        } catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @Override
    public void deleteArticle(long articleId, String writerId) throws Exception {
        try{
            articleMapper.deleteArticle(articleId, writerId);
        } catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
