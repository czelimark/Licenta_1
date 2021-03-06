package business_layer.services.impl;

import business_layer.dto.UserDto;
import business_layer.mappers.PortfolioMapper;
import business_layer.mappers.UserMapper;
import business_layer.mappers.WalletMapper;
import business_layer.services.IUserService;
import data_layer.domain.User;
import data_layer.repositories.IUserRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.AccessForbiddenException;
import utils.ResourceNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional(propagation = Propagation.REQUIRED)
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

    @Override
    public void addUser(UserDto userDto) {
        userDto.setProfilePhoto(null);
        User userr = UserMapper.toEntity(userDto);
        userRepo.save(userr);
        userRepo.flush();
    }

    @Override
    public void updateUser(String profilePhoto, String username) {
        User user = userRepo.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        user.setProfilePhoto(profilePhoto);
        userRepo.flush();
    }
}
