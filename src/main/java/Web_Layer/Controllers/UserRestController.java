package Web_Layer.Controllers;

import Business_Layer.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/app")
public class UserRestController {

    @Autowired
    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/password")
    public ResponseEntity<?> putPassword(@RequestParam String password, @RequestParam String newPassword, Principal userr) {
        userService.updatePassword(userr.getName(), password, newPassword);
        return ResponseEntity.ok(null);
    }
}
