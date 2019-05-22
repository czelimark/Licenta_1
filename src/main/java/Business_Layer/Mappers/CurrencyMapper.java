package Business_Layer.Mappers;

import Business_Layer.Dto.CurrencyDto;
import Data_Layer.Domain.Currency;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CurrencyMapper {

    public static Currency toEntity(CurrencyDto dto) {
        return Currency.builder()
                .currencyName(dto.getCurrencyName())
                .build();
    }

    public static CurrencyDto toDto(Currency entity) {
        return CurrencyDto.builder()
                .currencyName(entity.getCurrencyName())
                .build();
    }

    public static List<CurrencyDto> toDtoList(List<Currency> entities) {
        return entities.stream()
                .map(CurrencyMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Currency> toEntityList(List<CurrencyDto> dtos) {
        return dtos.stream()
                .map(CurrencyMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
