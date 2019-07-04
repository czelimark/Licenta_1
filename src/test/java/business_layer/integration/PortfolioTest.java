package business_layer.integration;

import business_layer.dto.PortfolioDto;
import business_layer.dto.UserDto;
import business_layer.services.IPortfolioService;
import business_layer.services.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public class PortfolioTest extends BaseIntegrationTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPortfolioService portfolioService;

    @Test
    @Transactional
    public void portfolioTest() {
        UserDto userDto = new UserDto(1, "user1@gmail.com", "parola123", "Name", "FirstName", Date.valueOf("1991-10-28"), false, "234623478", null, "USER");
        userService.addUser(userDto);
        PortfolioDto portfolioDto = new PortfolioDto(1, "Portfolio1", "Gud", Date.valueOf("2019-06-28"), userDto);
        portfolioService.addPortfolio(userDto.getUsername(), portfolioDto);
        Assert.assertEquals(userDto.getUsername(), portfolioDto.getUser().getUsername());

        PortfolioDto portfolioDto2 = new PortfolioDto(2, "Portfolio2", "Gud", Date.valueOf("2019-06-28"), userDto);
        portfolioService.addPortfolio(userDto.getUsername(), portfolioDto2);
        Assert.assertEquals(2, portfolioService.getPortfolios(userDto.getUsername()).size());

        portfolioDto.setDescription("The best");
        portfolioService.updatePortfolio(portfolioDto);
        Assert.assertEquals("The best", portfolioDto.getDescription());

        portfolioService.deletePortfolio(portfolioDto2.getId());
        Assert.assertEquals(1, portfolioService.getPortfolios(userDto.getUsername()).size());
    }
}
