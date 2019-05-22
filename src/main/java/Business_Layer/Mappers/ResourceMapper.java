package Business_Layer.Mappers;

import Business_Layer.Dto.ResourceDto;
import Data_Layer.Domain.Resource;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ResourceMapper {

    public static Resource toEntity(ResourceDto dto) {
        return Resource.builder()
                .id(dto.getId())
                .resourceName(dto.getResourceName())
                .usage(dto.getUsage())
                .currencies(CurrencyMapper.toEntityList(dto.getCurrencies()))
                .costs(CostMapper.toEntityList(dto.getCosts()))
                .build();
    }

    public static ResourceDto toDto(Resource entity) {
        return ResourceDto.builder()
                .id(entity.getId())
                .resourceName(entity.getResourceName())
                .usage(entity.getUsage())
                .currencies(CurrencyMapper.toDtoList(entity.getCurrencies()))
                .costs(CostMapper.toDtoList(entity.getCosts()))
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
