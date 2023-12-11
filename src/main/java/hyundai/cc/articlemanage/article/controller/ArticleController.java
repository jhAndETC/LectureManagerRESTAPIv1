package hyundai.cc.articlemanage.article.controller;

import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTOMapper;
import hyundai.cc.articlemanage.article.service.ArticleService;
import hyundai.cc.domain.ArticleCriteria;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log
@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleDTOMapper articleDTOMapper;

    public ArticleController(ArticleService articleService, ArticleDTOMapper articleDTOMapper) {
        this.articleService = articleService;
        this.articleDTOMapper = articleDTOMapper;
    }

     // (생성) 게시글 작성
//    @PostMapping
//    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleCreateRequestDTO articleCreateRequestDTO){
//        return new ResponseEntity<>(articleDTOMapper.toArticleResponseDTO(articleService.createArticle(articleCreateRequestDTO)),
//                HttpStatus.CREATED);
//    }

    @GetMapping("/link")
    public ResponseEntity<String> checkLink() throws Exception {
        try{
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (Exception e)
        {return new ResponseEntity<>("bad", HttpStatus.OK); }
    }

    // (조회) 확인용: 모든 article 가져오기
    @GetMapping("/all")
    public ResponseEntity<?> getAllArticle() throws Exception {
        try{
            List<ArticleDTO> articleList = articleService.getAllArticleList();
            log.info(articleList.toString());
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ResponseEntity<>("ALl Article List ERROR", HttpStatus.NOT_FOUND);
        }
    }

    // (조회) lecture 별 article 가져오기 -> pagination 전
    @GetMapping("/lectures/{lectureId}")
    public ResponseEntity<?> getArticleListByLecture(@PathVariable long lectureId,
                                                     @RequestParam(defaultValue="0") int offset,
                                                     @RequestParam(defaultValue="20") int limit){
        try{
            ArticleCriteria articleCriteria = new ArticleCriteria(lectureId, offset, limit);
            log.info("articleController: " + articleCriteria.toString());
            List<ArticleDTO> articleListByLecture = articleService.getArticleListByLectureWithPagination(articleCriteria);
            log.info(articleListByLecture.toString());
            return new ResponseEntity<>(articleListByLecture, HttpStatus.OK);
        } catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>("can't find article by lecture" + lectureId, HttpStatus.NOT_FOUND);
        }

    }

    // (조회) article detail 가져오기
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleDetail(@PathVariable long articleId){
        try{
            ArticleDTO articleDetail = articleService.getArticleDetail(articleId);
            log.info(articleDetail.toString());
            return new ResponseEntity<>(articleDetail, HttpStatus.OK);
        } catch (Exception e){
            log.warning(e.getMessage());
            return new ResponseEntity<>("can't find article detail" + articleId, HttpStatus.NOT_FOUND);
        }

    }




}
