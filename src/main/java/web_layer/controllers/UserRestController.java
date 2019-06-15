package web_layer.controllers;

import business_layer.dto.PortfolioDto;
import business_layer.dto.UserDto;
import business_layer.services.IPortfolioService;
import business_layer.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/app")
public class UserRestController {

    @Autowired
    private final IUserService userService;

    public UserRestController(IUserService userService, IPortfolioService portfolioService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(Principal user) {
        UserDto userr = userService.getUserByUsername(user.getName());
        return ResponseEntity.ok(userr);
    }

    @PutMapping("/password")
    public ResponseEntity<?> putPassword(@RequestParam String password, @RequestParam String newPassword, Principal userr) {
        userService.updatePassword(userr.getName(), password, newPassword);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/register")
    public ResponseEntity<?> putUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
