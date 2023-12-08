package hyundai.cc.usermanage.user.dto;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import hyundai.cc.lecturemanage.lecture.dto.LectureDTO;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DTOMapper {
    public UserCreateDTO toUserCreateDTO(UserCreateRequestDTO userCreateRequestDTO) {
        String id= UlidCreator.getMonotonicUlid().toString();
        String password= new BCryptPasswordEncoder().encode(userCreateRequestDTO.getPassword());
        String email=userCreateRequestDTO.getEmail();
        String username=userCreateRequestDTO.getUsername();
        String nickname=userCreateRequestDTO.getNickname();
        boolean enable=true;


        return new UserCreateDTO(
                id,
                email,
                password,
                username,
                nickname,
                enable
        );
    }

    public UserResponseDTO toUserResponseDTO(UserDTO userDTO){
        String id=userDTO.getId();
        String email=userDTO.getEmail();
        String username=userDTO.getUsername();
        String nickname=userDTO.getNickname();
        LocalDateTime createDate=userDTO.getCreateDate();

        return new UserResponseDTO(
                id,
                email,
                username,
                nickname,
                createDate
        );

    }



}
