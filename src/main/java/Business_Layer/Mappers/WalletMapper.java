package Business_Layer.Mappers;

import Business_Layer.Dto.WalletDto;
import Data_Layer.Domain.Wallet;
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
                .currencies(CurrencyMapper.toEntityList(dto.getCurrencies()))
                .projects(ProjectMapper.toEntityList(dto.getProjects()))
                .build();
    }

    public static WalletDto toDto(Wallet entity) {
        return WalletDto.builder()
                .walletName(entity.getWalletName())
                .walletDescription(entity.getWalletDescription())
                .allocatedMoney(entity.getAllocatedMoney())
                .currencies(CurrencyMapper.toDtoList(entity.getCurrencies()))
                .projects(ProjectMapper.toDtoList(entity.getProjects()))
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
