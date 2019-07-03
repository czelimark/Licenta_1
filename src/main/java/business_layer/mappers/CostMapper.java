package business_layer.mappers;

import business_layer.dto.CostDto;
import data_layer.domain.Cost;
import data_layer.domain.Currency;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CostMapper {

    public static Cost toEntity(CostDto dto) {
        return Cost.builder()
                .id(dto.getId())
                .project(ProjectMapper.toEntity(dto.getProject()))
                .resource(ResourceMapper.toEntity(dto.getResource()))
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .cost(dto.getCost())
                .currency(CurrencyMapper.toEntity(dto.getCurrency()))
                .build();
    }

    public static CostDto toDto(Cost entity) {
        return CostDto.builder()
                .id(entity.getId())
                .project(ProjectMapper.toDto(entity.getProject()))
                .resource(ResourceMapper.toDto(entity.getResource()))
                .description(entity.getDescription())
                .quantity(entity.getQuantity())
                .cost(entity.getCost())
                .currency(CurrencyMapper.toDto(entity.getCurrency()))
                .build();
    }

    public static List<CostDto> toDtoList(List<Cost> entities) {
        return entities.stream()
                .map(CostMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }
}
