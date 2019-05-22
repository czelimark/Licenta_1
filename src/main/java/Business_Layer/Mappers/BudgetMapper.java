package Business_Layer.Mappers;

import Business_Layer.Dto.BudgetDto;
import Data_Layer.Domain.Budget;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BudgetMapper {

    public static Budget toEntity(BudgetDto dto) {
        return Budget.builder()
                .id(dto.getId())
                .budgetDescription(dto.getBudgetDescription())
                .allocatedMoney(dto.getAllocatedMoney())
                .project(ProjectMapper.toEntity(dto.getProject()))
                .months(MonthMapper.toEntityList(dto.getMonths()))
                .build();
    }

    public static BudgetDto toDto(Budget entity) {
        return BudgetDto.builder()
                .id(entity.getId())
                .budgetDescription(entity.getBudgetDescription())
                .allocatedMoney(entity.getAllocatedMoney())
                .project(ProjectMapper.toDto(entity.getProject()))
                .months(MonthMapper.toDtoList(entity.getMonths()))
                .build();
    }

    public static List<BudgetDto> toDtoList(List<Budget> entities) {
        return entities.stream()
                .map(BudgetMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Budget> toEntityList(List<BudgetDto> dtos) {
        return dtos.stream()
                .map(BudgetMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
