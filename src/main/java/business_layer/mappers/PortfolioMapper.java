package business_layer.mappers;

import business_layer.dto.PortfolioDto;
import data_layer.domain.Portfolio;

import java.util.List;
import java.util.stream.Collectors;

public class PortfolioMapper {

    public static Portfolio toEntity(PortfolioDto dto) {
        return Portfolio.builder()
                .id(dto.getId())
                .portfolioName(dto.getPortfolioName())
                .description(dto.getDescription())
                .issueDate(dto.getIssueDate())
                .build();
    }

    public static PortfolioDto toDto(Portfolio entity) {
        return PortfolioDto.builder()
                .id(entity.getId())
                .portfolioName(entity.getPortfolioName())
                .description(entity.getDescription())
                .issueDate(entity.getIssueDate())
                .user(UserMapper.toDto(entity.getUser()))
                .build();
    }

    public static List<PortfolioDto> toDtoList(List<Portfolio> entities) {
        return entities.stream()
                .map(PortfolioMapper::toDto)
                .sorted()
                .collect(Collectors.toList());
    }
}
