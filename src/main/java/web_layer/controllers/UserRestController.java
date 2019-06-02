package web_layer.controllers;

import business_layer.dto.PortfolioDto;
import business_layer.dto.UserDto;
import business_layer.services.IPortfolioService;
import business_layer.services.IUserService;
import business_layer.services.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/app")
public class UserRestController {

    @Autowired
    private final IUserService userService;

    @Autowired
    private final IPortfolioService portfolioService;

    public UserRestController(IUserService userService, IPortfolioService portfolioService) {
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @PutMapping("/password")
    public ResponseEntity<?> putPassword(@RequestParam String password, @RequestParam String newPassword, Principal userr) {
        userService.updatePassword(userr.getName(), password, newPassword);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/register")
    public ResponseEntity<?> putUser(@RequestParam UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/addPortfolio")
    public ResponseEntity<?> putPortfolio(@RequestBody PortfolioDto portfolioDto, Principal userr) {
        portfolioService.addPortfolio(userr.getName(), portfolioDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deletePortfolio(@RequestBody PortfolioDto portfolioDto) {
        portfolioService.deletePortfolio(portfolioDto);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/updatePortfolio")
    public ResponseEntity<?> updatePortfolio(@RequestBody PortfolioDto portfolioDto) {
        portfolioService.updatePortfolio(portfolioDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{portfolioName}")
    public PortfolioDto getPortfolio(@PathVariable String portfolioName) {
        return portfolioService.getPortfolio(portfolioName);
    }
}
