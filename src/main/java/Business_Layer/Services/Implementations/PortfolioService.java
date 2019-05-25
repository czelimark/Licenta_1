package Business_Layer.Services.Implementations;

import Business_Layer.Dto.PortfolioDto;
import Business_Layer.Mappers.PortfolioMapper;
import Business_Layer.Services.IPortfolioService;
import Data_Layer.Domain.Portfolio;
import Data_Layer.Domain.User;
import Data_Layer.Repositories.IPortfolioRepository;
import Data_Layer.Repositories.IUserRepository;
import Utils.ResourceNotFoundException;
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
    public List<PortfolioDto> getPortfolios(String email) {
        List<Portfolio> portfolios = portfolioRepository.findByUser(email);
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
    public void addPortfolio(String email, PortfolioDto portfolioDto) {
        Portfolio portfolio = PortfolioMapper.toEntity(portfolioDto);
        User userr = userRepository.findByUsername(email).orElseThrow(ResourceNotFoundException::new);
        userr.getPortfolios().add(portfolio);
        portfolio.setUserr(userr);
        portfolioRepository.save(portfolio);
        portfolioRepository.flush();
    }
}
