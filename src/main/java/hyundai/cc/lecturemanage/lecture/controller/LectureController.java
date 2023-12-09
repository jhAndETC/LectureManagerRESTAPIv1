package hyundai.cc.lecturemanage.lecture.controller;


import hyundai.cc.lecturemanage.lecture.dto.*;
import hyundai.cc.lecturemanage.lecture.service.LectureService;
import hyundai.cc.lecturemanage.lecture.service.MockLectureServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("lectures")
public class LectureController {
    private final LectureService service;
    private final LectureDTOMapper dtoMapper;
    public LectureController(MockLectureServiceImpl service, LectureDTOMapper dtoMapper) {
        this.service = service;
        this.dtoMapper = dtoMapper;
    }
    @PostMapping
    public ResponseEntity<?> createLecture(String userId, @Valid @RequestBody LectureCreateDTO lec) {
        //LectureResponseDTO lecture = dtoMapper.toLectureResponsDTO(service.createLecture(lec));
        return new ResponseEntity<>(lec,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ArrayList<LectureDTO>> getLectureList() {
        ArrayList<LectureDTO> lectureList = service.getLectureList();
        return ResponseEntity.ok(lectureList);
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureDTO> getLectureDetail(@PathVariable Long lectureId) {
        LectureDTO lectureDetail = service.getLectureDetail(lectureId);
        return ResponseEntity.ok(lectureDetail);
    }
    @PutMapping("/{lectureId}")
    public ResponseEntity<?> updateLecture(@PathVariable Long lectureId, @RequestBody LectureCreateDTO updateDTO) {
        LectureDTO updatelec=service.updateLecture(lectureId, updateDTO);
        return ResponseEntity.ok(updatelec);
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<?> deleteLecture(@PathVariable Long lectureId) {
        String msg=service.deleteLecture(lectureId);
        return ResponseEntity.ok(msg);
    }








}
