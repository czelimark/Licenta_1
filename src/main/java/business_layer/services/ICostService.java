package business_layer.services;

import business_layer.dto.CostDto;
import business_layer.dto.ResourceDto;

import java.util.List;

public interface ICostService {

    CostDto getCost(Integer id);

    List<CostDto> getCosts(Integer id);

    void updateCost(CostDto costDto, Integer projectId, Integer resourceId, String currencyName);

    void deleteCost(Integer id);

    void addCost(CostDto costDto, Integer idP, Integer idR, String name);

    List<ResourceDto> getResources();
}
