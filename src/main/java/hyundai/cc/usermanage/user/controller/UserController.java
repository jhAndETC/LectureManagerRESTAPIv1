package hyundai.cc.usermanage.user.controller;


import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;
import hyundai.cc.usermanage.user.dto.*;
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
    private final DTOMapper dtoMapper;
    @Autowired
    public UserController(MockUserServiceImpl service,DTOMapper dtoMapper) {
        this.service = service;this.dtoMapper=dtoMapper;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestDTO cuser) {
        UserResponseDTO user =dtoMapper
                .toUserResponseDTO(service.createUser(cuser));

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

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable String userId) {
        UserDTO userDetail = service.getUserDetail(userId);
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(userDetail));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserCreateDTO updateDTO) {
        UserDTO user=service.updateUser(userId,updateDTO);
        return ResponseEntity.ok(user);
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
