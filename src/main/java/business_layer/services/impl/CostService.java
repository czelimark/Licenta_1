package business_layer.services.impl;

import business_layer.dto.CostDto;
import business_layer.dto.ResourceDto;
import business_layer.mappers.CostMapper;
import business_layer.mappers.CurrencyMapper;
import business_layer.mappers.ProjectMapper;
import business_layer.mappers.ResourceMapper;
import business_layer.services.ICostService;
import data_layer.domain.*;
import data_layer.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class CostService implements ICostService {

    @Autowired
    private ICostRepository costRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private IResourceRepository resourceRepository;

    @Override
    public List<CostDto> getCosts(Integer id) {
        List<Cost> costs = costRepository.findByPortfolio(id);
        return CostMapper.toDtoList(costs);
    }

    @Override
    public CostDto getCost(Integer id) {
        Cost cost = costRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return CostMapper.toDto(cost);
    }

    @Override
    public void updateCost(CostDto costDto, Integer projectId, Integer resourceId, String currencyName) {
        Cost cost = costRepository.findById(costDto.getId()).orElseThrow(ResourceNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(ResourceNotFoundException::new);
        cost.setProject(project);
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(ResourceNotFoundException::new);
        cost.setResource(resource);
        cost.setDescription(costDto.getDescription());
        cost.setQuantity(costDto.getQuantity());
        cost.setCost(costDto.getCost());
        Currency currency = currencyRepository.findByName(currencyName).orElseThrow(ResourceNotFoundException::new);
        cost.setCurrency(currency);
        costRepository.flush();
    }

    @Override
    public void deleteCost(Integer id) {
        costRepository.deleteCostById(id);
        costRepository.flush();
    }

    @Override
    public void addCost(CostDto costDto, Integer idP, Integer idR, String name) {
        Project project = projectRepository.findById(idP).orElseThrow(ResourceNotFoundException::new);
        Resource resource = resourceRepository.findById(idR).orElseThrow(ResourceNotFoundException::new);
        Currency currency = currencyRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
        costDto.setProject(ProjectMapper.toDto(project));
        costDto.setResource(ResourceMapper.toDto(resource));
        costDto.setCurrency(CurrencyMapper.toDto(currency));
        Cost cost = CostMapper.toEntity(costDto);
        costRepository.save(cost);
        costRepository.flush();
    }

    @Override
    public List<ResourceDto> getResources() {
        List<Resource> resources = resourceRepository.findAll();
        return ResourceMapper.toDtoList(resources);
    }
}
