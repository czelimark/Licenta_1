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

import javax.persistence.PersistenceException;
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
        return PortfolioMapper.toDtoList(portfolios);
    }

    @Override
    public PortfolioDto getPortfolio(Integer id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return PortfolioMapper.toDto(portfolio);
    }

    @Override
    public void updatePortfolio(PortfolioDto portfolioDto) {
        Portfolio portfolio = portfolioRepository.findById(portfolioDto.getId()).orElseThrow(ResourceNotFoundException::new);
        portfolio.setPortfolioName(portfolioDto.getPortfolioName());
        portfolio.setDescription(portfolioDto.getDescription());
        portfolio.setIssueDate(portfolioDto.getIssueDate());
        portfolioRepository.flush();
    }

    @Override
    public void deletePortfolio(Integer id) {
        portfolioRepository.deletePortfolioById(id);
        portfolioRepository.flush();
    }

    @Override
    public void addPortfolio(String username, PortfolioDto portfolioDto) {
        Portfolio portfolio = PortfolioMapper.toEntity(portfolioDto);
        User userr = userRepository.findByUsername(username).orElseThrow(ResourceNotFoundException::new);
        portfolio.setUser(userr);
        portfolioRepository.save(portfolio);
        portfolioRepository.flush();
    }
}
