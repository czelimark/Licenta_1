package Business_Layer.Services;

import Business_Layer.Dto.PortfolioDto;
import Data_Layer.Domain.Portfolio;
import Data_Layer.Domain.User;

import java.util.List;

public interface IPortfolioService {

    PortfolioDto getPortfolio(String portfolioName);

    List<PortfolioDto> getPortfolios(String email);

    void updatePortfolio(PortfolioDto portfolioDto);

    void deletePortfolio(PortfolioDto portfolioDto);

    void addPortfolio(String email, PortfolioDto portfolioDto);
}
