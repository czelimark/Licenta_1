package business_layer.services.impl;

import business_layer.dto.CurrencyDto;
import business_layer.dto.WalletDto;
import business_layer.mappers.CurrencyMapper;
import business_layer.mappers.WalletMapper;
import business_layer.services.IWalletService;
import data_layer.domain.Currency;
import data_layer.domain.User;
import data_layer.domain.Wallet;
import data_layer.repositories.ICurrencyRepository;
import data_layer.repositories.IUserRepository;
import data_layer.repositories.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class WalletService implements IWalletService {
    
    @Autowired
    private IWalletRepository walletRepository;
    
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Override
    public List<WalletDto> getWallets(String username) {
        List<Wallet> wallets = walletRepository.findByUser(username);
        return WalletMapper.toDtoList(wallets);
    }

    @Override
    public WalletDto getWallet(Integer id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return WalletMapper.toDto(wallet);
    }

    @Override
    public void updateWallet(WalletDto walletDto) {
        Wallet wallet = walletRepository.findById(walletDto.getId()).orElseThrow(ResourceNotFoundException::new);
        wallet.setWalletName(walletDto.getWalletName());
        wallet.setDescription(walletDto.getDescription());
        wallet.setAllocatedMoney(walletDto.getAllocatedMoney());
        wallet.setCurrency(CurrencyMapper.toEntity(walletDto.getCurrency()));
        walletRepository.flush();
    }

    @Override
    public void deleteWallet(Integer id) {
        walletRepository.deleteWalletById(id);
        walletRepository.flush();
    }

    @Override
    public void addWallet(String username, WalletDto walletDto) {
        Wallet wallet = WalletMapper.toEntity(walletDto);
        User userr = userRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        wallet.setUser(userr);
        walletRepository.save(wallet);
        walletRepository.flush();
    }

    @Override
    public List<CurrencyDto> getCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        return CurrencyMapper.toDtoList(currencies);
    }
}