package business_layer.mappers;

import business_layer.dto.ResourceDto;
import data_layer.domain.Resource;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ResourceMapper {

    public static Resource toEntity(ResourceDto dto) {
        return Resource.builder()
                .resourceName(dto.getResourceName())
                .resourceUsage(dto.getResourceUsage())
                .build();
    }

    public static ResourceDto toDto(Resource entity) {
        return ResourceDto.builder()
                .resourceName(entity.getResourceName())
                .resourceUsage(entity.getResourceUsage())
                .build();
    }

    public static List<ResourceDto> toDtoList(List<Resource> entities) {
        return entities.stream()
                .map(ResourceMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Resource> toEntityList(List<ResourceDto> dtos) {
        return dtos.stream()
                .map(ResourceMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
