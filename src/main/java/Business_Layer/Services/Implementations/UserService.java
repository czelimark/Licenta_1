package Business_Layer.Services.Implementations;

import Business_Layer.Dto.UserDto;
import Business_Layer.Mappers.PortfolioMapper;
import Business_Layer.Mappers.UserMapper;
import Business_Layer.Mappers.WalletMapper;
import Business_Layer.Services.IUserService;
import Data_Layer.Domain.User;
import Data_Layer.Repositories.IUserRepository;
import Utils.AccessForbiddenException;
import Utils.ResourceNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        return UserMapper.toDto(user);
    }

    @Override
    public void updatePassword(String email, String password, String newPassword) {
        User user = userRepo.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new AccessForbiddenException();
        }
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        userRepo.flush();
    }

    @Override
    public void addUser(UserDto userDto) {
        User userr = UserMapper.toEntity(userDto);
        userr.setEmail(userDto.getEmail());
        userr.setPassword(userDto.getPassword());
        userr.setFirstName(userDto.getFirstName());
        userr.setLastName(userDto.getLastName());
        userr.setBirthDate(userDto.getBirthDate());
        userr.setGender(userDto.getGender());
        userr.setPhoneNumber(userDto.getPhoneNumber());
        userr.setProfilePhoto(userDto.getProfilePhoto());
        userr.setPortfolios(PortfolioMapper.toEntityList(userDto.getPortfolios()));
        userr.setWallets(WalletMapper.toEntityList(userDto.getWallets()));
        userRepo.save(userr);
        userRepo.flush();
    }
}
