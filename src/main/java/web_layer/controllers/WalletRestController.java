package web_layer.controllers;

import business_layer.dto.CurrencyDto;
import business_layer.dto.WalletDto;
import business_layer.services.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/app")
public class WalletRestController {

    @Autowired
    private IWalletService walletService;

    public WalletRestController(IWalletService walletService) {
        this.walletService = walletService;
    }

    @PutMapping("/addWallet")
    public ResponseEntity<?> putWallet(@RequestBody WalletDto walletDto, Principal userr) {
        walletService.addWallet(userr.getName(), walletDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deleteWallet/{id}")
    public ResponseEntity<?> deletePortfolio(@PathVariable Integer id) {
        walletService.deleteWallet(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/updateWallet")
    public ResponseEntity<?> updatePortfolio(@RequestBody WalletDto walletDto) {
        walletService.updateWallet(walletDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/wallet/{id}")
    public ResponseEntity<?> getWallet(@PathVariable String id) {
        WalletDto wallet = walletService.getWallet(Integer.valueOf(id));
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/wallets")
    public ResponseEntity<?> getWallets(Principal user) {
        List<WalletDto> portfolios = walletService.getWallets(user.getName());
        return ResponseEntity.ok(portfolios);
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> getCurrencies() {
        List<CurrencyDto> currencies = walletService.getCurrencies();
        return ResponseEntity.ok(currencies);
    }
}
