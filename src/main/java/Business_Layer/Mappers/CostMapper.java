package Business_Layer.Mappers;

import Business_Layer.Dto.CostDto;
import Data_Layer.Domain.Cost;
import Data_Layer.Domain.Project;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CostMapper {

    public static Cost toEntity(CostDto dto) {
        return Cost.builder()
                .costType(dto.getCostType())
                .cost(dto.getCost())
                .project(ProjectMapper.toEntity(dto.getProject()))
                .build();
    }

    public static CostDto toDto(Cost entity) {
        return CostDto.builder()
                .costType(entity.getCostType())
                .cost(entity.getCost())
                .project(ProjectMapper.toDto(entity.getProject()))
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
