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

    //private final LectureService lectureService;
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
    @GetMapping("/pages")
    public ResponseEntity<?> getUsersByPage(Criteria cri) {
        int total = userservice.getTotal(cri);
        HashMap<PageDTO, List<UserResponseDTO>> map=new HashMap<>();
        map.put(new PageDTO(cri, total),userservice.getUsersByPage(cri).stream()
                        .map(userdtoMapper::toUserResponseDTO)
                        .collect(Collectors.toList()));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getUserList() {
        return new ResponseEntity<>(userservice.getUserList().stream()
                .map(userdtoMapper::toUserResponseDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable String userId) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.getUserDetail(userId)));
    }

//    @GetMapping("/{userId}/lectures/progress")
//    public ResponseEntity<?> getUserLecturesProgress(@PathVariable String userId){
//        return ResponseEntity.ok(lecturedtoMapper.toLectureResponseDTO(lectureService.getUserLecturesProgress(userId)));
//    }
//
//    @GetMapping("/{userId}/lectures/finish")
//    public ResponseEntity<?> getUserLecturesFinish(@PathVariable String userId){
//        return ResponseEntity.ok(lecturedtoMapper.toLectureResponseDTO(lectureService.getUserLecturesFinish(userId)));
//    }
//    @GetMapping("/{userId}/lectures/keep")
//    @GetMapping("/{userId}/community/posts")



    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @Valid @RequestBody UserCreateRequestDTO updateDTO) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.updateUser(userId,updateDTO)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.deleteUser(userId)));
    }



}
