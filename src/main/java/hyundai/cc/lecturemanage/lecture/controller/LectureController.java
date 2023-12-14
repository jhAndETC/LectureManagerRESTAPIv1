package hyundai.cc.lecturemanage.lecture.controller;


import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.articlemanage.article.service.ArticleService;
import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import hyundai.cc.articlemanage.reply.service.ReplyService;
import hyundai.cc.domain.ArticleCriteria;
import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureCreateRequestDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
import hyundai.cc.lecturemanage.lecture.service.LectureService;
import hyundai.cc.lecturemanage.lecture.service.MockLectureServiceImpl;
import hyundai.cc.lecturemanage.lecturer.dto.LecturerDTOMapper;
import hyundai.cc.usermanage.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("lectures")
public class LectureController {
    private final ArticleService articleService;
    private final LectureService lectureservice;
    private final UserService userservice;
    private final ReplyService replyservice;
    private final LectureDTOMapper lecturedtoMapper;
    private final LecturerDTOMapper lecturerDTOMapper;
    public LectureController(ArticleService articleService, MockLectureServiceImpl lectureservice, UserService userservice, ReplyService replyservice, LectureDTOMapper lecturedtoMapper, LecturerDTOMapper lecturerDTOMapper) {
        this.articleService = articleService;
        this.lectureservice = lectureservice;
        this.userservice = userservice;
        this.replyservice = replyservice;
        this.lecturedtoMapper = lecturedtoMapper;
        this.lecturerDTOMapper = lecturerDTOMapper;
    }




