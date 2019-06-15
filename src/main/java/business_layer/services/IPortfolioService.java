package business_layer.services;

import business_layer.dto.PortfolioDto;

import java.util.List;

public interface IPortfolioService {

    PortfolioDto getPortfolio(Integer id);

    List<PortfolioDto> getPortfolios(String email);

    void updatePortfolio(PortfolioDto portfolioDto);

    void deletePortfolio(Integer id);

    void addPortfolio(String email, PortfolioDto portfolioDto);
}
