package Business_Layer.Mappers;

import Business_Layer.Dto.MonthDto;
import Data_Layer.Domain.Month;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MonthMapper {

    public static Month toEntity(MonthDto dto) {
        return Month.builder()
                .monthName(dto.getMonthName())
                .project(ProjectMapper.toEntity(dto.getProject()))
                .build();
    }

    public static MonthDto toDto(Month entity) {
        return MonthDto.builder()
                .monthName(entity.getMonthName())
                .project(ProjectMapper.toDto(entity.getProject()))
                .build();
    }

    public static List<MonthDto> toDtoList(List<Month> entities) {
        return entities.stream()
                .map(MonthMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Month> toEntityList(List<MonthDto> dtos) {
        return dtos.stream()
                .map(MonthMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
