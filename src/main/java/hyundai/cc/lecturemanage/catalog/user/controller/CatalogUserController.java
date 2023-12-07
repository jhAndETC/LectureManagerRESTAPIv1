//package hyundai.cc.lecturemanage.catalog.user.controller;
//
//import hyundai.cc.lecturemanage.catalog.user.dto.lecture.LectureCreateDTO;
//import hyundai.cc.lecturemanage.catalog.user.dto.lecture.LectureDTO;
//import hyundai.cc.lecturemanage.catalog.user.service.MockCatalogUserService;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.HashMap;
//import java.util.Map;
//
//
////@RestController
////@RequestMapping("catalog/users")
//public class CatalogUserController {
//    private final MockCatalogUserService service;
//
//    public CatalogUserController(MockCatalogUserService service) {
//        this.service = service;
//    }
////
////    @GetMapping("/hello")
////    public ResponseEntity<LectureDTO> getLecture() {
////        LectureDTO lecture = service.getLecture();
////        return ResponseEntity.ok(lecture); // Return the lecture object with an OK status
////    }
//
//    @PostMapping("/hello")
//    public ResponseEntity<LectureDTO> createLecture(
//        @Valid @RequestBody LectureCreateDTO lectureCreateDTO
//    ) {
//        System.out.println(lectureCreateDTO.getTitle());
//        LectureDTO lecture = service.createLecture(lectureCreateDTO);
//        return ResponseEntity.ok(lecture); // Return the lecture object with an OK status
//    }
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(
//            MethodArgumentNotValidException ex
//    ) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//}