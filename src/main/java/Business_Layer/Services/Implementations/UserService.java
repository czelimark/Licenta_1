package Business_Layer.Services.Implementations;

import Business_Layer.Dto.UserDto;
import Business_Layer.Mappers.UserMapper;
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
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        return UserMapper.toDto(user);
    }

    @Override
    public void updatePassword(String username, String password, String newPassword) {
        User user = userRepo.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new AccessForbiddenException();
        }
        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        userRepo.flush();
    }
}
