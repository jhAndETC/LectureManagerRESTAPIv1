package hyundai.cc.usermanage.user.controller;


import hyundai.cc.domain.Criteria;
import hyundai.cc.domain.PageDTO;
import hyundai.cc.usermanage.user.dto.*;
import hyundai.cc.usermanage.user.service.MockUserServiceImpl;
import hyundai.cc.usermanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

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
        return new ResponseEntity<>(dtoMapper.toUserResponseDTO(service.createUser(cuser)),
                HttpStatus.CREATED);
    }

    @GetMapping("/pages")
    public ResponseEntity<?> getUsersByPage(Criteria cri) {
        int total = service.getTotal(cri);
        //new PageDTO(cri, total);
        return new ResponseEntity<>(service.getUsersByPage(cri).stream()
                .map(dtoMapper::toUserResponseDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getUserList() {
        return new ResponseEntity<>(service.getUserList().stream()
                .map(dtoMapper::toUserResponseDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable String userId) {
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(service.getUserDetail(userId)));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @Valid @RequestBody UserCreateRequestDTO updateDTO) {
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(service.updateUser(userId,updateDTO)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(service.deleteUser(userId)));
    }

}
