package business_layer.services;

import business_layer.dto.CurrencyDto;
import business_layer.dto.WalletDto;

import java.util.List;

public interface IWalletService {

    WalletDto getWallet(Integer id);

    List<WalletDto> getWallets(String username);

    void updateWallet(WalletDto walletDto);

    void deleteWallet(Integer id);

    void addWallet(String username, WalletDto walletDto);

    List<CurrencyDto> getCurrencies();
}
