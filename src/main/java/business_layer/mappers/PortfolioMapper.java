package business_layer.mappers;

import business_layer.dto.PortfolioDto;
import data_layer.domain.Portfolio;

import java.util.List;
import java.util.stream.Collectors;

public class PortfolioMapper {

    public static Portfolio toEntity(PortfolioDto dto) {
        return Portfolio.builder()
                .portfolioName(dto.getPortfolioName())
                .description(dto.getDescription())
                .issueDate(dto.getIssueDate())
                .user(UserMapper.toEntity(dto.getUser()))
                .projects(ProjectMapper.toEntityList(dto.getProjects()))
                .build();
    }

    public static PortfolioDto toDto(Portfolio entity) {
        return PortfolioDto.builder()
                .portfolioName(entity.getPortfolioName())
                .description(entity.getDescription())
                .issueDate(entity.getIssueDate())
                .user(UserMapper.toDto(entity.getUser()))
                .projects(ProjectMapper.toDtoList(entity.getProjects()))
                .build();
    }

    public static List<PortfolioDto> toDtoList(List<Portfolio> entities) {
        return entities.stream()
                .map(PortfolioMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Portfolio> toEntityList(List<PortfolioDto> dtos) {
        return dtos.stream()
                .map(PortfolioMapper::toEntity)
                .sorted()
                .collect(Collectors.toList());
    }
}
