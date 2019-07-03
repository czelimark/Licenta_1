package business_layer.services;

import business_layer.dto.CostDto;

import java.util.List;

public interface ICostService {

    CostDto getCost(Integer id);

    List<CostDto> getCosts(Integer id);

    void updateCost(CostDto costDto, Integer projectId, Integer resourceId, Integer currencyId);

    void deleteCost(Integer id);

    void addCost(CostDto costDto);
}
