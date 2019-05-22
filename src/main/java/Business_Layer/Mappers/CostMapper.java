package Business_Layer.Mappers;

import Business_Layer.Dto.CostDto;
import Data_Layer.Domain.Cost;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CostMapper {

    public static Cost toEntity(CostDto dto) {
        return Cost.builder()
                .id(dto.getId())
                .cost(dto.getCost())
                .resource(ResourceMapper.toEntity(dto.getResource()))
                .build();
    }

    public static CostDto toDto(Cost entity) {
        return CostDto.builder()
                .id(entity.getId())
                .cost(entity.getCost())
                .resource(ResourceMapper.toDto(entity.getResource()))
                .build();
    }

    public static List<CostDto> toDtoList(List<Cost> entities) {
        return entities.stream()
                .map(CostMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Cost> toEntityList(List<CostDto> dtos) {
        return dtos.stream()
                .map(CostMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
