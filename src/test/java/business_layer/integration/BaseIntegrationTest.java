package business_layer.integration;

import bd_config.TestConfiguration;
import business_layer.dto.UserDto;
import business_layer.mappers.UserMapper;
import data_layer.domain.User;
import data_layer.repositories.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

/**
 * Utilitary class for tests.
 * Here you could add methods for inserting data into db, for having presets
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public abstract class BaseIntegrationTest {

    @Autowired
    protected IUserRepository userRepository;

    @Autowired
    protected IPortfolioRepository portfolioRepository;

    @Autowired
    protected IProjectRepository projectRepository;

    @Autowired
    protected IWalletRepository walletRepository;

    @Autowired
    protected ICostRepository costRepository;

    @Autowired
    protected ICurrencyRepository currencyRepository;

    @Autowired
    protected IResourceRepository resourceRepository;

    @Before
    public void emptyDatabase() {
        resourceRepository.deleteAll();
        resourceRepository.flush();
        currencyRepository.deleteAll();
        currencyRepository.flush();
        walletRepository.deleteAll();
        walletRepository.flush();
        projectRepository.deleteAll();
        projectRepository.flush();
        costRepository.deleteAll();
        costRepository.flush();
        portfolioRepository.deleteAll();
        portfolioRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();
    }
}
