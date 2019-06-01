package business_layer.services;

import business_layer.dto.PortfolioDto;

import java.util.List;

public interface IPortfolioService {

    PortfolioDto getPortfolio(String portfolioName);

    List<PortfolioDto> getPortfolios(String email);

    void updatePortfolio(PortfolioDto portfolioDto);

    void deletePortfolio(PortfolioDto portfolioDto);

    void addPortfolio(String email, PortfolioDto portfolioDto);
}
