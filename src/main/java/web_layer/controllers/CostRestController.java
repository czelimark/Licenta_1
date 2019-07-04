package web_layer.controllers;

import business_layer.dto.CostDto;
import business_layer.dto.ResourceDto;
import business_layer.services.ICostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class CostRestController {

    @Autowired
    private final ICostService costService;

    public CostRestController(ICostService costService) {
        this.costService = costService;
    }

    @PutMapping("/addCost/{idP}/{idR}/{name}")
    public ResponseEntity<?> putCost(@RequestBody CostDto costDto, @PathVariable String idP, @PathVariable String idR, @PathVariable String name) {
        costService.addCost(costDto, Integer.valueOf(idP), Integer.valueOf(idR), name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deleteCost/{id}")
    public ResponseEntity<?> deleteCost(@PathVariable String id) {
        costService.deleteCost(Integer.parseInt(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/updateCost/{idP}/{idR}/{name}")
    public ResponseEntity<?> updateCost(@RequestBody CostDto costDto, @PathVariable String idP, @PathVariable String idR, @PathVariable String name) {
        costService.updateCost(costDto, Integer.valueOf(idP), Integer.valueOf(idR), name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/cost/{id}")
    public ResponseEntity<?> getCost(@PathVariable String id) {
        CostDto cost = costService.getCost(Integer.valueOf(id));
        return ResponseEntity.ok(cost);
    }

    @GetMapping("/costs/{portfolioId}")
    public ResponseEntity<?> getCosts(@PathVariable String portfolioId) {
        List<CostDto> costs = costService.getCosts(Integer.valueOf(portfolioId));
        return ResponseEntity.ok(costs);
    }

    @GetMapping("/resources")
    public ResponseEntity<?> getResources() {
        List<ResourceDto> resources = costService.getResources();
        return ResponseEntity.ok(resources);
    }
}
