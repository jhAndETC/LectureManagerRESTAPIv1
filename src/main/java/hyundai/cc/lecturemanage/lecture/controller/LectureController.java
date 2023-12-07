package hyundai.cc.lecturemanage.lecture.controller;


import hyundai.cc.lecturemanage.lecture.dto.LectureCreateDTO;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import hyundai.cc.lecturemanage.lecture.service.LectureService;
import hyundai.cc.lecturemanage.lecture.service.MockLectureServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("lectures")
public class LectureController {
    private final LectureService service;
    public LectureController(MockLectureServiceImpl service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> createLecture(@Valid @RequestBody LectureCreateDTO lec) {
        LectureDTO lecture = service.createLecture(lec);
        return new ResponseEntity<>(lecture,HttpStatus.CREATED);
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }






}
