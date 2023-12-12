package hyundai.cc.articlemanage.article.controller;

import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTOMapper;
import hyundai.cc.articlemanage.article.service.ArticleService;
import hyundai.cc.domain.ArticleCriteria;
import hyundai.cc.filemanage.file.controller.FileController;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Log
@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleDTOMapper articleDTOMapper;
    private final FileController fileController;

    public ArticleController(ArticleService articleService,
                             ArticleDTOMapper articleDTOMapper,
                             FileController fileController) {
        this.articleService = articleService;
        this.articleDTOMapper = articleDTOMapper;
        this.fileController = fileController;
    }

     // (생성) 게시글 작성
//    @PostMapping()
//    public ResponseEntity<?> createArticle(@RequestBody ArticleCreateRequestDTO articleCreateRequestDTO){
//        try{
//            // 파일 첨부 시 파일 업로드
//            if (articleCreateRequestDTO.getUploadFile() != null) {
//                fileController.upload(articleCreateRequestDTO.getUploadFile());
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("insert Error", HttpStatus.BAD_REQUEST);
//        }
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
    @GetMapping()
    public ResponseEntity<?> getArticleListByLecture(@RequestParam long lecId,
                                                     @RequestParam(required = false) Integer cursor,
                                                     @RequestParam(defaultValue="10") Integer amount) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int total = articleService.getTotal(lecId);
        map.put("total", total);
        map.put("data", null);
        if (cursor != null){
            map.put("currentCursor", cursor);
        } else {
            map.put("currentCursor", null);
        }
        map.put("next", null);
        map.put("amount", amount);
        try{
            ArticleCriteria articleCriteria = new ArticleCriteria(lecId, cursor, amount);
            log.info("articleController, articleCriteria: " + articleCriteria.toString());
            HashMap<String, Object> articleListByLecture = articleService.getArticleListByLectureWithPagination(articleCriteria);
            log.info(articleListByLecture.toString());
            map.replace("data", articleListByLecture.get("articleDTOList"));
            map.replace("currentCursor", cursor);
            map.replace("next", articleListByLecture.get("next"));
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    // (조회) article detail 가져오기
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleDetail(@PathVariable long articleId){
        try{
            articleService.updateHits(articleId);
            ArticleDTO articleDetail = articleService.getArticleDetail(articleId);
            log.info(articleDetail.toString());
            return new ResponseEntity<>(articleDetail, HttpStatus.OK);
        } catch (Exception e){
            log.warning(e.getMessage());
            return new ResponseEntity<>("can't find article detail" + articleId, HttpStatus.NOT_FOUND);
        }

    }

}
