package business_layer.mappers;

import business_layer.dto.CurrencyDto;
import data_layer.domain.Currency;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CurrencyMapper {

    public static Currency toEntity(CurrencyDto dto) {
        return Currency.builder()
                .id(dto.getId())
                .currencyName(dto.getCurrencyName())
                .build();
    }

    public static CurrencyDto toDto(Currency entity) {
        return CurrencyDto.builder()
                .id(entity.getId())
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
