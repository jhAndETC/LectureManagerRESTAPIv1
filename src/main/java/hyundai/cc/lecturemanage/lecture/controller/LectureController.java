//package hyundai.cc.lecturemanage.lecture.controller;
//
//
//import hyundai.cc.domain.Criteria;
//import hyundai.cc.domain.PageDTO;
//import hyundai.cc.lecturemanage.lecture.dto.*;
//import hyundai.cc.lecturemanage.lecture.service.LectureService;
//import hyundai.cc.lecturemanage.lecture.service.MockLectureServiceImpl;
//import hyundai.cc.lecturemanage.lecturer.dto.LecturerDTOMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.HashMap;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequestMapping("lectures")
//public class LectureController {
//    private final LectureService lectureservice;
//    private final LectureDTOMapper lecturedtoMapper;
//    private final LecturerDTOMapper lecturerDTOMapper;
//    public LectureController(MockLectureServiceImpl lectureservice, LectureDTOMapper lecturedtoMapper, LecturerDTOMapper lecturerDTOMapper) {
//        this.lectureservice = lectureservice;
//        this.lecturedtoMapper = lecturedtoMapper;
//        this.lecturerDTOMapper = lecturerDTOMapper;
//    }
//
//
//
//
//    //lecture 강의 목록
//    @GetMapping
//    public ResponseEntity<?> getLecturesByPage(Criteria cri) {
//        int total = lectureservice.getTotal(cri);
//        PageDTO page=new PageDTO(cri, total);
//        HashMap<String,Object> map=new HashMap<>();
//        map.put("next",page.isNext());
//        map.put("prev",page.isPrev());
//        map.put("currentPage",cri.getPageNum());
//        map.put("itemsPerPage",cri.getAmount());
//        map.put("totalItems",total);
//        map.put("lecturelist",lectureservice.getLecturesByPage(cri).stream()
//                .map(lecturedtoMapper::toLectureResponseDTO)
//                .collect(Collectors.toList()));
//        return new ResponseEntity<>(map,HttpStatus.OK);
//    }
//
//
//    //lectures/3 강의 소개
//    @GetMapping("/{lectureId}")
//    public ResponseEntity<?> getLectureDetail(@PathVariable Long lectureId) {
//        return ResponseEntity.ok(lecturedtoMapper.toLectureResponseDTO(lectureservice.getLectureDetail(lectureId)));
//    }
//
//    //lectures/3/lecturer 강사소개
//    @GetMapping("/{lectureId}/lecturer")
//    public ResponseEntity<?> getLectureLecturer(@PathVariable Long lectureId) {
//        return ResponseEntity.ok(lecturerDTOMapper.toLecturerResponseDTO(lectureservice.getLectureLecturer(lectureId)));
//    }
//
//
//    //lectures/3/community 강의 커뮤니티
////    @GetMapping("/{lectureId}/community")
////    public ResponseEntity<?> getLecturePosts(@PathVariable Long lectureId) {
////        return ResponseEntity.ok(lecturedtoMapper.toLectureResponseDTO(lectureservice.getLecturePosts(lectureId)));
////    }
//
//
//    //admin/lectures
//    @PostMapping
//    public ResponseEntity<?> createLecture(String userId, @Valid @RequestBody LectureCreateDTO lec) {
//        //LectureResponseDTO lecture = dtoMapper.toLectureResponsDTO(service.createLecture(lec));
//        return new ResponseEntity<>(lec,HttpStatus.CREATED);
//    }
//    @PutMapping("/{lectureId}")
//    public ResponseEntity<?> updateLecture(@PathVariable Long lectureId, @RequestBody LectureCreateDTO updateDTO) {
//        LectureDTO updatelec=lectureservice.updateLecture(lectureId, updateDTO);
//        return ResponseEntity.ok(updatelec);
//    }
//
//    @DeleteMapping("/{lectureId}")
//    public ResponseEntity<?> deleteLecture(@PathVariable Long lectureId) {
//        String msg=lectureservice.deleteLecture(lectureId);
//        return ResponseEntity.ok(msg);
//    }
//
//    //    @GetMapping
////    public ResponseEntity<ArrayList<LectureDTO>> getLectureList() {
////        ArrayList<LectureDTO> lectureList = lectureservice.getLectureList();
////        return ResponseEntity.ok(lectureList);
////    }
//
//
//
//}
