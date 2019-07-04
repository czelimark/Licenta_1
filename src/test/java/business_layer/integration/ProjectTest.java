package business_layer.integration;

import business_layer.dto.*;
import business_layer.mappers.CurrencyMapper;
import business_layer.services.IPortfolioService;
import business_layer.services.IProjectService;
import business_layer.services.IUserService;
import business_layer.services.IWalletService;
import data_layer.domain.Currency;
import data_layer.repositories.ICurrencyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public class ProjectTest extends BaseIntegrationTest {

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

    @Test
    @Transactional
    public void portfolioTest() {
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

        ProjectDto projectDto2 = new ProjectDto(2, "Project1", "Gud", Date.valueOf("2018-02-26"), Date.valueOf("2019-06-26"), 3500.00, null, null, "Gud", portfolioDto, walletDto);
        projectService.addProject(projectDto2);
        Assert.assertEquals(2, projectService.getProjects(portfolioDto.getId()).size());

        projectDto.setDescription("The best");
        projectService.updateProject(projectDto, null, null);
        Assert.assertEquals("The best", projectDto.getDescription());
    }
}
