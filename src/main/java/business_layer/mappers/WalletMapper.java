package business_layer.mappers;

import business_layer.dto.WalletDto;
import data_layer.domain.Wallet;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class WalletMapper {

    public static Wallet toEntity(WalletDto dto) {
        return Wallet.builder()
                .walletName(dto.getWalletName())
                .walletDescription(dto.getWalletDescription())
                .allocatedMoney(dto.getAllocatedMoney())
                .currency(CurrencyMapper.toEntity(dto.getCurrency()))
                .build();
    }

    public static WalletDto toDto(Wallet entity) {
        return WalletDto.builder()
                .walletName(entity.getWalletName())
                .walletDescription(entity.getWalletDescription())
                .allocatedMoney(entity.getAllocatedMoney())
                .currency(CurrencyMapper.toDto(entity.getCurrency()))
                .build();
    }

    public static List<WalletDto> toDtoList(List<Wallet> entities) {
        return entities.stream()
                .map(WalletMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Wallet> toEntityList(List<WalletDto> dtos) {
        return dtos.stream()
                .map(WalletMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
