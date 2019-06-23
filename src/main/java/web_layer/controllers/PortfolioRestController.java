package web_layer.controllers;

import business_layer.dto.PortfolioDto;
import business_layer.services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/app")
public class PortfolioRestController {

    @Autowired
    private final IPortfolioService portfolioService;

    public PortfolioRestController(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PutMapping("/addPortfolio")
    public ResponseEntity<?> putPortfolio(@RequestBody PortfolioDto portfolioDto, Principal userr) {
        portfolioService.addPortfolio(userr.getName(), portfolioDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deletePortfolio/{id}")
    public ResponseEntity<?> deletePortfolio(@PathVariable String id) {
        portfolioService.deletePortfolio(Integer.parseInt(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/updatePortfolio")
    public ResponseEntity<?> updatePortfolio(@RequestBody PortfolioDto portfolioDto) {
        portfolioService.updatePortfolio(portfolioDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/portfolio/{id}")
    public PortfolioDto getPortfolio(@PathVariable String id) {
        return portfolioService.getPortfolio(Integer.valueOf(id));
    }

    @GetMapping("/portfolios")
    public ResponseEntity<?> getPortfolios(Principal user) {
        List<PortfolioDto> portfolios = portfolioService.getPortfolios(user.getName());
        return ResponseEntity.ok(portfolios);
    }
}
