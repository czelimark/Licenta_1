package business_layer.services.impl;

import business_layer.dto.PortfolioDto;
import business_layer.mappers.PortfolioMapper;
import business_layer.services.IPortfolioService;
import data_layer.domain.Portfolio;
import data_layer.domain.User;
import data_layer.repositories.IPortfolioRepository;
import data_layer.repositories.IUserRepository;
import utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PortfolioService implements IPortfolioService {

    @Autowired
    private IPortfolioRepository portfolioRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<PortfolioDto> getPortfolios(String username) {
        List<Portfolio> portfolios = portfolioRepository.findByUser(username);
        if(portfolios.size() == 0)
            throw new ResourceNotFoundException();
        return PortfolioMapper.toDtoList(portfolios);
    }

    @Override
    public PortfolioDto getPortfolio(String portfolioName) {
        Portfolio portfolio = portfolioRepository.findByName(portfolioName).orElseThrow(ResourceNotFoundException::new);
        return PortfolioMapper.toDto(portfolio);
    }

    @Override
    public void updatePortfolio(PortfolioDto portfolioDto) {
        Portfolio portfolio = portfolioRepository.findById(portfolioDto.getId()).orElseThrow(ResourceNotFoundException::new);
        portfolio.setDescription(portfolioDto.getDescription());
        portfolio.setIssueDate(portfolioDto.getIssueDate());
        portfolioRepository.flush();
    }

    @Override
    public void deletePortfolio(PortfolioDto portfolioDto) {
        portfolioRepository.deletePortfolioById(PortfolioMapper.toEntity(portfolioDto).getId());
        portfolioRepository.flush();
    }

    @Override
    public void addPortfolio(String username, PortfolioDto portfolioDto) {
        Portfolio portfolio = PortfolioMapper.toEntity(portfolioDto);
        User userr = userRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        userr.getPortfolios().add(portfolio);
        portfolio.setUser(userr);
        portfolioRepository.save(portfolio);
        portfolioRepository.flush();
    }
}