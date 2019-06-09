package business_layer.mappers;

import business_layer.dto.UserDto;
import data_layer.domain.User;
import lombok.experimental.UtilityClass;
import org.mindrot.jbcrypt.BCrypt;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(8)))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .profilePhoto(dto.getProfilePhoto())
                .build();
    }

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .phoneNumber(entity.getPhoneNumber())
                .profilePhoto(entity.getProfilePhoto())
                .build();
    }
}
