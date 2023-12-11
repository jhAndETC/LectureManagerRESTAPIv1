package hyundai.cc.usermanage.user.controller;


import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;

import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
import hyundai.cc.lecturemanage.lecture.service.LectureService;
import hyundai.cc.usermanage.user.dto.*;
import hyundai.cc.usermanage.user.service.MockUserServiceImpl;
import hyundai.cc.usermanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestDTO cuser) {
        return new ResponseEntity<>(userdtoMapper.toUserResponseDTO(userservice.createUser(cuser)),
                HttpStatus.CREATED);
    }

    //admin 권한
    @GetMapping
    public ResponseEntity<?> getUsersByPage(Criteria cri) {
        int total = userservice.getTotal(cri);
        PageDTO page=new PageDTO(cri, total);
        HashMap<String,Object> map=new HashMap<>();
        map.put("next",page.isNext());
        map.put("prev",page.isPrev());
        //map.put("startpage",page.getStartPage());
        map.put("endpage",page.getEndPage());
        map.put("currentPage",cri.getPageNum());
        map.put("itemsPerPage",cri.getAmount());
        map.put("totalItems",total);
        map.put("userlist",userservice.getUsersByPage(cri).stream()
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


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable String userId) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.getUserDetail(userId)));
    }

    @GetMapping("/{userId}/lectures/progress")
    public ResponseEntity<?> findProgressCourses(@PathVariable String userId){
        return new ResponseEntity<>(userservice.findProgressCourses(userId).stream()
                .map(lecturedtoMapper::toLectureResponseDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping("/{userId}/lectures/finish")
    public ResponseEntity<?> findFinishCourses(@PathVariable String userId){
        return new ResponseEntity<>(userservice.findFinishCourses(userId).stream()
                .map(lecturedtoMapper::toLectureResponseDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }
    @GetMapping("/{userId}/lectures/keep")
    public ResponseEntity<?> findLikedCourses(@PathVariable String userId){
        return new ResponseEntity<>(userservice.findLikedCourses(userId).stream()
                .map(lecturedtoMapper::toLectureResponseDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


//    @GetMapping("/{userId}/community/posts")
//    public ResponseEntity<?> getUserCommunityList(@PathVariable String userId){
//        return ResponseEntity.ok(lecturedtoMapper.toLectureListResponseDTO(lectureService.getUserLecturesKeep(userId)));
//    }



    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateRequestDTO updateDTO) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.updateUser(userId,updateDTO)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.deleteUser(userId)));
    }



}
