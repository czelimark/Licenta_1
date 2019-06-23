package business_layer.mappers;

import business_layer.dto.ProjectDto;
import data_layer.domain.Project;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProjectMapper {

    public static Project toEntity(ProjectDto dto) {
        return Project.builder()
                .id(dto.getId())
                .projectName(dto.getProjectName())
                .description(dto.getDescription())
                .issueDate(dto.getIssueDate())
                .closingDate(dto.getClosingDate())
                .estimatedPrice(dto.getEstimatedPrice())
                .actualPrice(0.00)
                .difference(0.00)
                .comments(dto.getComments())
                .portfolio(PortfolioMapper.toEntity(dto.getPortfolio()))
                .wallet(WalletMapper.toEntity(dto.getWallet()))
                .build();
    }

    public static ProjectDto toDto(Project entity) {
        return ProjectDto.builder()
                .id(entity.getId())
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
