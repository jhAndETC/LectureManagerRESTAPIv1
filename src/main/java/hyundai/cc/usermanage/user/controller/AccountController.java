package hyundai.cc.usermanage.user.controller;

import hyundai.cc.articlemanage.article.service.ArticleService;
import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;
import hyundai.cc.domain.PostCriteria;
import hyundai.cc.exception.PageNotFoundException;
import hyundai.cc.exception.UserNotFoundException;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
import hyundai.cc.usermanage.user.dto.UserDTOMapper;
import hyundai.cc.usermanage.user.dto.UserResponseDTO;
import hyundai.cc.usermanage.user.service.MockUserServiceImpl;
import hyundai.cc.usermanage.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping(value = "account",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("isAuthenticated()")
public class AccountController {
    private final UserService userservice;
    private final UserDTOMapper userdtoMapper;
    private final LectureDTOMapper lecturedtoMapper;
    private final ArticleService articleService;

    @Autowired
    public AccountController(MockUserServiceImpl userservice, UserDTOMapper dtoMapper, LectureDTOMapper lecturedtoMapper, ArticleService articleService) {
        this.userservice = userservice;
        this.userdtoMapper=dtoMapper;
        this.lecturedtoMapper = lecturedtoMapper;
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserDetail(Principal principal) {
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.getUserDetail(userId)));
        } catch (Exception e){
            throw new UserNotFoundException("존재하지 않는 회원입니다");
        }
    }


    @GetMapping(value="/lectures/progress")
    public ResponseEntity<?> findProgressCourses(Principal principal, Criteria cri) throws PageNotFoundException {
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            int total = userservice.getProgressCount(userId,cri);
            PageDTO page=new PageDTO(cri, total);
            HashMap<String,Object> map=new HashMap<>();
            map.put("next",page.isNext());
            map.put("prev",page.isPrev());
            map.put("totalPages",page.getEndPage());
            map.put("currentPage",cri.getPageNum());
            map.put("itemsPerPage",cri.getAmount());
            map.put("totalItems",total);
            map.put("data",userservice.findProgressCourses(userId,cri).stream()
                    .map(lecturedtoMapper::toLectureResponseDTO)
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e){
            throw new PageNotFoundException("해당 정보를 조회할 수 없습니다");
        }

    }


    @GetMapping("/lectures/finish")
    public ResponseEntity<?> findFinishCourses(Principal principal,Criteria cri) throws PageNotFoundException {
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            int total = userservice.getFinishCount(userId,cri);
            PageDTO page=new PageDTO(cri, total);
            HashMap<String,Object> map=new HashMap<>();
            map.put("data",userservice.findFinishCourses(userId,cri).stream()
                    .map(lecturedtoMapper::toLectureResponseDTO)
                    .collect(Collectors.toList()));
            map.put("next",page.isNext());
            map.put("prev",page.isPrev());
            map.put("totalPages",page.getEndPage());
            map.put("currentPage",cri.getPageNum());
            map.put("itemsPerPage",cri.getAmount());
            map.put("totalItems",total);

            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (Exception e){
            throw new PageNotFoundException("해당 정보를 조회할 수 없습니다");
        }

    }
    @GetMapping("/lectures/keep")
    public ResponseEntity<?> findLikedCourses(Principal principal,Criteria cri) throws PageNotFoundException {
        try{
            String currentEmail = principal.getName();
            String userId = userservice.getUuidByEmail(currentEmail);
            int total = userservice.getLikedCount(userId,cri);
            PageDTO page=new PageDTO(cri, total);
            HashMap<String,Object> map=new HashMap<>();
            map.put("next",page.isNext());
            map.put("prev",page.isPrev());
            map.put("totalPages",page.getEndPage());
            map.put("currentPage",cri.getPageNum());
            map.put("itemsPerPage",cri.getAmount());
            map.put("totalItems",total);
            map.put("data",userservice.findLikedCourses(userId,cri).stream()
                    .map(lecturedtoMapper::toLectureResponseDTO)
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (Exception e){
            throw new PageNotFoundException("해당 정보를 조회할 수 없습니다");
        }
    }

    @GetMapping("/community/posts")
    public ResponseEntity<?> getArticleListById(Principal principal,@RequestParam(required = false) Integer cursor,
                                                @RequestParam(defaultValue="10") Integer amount) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String currentEmail = principal.getName();
        String userId = userservice.getUuidByEmail(currentEmail);
        int total = articleService.getTotalbyId(userId);
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
            PostCriteria postCriteria = new PostCriteria(userId, cursor, amount);
            HashMap<String, Object> articleListById = articleService.getArticleListById(postCriteria);
            map.replace("data", articleListById.get("articleDTOList"));
            map.replace("currentCursor", cursor);
            map.replace("next", articleListById.get("next"));
        } catch (Exception e){
            throw new PageNotFoundException("해당 정보를 조회할 수 없습니다");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
