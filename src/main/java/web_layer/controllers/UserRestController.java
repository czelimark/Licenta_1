package web_layer.controllers;

import business_layer.dto.UserDto;
import business_layer.services.IPortfolioService;
import business_layer.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.EmailSender;

import java.security.Principal;

@RestController
@RequestMapping("/app")
@ComponentScan(basePackages = "utils")
public class UserRestController {

    @Autowired
    private final IUserService userService;

    @Autowired
    private final EmailSender emailSender;

    @Autowired
    public UserRestController(IUserService userService, EmailSender emailSender) {
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(Principal user) {
        UserDto userr = userService.getUserByUsername(user.getName());
        return ResponseEntity.ok(userr);
    }

    @PutMapping("/password")
    public ResponseEntity<?> putPassword(@RequestParam String password, @RequestParam String newPassword, Principal userr) {
        userService.updatePassword(userr.getName(), password, newPassword);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/register")
    public ResponseEntity<?> putUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> put(@RequestBody String profilePhoto, Principal crtUser) {
        userService.updateUser(profilePhoto, crtUser.getName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/email/{to}")
    public ResponseEntity<?> sendMail(@PathVariable String to, Principal crtUser) {
        UserDto userDto = userService.getUserByUsername(crtUser.getName());
        emailSender.sendEmail(userDto.getFirstName() + ' ' + userDto.getLastName(), to);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
