package Web_Layer.Controllers;

import Business_Layer.Dto.UserDto;
import Business_Layer.Services.IUserService;
import Utils.Constants;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserMvcController {

    @Autowired
    private final IUserService userService;

    public UserMvcController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() { return new ModelAndView("src/java/resources/Templates/LoginView.html"); }

    @GetMapping("/")
    public String getHomePage() {
        return Constants.REDIRECT + "/";
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(Principal userr) {
        UserDto user = userService.getUserByUsername(userr.getNickname());
        return new ModelAndView("/profile")
                .addObject("user",user);
    }
}
