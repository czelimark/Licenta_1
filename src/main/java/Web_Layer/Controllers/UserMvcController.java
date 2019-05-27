package Web_Layer.Controllers;

import Business_Layer.Dto.PortfolioDto;
import Business_Layer.Dto.UserDto;
import Business_Layer.Services.IPortfolioService;
import Business_Layer.Services.IUserService;
import Utils.Constants;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserMvcController {

    @Autowired
    private final IUserService userService;

    @Autowired
    private final IPortfolioService portfolioService;

    public UserMvcController(IUserService userService, IPortfolioService portfolioService) {
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() { return new ModelAndView("src/java/resources/Templates/LoginView.html"); }

    @GetMapping("/")
    public String getHomePage() {
        return Constants.REDIRECT + "/";
    }

    @GetMapping("/profile")
    public ModelAndView getProfilePage(Principal userr) {
        UserDto user = userService.getUserByEmail(userr.getNickname());
        return new ModelAndView("/profile")
                .addObject("user",user);
    }

    @GetMapping("/portfolios")
    public ModelAndView getPortfolioPage(Principal user) {
        List<PortfolioDto> portfolios = portfolioService.getPortfolios(user.getNickname());
        return new ModelAndView("/portfolios")
                .addObject("portfolios", portfolios);
    }
}
