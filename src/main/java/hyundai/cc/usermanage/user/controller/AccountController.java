package hyundai.cc.usermanage.user.controller;

import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTOMapper;
import hyundai.cc.usermanage.user.dto.UserDTOMapper;
import hyundai.cc.usermanage.user.dto.UserResponseDTO;
import hyundai.cc.usermanage.user.service.MockUserServiceImpl;
import hyundai.cc.usermanage.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.stream.Collectors;

@Log
@RestController
@RequestMapping("account")
@PreAuthorize("hasRole('ROLE_USER')")
public class AccountController {
    private final UserService userservice;
    private final UserDTOMapper userdtoMapper;
    private final LectureDTOMapper lecturedtoMapper;

    @Autowired
    public AccountController(MockUserServiceImpl userservice, UserDTOMapper dtoMapper, LectureDTOMapper lecturedtoMapper) {
        this.userservice = userservice;
        this.userdtoMapper=dtoMapper;
        this.lecturedtoMapper = lecturedtoMapper;
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserDetail(Principal principal) {
        String currentEmail = principal.getName();
        String userId = userservice.getUuidByEmail(currentEmail);
        return ResponseEntity.ok(userdtoMapper.toUserResponseDTO(userservice.getUserDetail(userId)));
    }


    @GetMapping("/lectures/progress")
    public ResponseEntity<?> findProgressCourses(Principal principal, Criteria cri){
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
    }


    @GetMapping("/lectures/finish")
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
    @GetMapping("/lectures/keep")
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
}
