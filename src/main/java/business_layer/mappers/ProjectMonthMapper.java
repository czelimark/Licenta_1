package business_layer.mappers;

import business_layer.dto.ProjectMonthDto;
import data_layer.domain.ProjectMonth;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectMonthMapper {

    public static ProjectMonth toEntity(ProjectMonthDto dto) {
        return ProjectMonth.builder()
                .project(ProjectMapper.toEntity(dto.getProject()))
                .month(MonthMapper.toEntity(dto.getMonth()))
                .build();
    }

    public static ProjectMonthDto toDto(ProjectMonth entity) {
        return ProjectMonthDto.builder()
                .project(ProjectMapper.toDto(entity.getProject()))
                .month(MonthMapper.toDto(entity.getMonth()))
                .build();
    }
}
