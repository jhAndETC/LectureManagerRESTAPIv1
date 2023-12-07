package hyundai.cc.usermanage.user.controller;


import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;
import hyundai.cc.usermanage.user.dto.UserCreateDTO;
import hyundai.cc.usermanage.user.dto.UserDTO;
import hyundai.cc.usermanage.user.service.MockUserServiceImpl;
import hyundai.cc.usermanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO cuser) {
        UserDTO user = service.createUser(cuser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/pages")
    public ResponseEntity<?> getUsersByPage(Criteria cri) {
        List<UserDTO> userList = service.getUsersByPage(cri);
        //PageDTO 추가 int total = service.getTotal(cri); new PageDTO(cri, total)
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ArrayList<UserDTO>> getUserList() {
        ArrayList<UserDTO> userList = service.getUserList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/mypage")
    public ResponseEntity<UserDTO> getUserDetail() {
        //아이디는 세션에서 받아오기(예정)
        //String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId="fa6bb20a-4115-4852-b5a1-ba9bf3c5042f";
        UserDTO userDetail = service.getUserDetail(userId);
        return ResponseEntity.ok(userDetail);
    }
    @PutMapping("/mypage/profile")
    public ResponseEntity<?> updateUser(@RequestBody UserCreateDTO updateDTO) {
        //String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId="fa6bb20a-4115-4852-b5a1-ba9bf3c5042f";
        UserDTO updateuser=service.updateUser(userId, updateDTO);
        return ResponseEntity.ok(updateuser);
    }

    @DeleteMapping("/mypage/profile")
    public ResponseEntity<?> deleteUser() {
        //String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId="fa6bb20a-4115-4852-b5a1-ba9bf3c5042f";
        String msg=service.deleteUser(userId);
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