    //lecture 강의 목록
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getLecturesByPage(Criteria cri) {
        int total = lectureservice.getTotal(cri);
        PageDTO page=new PageDTO(cri, total);
        HashMap<String,Object> map=new HashMap<>();
        map.put("next",page.isNext());
        map.put("prev",page.isPrev());
        //map.put("startpage",page.getStartPage());
        map.put("totalPages",page.getEndPage());
        map.put("currentPage",cri.getPageNum());
        map.put("itemsPerPage",cri.getAmount());
        map.put("totalItems",total);
        map.put("data",lectureservice.getLecturesByPage(cri).stream()
                .map(lecturedtoMapper::toLectureResponseDTO)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


    //lectures/3 강의 소개
    @GetMapping(value = "/{lectureId}",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getLectureDetail(@PathVariable Long lectureId) {
        return ResponseEntity.ok(lecturedtoMapper.toLectureResponseDTO(lectureservice.getLectureDetail(lectureId)));
    }

    //lectures/3/lecturer 강사소개
    @GetMapping("/{lectureId}/lecturer")
    public ResponseEntity<?> getLectureLecturer(@PathVariable Long lectureId) {
        return ResponseEntity.ok(lecturerDTOMapper.toLecturerResponseDTO(lectureservice.getLectureLecturer(lectureId)));
    }


    @GetMapping(value = "/{lecId}/community",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getArticleListByLecture(@PathVariable Long lecId,@RequestParam(required = false) Integer cursor,
                                                     @RequestParam(defaultValue="10") Integer amount) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int total = articleService.getTotal(lecId);
        map.put("total", total);
        List<ArticleDTO> replyDTOList = new ArrayList<>();
        map.put("data", replyDTOList);
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

    @GetMapping(value = "/{lecId}/community/{articleId}",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getArticleDetail(@PathVariable long lecId, @PathVariable long articleId){
        try{
            articleService.updateHits(articleId);
            ArticleDTO articleDetail = articleService.getArticleDetail(articleId);
            if(articleDetail.getLectureId()!=lecId){
                return new ResponseEntity<>("해당 강좌의 게시글이 아닙니다.", HttpStatus.NOT_FOUND);
            }
            log.info(articleDetail.toString());
            return new ResponseEntity<>(articleDetail, HttpStatus.OK);
        } catch (Exception e){
            log.warning(e.getMessage());
            return new ResponseEntity<>("can't find article detail" + articleId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{lecId}/community/{articleId}/comments",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getReply(@PathVariable long articleId,
                                      @RequestParam(required = false) Integer cursor,
                                      @RequestParam(defaultValue = "10") Integer amount) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int total = replyservice.getReplyTotal(articleId);
        map.put("total", total);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        map.put("data", replyDTOList);
        if (cursor != null){
            map.put("currentCursor", cursor);
        } else {
            map.put("currentCursor", null);
        }
        map.put("next", null);
        map.put("amount", amount);
        try{
            ArticleCriteria replyCriteria = new ArticleCriteria(articleId, cursor, amount);
            log.info("replyController, replyCritera: " + replyCriteria.toString());
            HashMap<String, Object> replyListByArticle = replyservice.getReplyListByArticleWithPagination(replyCriteria);
            log.info(replyListByArticle.toString());
            map.replace("data", replyListByArticle.get("replyDTOList"));
            map.replace("currentCursor", cursor);
            map.replace("next", replyListByArticle.get("next"));
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{lecId}/community/{articleId}/comments/{parentId}")
    public ResponseEntity<?> getReReply(@PathVariable Long parentId,
                                        @RequestParam(required = false) Integer cursor,
                                        @RequestParam(defaultValue = "10") Integer amount) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int total = replyservice.getReReplyTotal(parentId);
        map.put("total", total);
        List<ReplyDTO> rereplyDTOList = new ArrayList<>();
        map.put("data", rereplyDTOList);
        if (cursor != null){
            map.put("currentCursor", cursor);
        } else {
            map.put("currentCursor", null);
        }
        map.put("next", null);
        map.put("amount", amount);
        try{
            ArticleCriteria replyCriteria = new ArticleCriteria(parentId, cursor, amount);
            log.info("replyController, replyCritera: " + replyCriteria.toString());
            HashMap<String, Object> rereplyListByArticle = replyservice.getReReplyListByArticleWithPagination(replyCriteria);
            log.info(rereplyListByArticle.toString());
            map.replace("data", rereplyListByArticle.get("replyDTOList"));
            map.replace("currentCursor", cursor);
            map.replace("next", rereplyListByArticle.get("next"));
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{lecId}/community/{articleId}/comments/{parentId}")
    public ResponseEntity<?> createReReply(@PathVariable long articleId, Principal principal, @PathVariable long parentId,@RequestBody ReplyCreateDTO content){
        String currentEmail=principal.getName();
        String userId = userservice.getUuidByEmail(currentEmail);
        try{
            replyservice.createReReply(articleId,userId,parentId,content);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{lecId}/community/{articleId}/comments")
    public ResponseEntity<?> createReply(@PathVariable long articleId, Principal principal, @RequestBody ReplyCreateDTO content){
        String currentEmail=principal.getName();
        String userId = userservice.getUuidByEmail(currentEmail);
        try{
            replyservice.createReply(articleId,userId,content);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }



    //admin/lectures
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createLecture(@Valid @RequestBody LectureCreateRequestDTO lec) {
        return new ResponseEntity<>(lecturedtoMapper.toLectureResponseDTO(lectureservice.createLecture(lec)),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{lectureId}",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateLecture(@PathVariable Long lectureId, @RequestBody LectureCreateRequestDTO updateDTO) {
        return new ResponseEntity<>(lecturedtoMapper.toLectureResponseDTO(lectureservice.updateLecture(lectureId, updateDTO)),HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{lectureId}",
                   produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteLecture(@PathVariable Long lectureId) {
        return ResponseEntity.ok(lecturedtoMapper.toLectureResponseDTO(lectureservice.deleteLecture(lectureId)));
    }

//    @GetMapping
//    public ResponseEntity<?> getLectureList() {
//        return new ResponseEntity<>(lectureservice.getLecturesList().stream()
//                .map(lecturedtoMapper::toLectureResponseDTO)
//                .collect(Collectors.toList()),HttpStatus.OK);
//    }



}
