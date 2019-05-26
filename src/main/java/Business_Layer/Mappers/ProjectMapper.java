package Business_Layer.Mappers;

import Business_Layer.Dto.ProjectDto;
import Data_Layer.Domain.Project;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProjectMapper {

    public static Project toEntity(ProjectDto dto) {
        return Project.builder()
                .projectName(dto.getProjectName())
                .description(dto.getDescription())
                .issueDate(dto.getIssueDate())
                .closingDate(dto.getClosingDate())
                .estimatedPrice(dto.getEstimatedPrice())
                .actualPrice(dto.getActualPrice())
                .difference(dto.getDifference())
                .comments(dto.getComments())
                .portfolio(PortfolioMapper.toEntity(dto.getPortfolio()))
                .wallet(WalletMapper.toEntity(dto.getWallet()))
                .costs(CostMapper.toEntityList(dto.getCosts()))
                .months(MonthMapper.toEntityList(dto.getMonths()))
                .build();
    }

    public static ProjectDto toDto(Project entity) {
        return ProjectDto.builder()
                .projectName(entity.getProjectName())
                .description(entity.getDescription())
                .issueDate(entity.getIssueDate())
                .closingDate(entity.getClosingDate())
                .estimatedPrice(entity.getEstimatedPrice())
                .actualPrice(entity.getActualPrice())
                .difference(entity.getDifference())
                .comments(entity.getComments())
                .portfolio(PortfolioMapper.toDto(entity.getPortfolio()))
                .wallet(WalletMapper.toDto(entity.getWallet()))
                .costs(CostMapper.toDtoList(entity.getCosts()))
                .months(MonthMapper.toDtoList(entity.getMonths()))
                .build();
    }

    public static List<ProjectDto> toDtoList(List<Project> entities) {
        return entities.stream()
                .map(ProjectMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Project> toEntityList(List<ProjectDto> dtos) {
        return dtos.stream()
                .map(ProjectMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
