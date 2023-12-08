package hyundai.cc.articlemanage.article.controller;

import hyundai.cc.articlemanage.article.dto.ArticleCreateRequestDTO;
import hyundai.cc.articlemanage.article.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleCreateRequestDTO articleCreateRequestDTO){
        return new ResponseEntity<>(dtoMapper.toArticleResponseDTO(service.createArticle(articleCreateRequestDTO)),
                HttpStatus.CREATED);
    }


}
