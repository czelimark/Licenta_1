package Business_Layer.Services.Implementations;

import Data_Layer.Domain.User;
import Data_Layer.Repositories.IUserRepository;
import Utils.ExceptionMessages;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthenticationService implements AuthenticationManager, UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        Optional<User> user = userRepo.findByUsername(username);
        if (!user.isPresent() || !BCrypt.checkpw(password, user.get().getPassword())) {
            throw new BadCredentialsException(ExceptionMessages.INVALID_USERNAME_OR_PASSWORD);
        }
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.INVALID_USERNAME));
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        builder.password(user.getPassword());
        return builder.build();
    }
}
