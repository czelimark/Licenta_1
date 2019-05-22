package Business_Layer.Mappers;

import Business_Layer.Dto.UserDto;
import Data_Layer.Domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .profilePhoto(dto.getProfilePhoto())
                .portfolios(PortfolioMapper.toEntityList(dto.getPortfolios()))
                .wallets(WalletMapper.toEntityList(dto.getWallets()))
                .build();
    }

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .phoneNumber(entity.getPhoneNumber())
                .profilePhoto(entity.getProfilePhoto())
                .portfolios(PortfolioMapper.toDtoList(entity.getPortfolios()))
                .wallets(WalletMapper.toDtoList(entity.getWallets()))
                .build();
    }
}
