package business_layer.services.impl;

import data_layer.domain.User;
import data_layer.repositories.IUserRepository;
import org.springframework.stereotype.Service;
import utils.ExceptionMessages;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationManager, UserDetailsService {

    @Autowired
    private IUserRepository userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent() || !BCrypt.checkpw(password, user.get().getPassword())) {
            throw new BadCredentialsException(ExceptionMessages.INVALID_USERNAME_OR_PASSWORD);
        }
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.INVALID_USERNAME));
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        builder.password(user.getPassword());
        return builder.build();
    }
}
