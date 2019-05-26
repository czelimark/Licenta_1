package Business_Layer.Mappers;

import Business_Layer.Dto.ProjectCostDto;
import Data_Layer.Domain.ProjectCost;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectCostMapper {

    public static ProjectCost toEntity(ProjectCostDto dto) {
        return ProjectCost.builder()
                .resourceCost(dto.getResourceCost())
                .project(ProjectMapper.toEntity(dto.getProject()))
                .resource(ResourceMapper.toEntity(dto.getResource()))
                .build();
    }

    public static ProjectCostDto toDto(ProjectCost entity) {
        return ProjectCostDto.builder()
                .resourceCost(entity.getResourceCost())
                .project(ProjectMapper.toDto(entity.getProject()))
                .resource(ResourceMapper.toDto(entity.getResource()))
                .build();
    }
}
