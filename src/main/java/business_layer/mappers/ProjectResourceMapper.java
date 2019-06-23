package business_layer.mappers;

import business_layer.dto.ProjectResourceDto;
import data_layer.domain.ProjectResource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectResourceMapper {

    public static ProjectResource toEntity(ProjectResourceDto dto) {
        return ProjectResource.builder()
                .id(dto.getId())
                .resourceCost(dto.getResourceCost())
                .project(ProjectMapper.toEntity(dto.getProject()))
                .resource(ResourceMapper.toEntity(dto.getResource()))
                .build();
    }

    public static ProjectResourceDto toDto(ProjectResource entity) {
        return ProjectResourceDto.builder()
                .id(entity.getId())
                .resourceCost(entity.getResourceCost())
                .project(ProjectMapper.toDto(entity.getProject()))
                .resource(ResourceMapper.toDto(entity.getResource()))
                .build();
    }
}
