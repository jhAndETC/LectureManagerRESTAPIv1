package hyundai.cc.articlemanage.article.controller;

import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.articlemanage.article.dto.ArticleDTOMapper;
import hyundai.cc.articlemanage.article.service.ArticleService;
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

    // 확인용: 모든 article 가져오기
    @GetMapping("/all")
    public ResponseEntity<List<ArticleDTO>> getAllArticle() throws Exception {
        try{
            List<ArticleDTO> articleList = articleService.getAllArticleList();
            log.info(articleList.toString());
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
