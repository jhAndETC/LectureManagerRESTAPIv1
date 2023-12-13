package hyundai.cc.articlemanage.article.controller;

import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTOMapper;
import hyundai.cc.articlemanage.article.service.ArticleService;
import hyundai.cc.domain.ArticleCriteria;
import hyundai.cc.filemanage.file.controller.FileController;
import hyundai.cc.usermanage.user.dto.UserDTO;
import hyundai.cc.usermanage.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Log
@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userservice;

    public ArticleController(ArticleService articleService,
                             UserService userservice) {
        this.articleService = articleService;
        this.userservice = userservice;
    }

     // (생성) 게시글 작성
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createArticle(Principal principal, @RequestBody ArticleCreateRequestDTO articleCreateRequestDTO){
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            articleCreateRequestDTO.setWriterId(userId);
            // article db 업로드
            articleService.createArticle(articleCreateRequestDTO);
            log.info("생성된 article Id: " + articleCreateRequestDTO.getArticleId());
            // 파일 첨부 시 파일 업로드
            return new ResponseEntity<>(articleCreateRequestDTO, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("insert Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/link")
    public ResponseEntity<String> checkLink() throws Exception {
        try{
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (Exception e)
        {return new ResponseEntity<>("bad", HttpStatus.OK); }
    }

    // (조회) 확인용: 모든 article 가져오기
    @GetMapping(value = "/all",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(value = "/{articleId}",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
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

    // 수정
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateArticle(Principal principal, @RequestBody ArticleCreateRequestDTO articleCreateRequestDTO){
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            articleCreateRequestDTO.setWriterId(userId);
            // article db 업로드
            articleService.updateArticle(articleCreateRequestDTO);
            // 파일 첨부 시 파일 업로드
            return new ResponseEntity<>(articleService.getArticleDetail(articleCreateRequestDTO.getArticleId()), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("update Error", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteArticle(@RequestParam long articleId, Principal principal){
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            // article db 삭제
            articleService.deleteArticle(articleId, userId);
            return new ResponseEntity<>("delete article" + articleId, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("delete Error", HttpStatus.BAD_REQUEST);
        }
    }



}
