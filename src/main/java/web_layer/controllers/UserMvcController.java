package web_layer.controllers;

import business_layer.dto.PortfolioDto;
import business_layer.dto.UserDto;
import business_layer.services.IPortfolioService;
import business_layer.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
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

    @GetMapping("/profile")
    public ModelAndView getProfilePage(Principal userr) {
        UserDto user = userService.getUserByUsername(userr.getName());
        return new ModelAndView("/profile")
                .addObject("user",user);
    }
}
