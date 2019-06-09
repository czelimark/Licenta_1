package web_layer.controllers;

import business_layer.dto.PortfolioDto;
import business_layer.dto.UserDto;
import business_layer.services.IPortfolioService;
import business_layer.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
        return ResponseEntity.ok("User registered with success");
    }

    @PutMapping("/addPortfolio")
    public ResponseEntity<?> putPortfolio(@RequestBody PortfolioDto portfolioDto, Principal userr) {
        portfolioService.addPortfolio(userr.getName(), portfolioDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/deletePortfolio")
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

    @GetMapping("/portfolios")
    public ResponseEntity<?> getPortfolios(Principal user) {
        List<PortfolioDto> portfolios = portfolioService.getPortfolios(user.getName());
        return ResponseEntity.ok(portfolios);
    }
}
