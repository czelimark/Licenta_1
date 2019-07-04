package business_layer.integration;

import business_layer.dto.*;
import business_layer.mappers.CurrencyMapper;
import business_layer.mappers.ResourceMapper;
import business_layer.services.*;
import data_layer.domain.Currency;
import data_layer.domain.Resource;
import data_layer.repositories.ICurrencyRepository;
import data_layer.repositories.IResourceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public class CostTest extends BaseIntegrationTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPortfolioService portfolioService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private IResourceRepository resourceRepository;

    @Autowired
    private ICostService costService;

    @Test
    @Transactional
    public void costTest() {
        UserDto userDto = new UserDto(2, "userr1@gmail.com", "parola123", "Name", "FirstName", Date.valueOf("1991-10-28"), false, "234623478", null, "USER");
        userService.addUser(userDto);
        PortfolioDto portfolioDto = new PortfolioDto(1, "Portfolio1", "Gud", Date.valueOf("2019-06-28"), userDto);
        portfolioService.addPortfolio(userDto.getUsername(), portfolioDto);

        Currency currency = new Currency(1, "RON");
        currencyRepository.save(currency);
        currencyRepository.flush();
        Assert.assertEquals(1, currencyRepository.findAll().size());

        WalletDto walletDto = new WalletDto(1, "Wallet1", "Gud", 3500.00, userDto, CurrencyMapper.toDto(currency));
        walletService.addWallet(userDto.getUsername(), walletDto);
        Assert.assertEquals(1, walletService.getWallets(userDto.getUsername()).size());

        ProjectDto projectDto = new ProjectDto(1, "Project1", "Gud", Date.valueOf("2018-02-26"), Date.valueOf("2019-06-26"), 3500.00, null, null, "Gud", portfolioDto, walletDto);
        projectService.addProject(projectDto);
        Assert.assertEquals(1, projectService.getProjects(portfolioDto.getId()).size());

        Resource resource = new Resource(1, "Human Resouces");
        resourceRepository.save(resource);
        resourceRepository.flush();
        Assert.assertEquals(1, currencyRepository.findAll().size());

        CostDto costDto = new CostDto(1, projectDto, ResourceMapper.toDto(resource), "Gud", 8, 300.00, CurrencyMapper.toDto(currency));
        costService.addCost(costDto, projectDto.getId(), resource.getId(), currency.getCurrencyName());
        Assert.assertEquals(1, costService.getCosts(portfolioDto.getId()).size());

        CostDto costDto2 = new CostDto(2, projectDto, ResourceMapper.toDto(resource), "Gud", 8, 300.00, CurrencyMapper.toDto(currency));
        costService.addCost(costDto2, projectDto.getId(), resource.getId(), currency.getCurrencyName());
        Assert.assertEquals(2, costService.getCosts(portfolioDto.getId()).size());

        costDto2.setCost(800.00);
        costService.updateCost(costDto2, projectDto.getId(), resource.getId(), currency.getCurrencyName());
        Assert.assertEquals((Object)800.00, (Object)costDto2.getCost());
    }
}
