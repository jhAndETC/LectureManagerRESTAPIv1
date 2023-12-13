package hyundai.cc.usermanage.user.controller;


import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;

import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
import hyundai.cc.lecturemanage.lecture.service.LectureService;
import hyundai.cc.usermanage.user.dto.*;
import hyundai.cc.usermanage.user.service.MockUserServiceImpl;
import hyundai.cc.usermanage.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Log
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userservice;
    private final UserDTOMapper userdtoMapper;
    private final LectureDTOMapper lecturedtoMapper;
    @Autowired
    public UserController(MockUserServiceImpl userservice, UserDTOMapper dtoMapper, LectureDTOMapper lecturedtoMapper) {
        this.userservice = userservice;
        this.userdtoMapper=dtoMapper;
        this.lecturedtoMapper = lecturedtoMapper;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestDTO cuser) {
        return new ResponseEntity<>(userdtoMapper.toUserResponseDTO(userservice.createUser(cuser)),
                HttpStatus.CREATED);
    }

    //admin 권한
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUsersByPage(Criteria cri) {
        int total = userservice.getTotal(cri);
        PageDTO page=new PageDTO(cri, total);
        HashMap<String,Object> map=new HashMap<>();
        map.put("next",page.isNext());
        map.put("prev",page.isPrev());
        map.put("totalPages",page.getEndPage());
        map.put("currentPage",cri.getPageNum());
        map.put("itemsPerPage",cri.getAmount());
        map.put("totalItems",total);
        map.put("data",userservice.getUsersByPage(cri).stream()
                        .map(userdtoMapper::toUserResponseDTO)
                        .collect(Collectors.toList()));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


//    @GetMapping
//    public ResponseEntity<?> getUserList() {
//        return new ResponseEntity<>(userservice.getUserList().stream()
//                .map(userdtoMapper::toUserResponseDTO)
//                .collect(Collectors.toList()),
//                HttpStatus.OK);
//    }


    @GetMapping(value = "/account",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserResponseDTO> getUserDetail(Principal principal) {
        String currentEmail = principal.getName();
        String userId = userservice.getUuidByEmail(currentEmail);
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.getUserDetail(userId)));
    }


    @GetMapping(value = "/account/lectures/progress,",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findProgressCourses(Principal principal,Criteria cri){
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
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


    @GetMapping(value = "/account/lectures/finish",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> findFinishCourses(Principal principal,Criteria cri){
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
        //map.put("startpage",page.getStartPage());
        map.put("totalPages",page.getEndPage());
        map.put("currentPage",cri.getPageNum());
        map.put("itemsPerPage",cri.getAmount());
        map.put("totalItems",total);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @GetMapping(value = "/account/lectures/keep",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> findLikedCourses(Principal principal,Criteria cri){
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
    }


//    @GetMapping("/{userId}/community/posts")
//    public ResponseEntity<?> getUserCommunityList(@PathVariable String userId){
//        return ResponseEntity.ok(lecturedtoMapper.toLectureListResponseDTO(lectureService.getUserLecturesKeep(userId)));
//    }



    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateRequestDTO updateDTO) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.updateUser(userId,updateDTO)));
    }

    @DeleteMapping(value = "/{userId}",
                   produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        try {
            userservice.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
