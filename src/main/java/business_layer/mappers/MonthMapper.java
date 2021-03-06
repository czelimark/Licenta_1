package business_layer.mappers;

import business_layer.dto.MonthDto;
import data_layer.domain.Month;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MonthMapper {

    public static Month toEntity(MonthDto dto) {
        return Month.builder()
                .id(dto.getId())
                .monthName(dto.getMonthName())
                .nrOfProjects(dto.getNrOfProjects())
                .build();
    }

    public static MonthDto toDto(Month entity) {
        return MonthDto.builder()
                .id(entity.getId())
                .monthName(entity.getMonthName())
                .nrOfProjects(entity.getNrOfProjects())
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
