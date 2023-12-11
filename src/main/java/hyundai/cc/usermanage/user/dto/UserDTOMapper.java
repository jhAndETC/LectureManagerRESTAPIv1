package hyundai.cc.usermanage.user.dto;

import com.github.f4b6a3.ulid.UlidCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserDTOMapper {
    public UserCreateDTO toUserCreateDTO(UserCreateRequestDTO userCreateRequestDTO) {
        String id= UlidCreator.getUlid().toRfc4122().toUuid().toString();
        String password= new BCryptPasswordEncoder().encode(userCreateRequestDTO.getPassword());
        String email=userCreateRequestDTO.getEmail();
        String username=userCreateRequestDTO.getUsername();
        String nickname=userCreateRequestDTO.getNickname();
        String content=userCreateRequestDTO.getIntroduction();
        content=(content != null && content.length() > 0) ? content : " ";

        boolean enable=true;


        return new UserCreateDTO(
                id,
                email,
                password,
                username,
                nickname,
                content,
                enable
        );
    }


    public UserResponseDTO toUserResponseDTO(UserDTO userDTO){
        String id=userDTO.getId();
        String email=userDTO.getEmail();
        String username=userDTO.getUsername();
        String nickname=userDTO.getNickname();
        LocalDateTime createDate=userDTO.getCreateDate();
        String content=userDTO.getContent();

        return new UserResponseDTO(
                id,
                email,
                username,
                nickname,
                createDate,
                content
        );

    }

    public UserCreateDTO FromUpdatetoUserCreateDTO(UserDTO userDTO,UserUpdateRequestDTO userUpdateRequestDTO){
        String id=userDTO.getId();
        String email=userDTO.getEmail();
        String password= userDTO.getPassword();
        String username= userUpdateRequestDTO.getUsername();
        String nickname=userUpdateRequestDTO.getNickname();
        String content=userUpdateRequestDTO.getIntroduction();
        content=(content != null && content.length() > 0) ? content : " ";
        boolean enable=userDTO.isEnable();

        return new UserCreateDTO(
                id,
                email,
                password,
                username,
                nickname,
                content,
                enable);

    }



}
