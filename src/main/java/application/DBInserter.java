package application;

import data_layer.domain.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

public class DBInserter {

    private static User user1;
    private static Portfolio portfolio1, portfolio2;
    private static Project project1, project2;
    private static Resource resource1, resource2;
    private static Wallet wallet1, wallet2;
    private static Cost cost1, cost2;
    private static Currency currency1, currency2;
    private static Month january, february, march, april, may, june, july, august, september, october, november, december;
    private static ProjectCost projectCost1, projectCost2;
    private static ProjectMonth projectMonth1, projectMonth2, projectMonth3;

    static {
        user1 = new User();
        portfolio1 = new Portfolio();
        portfolio2 = new Portfolio();
        project1 = new Project();
        project2 = new Project();
        resource1 = new Resource();
        resource2 = new Resource();
        wallet1 = new Wallet();
        wallet2 = new Wallet();
        cost1 = new Cost();
        cost2 = new Cost();
        currency1 = new Currency();
        currency2 = new Currency();
        january = new Month();
        february = new Month();
        march = new Month();
        april = new Month();
        may = new Month();
        june = new Month();
        july = new Month();
        august = new Month();
        september = new Month();
        october = new Month();
        november = new Month();
        december = new Month();
        projectCost1 = new ProjectCost();
        projectCost2 = new ProjectCost();
        projectMonth1 = new ProjectMonth();
        projectMonth2 = new ProjectMonth();
        projectMonth3 = new ProjectMonth();
    }

    private static void initResources() {
        resource1.setResourceName("Resource1");
        resource1.setResourceUsage("Usage1");

        resource1.setResourceName("Resource2");
        resource1.setResourceUsage("Usage2");
    }

    private static void initCosts() {
        cost1.setCostType("Cost1");
        cost1.setCost(200.00);
        cost1.setProject(project1);

        cost2.setCostType("Cost2");
        cost2.setCost(400.00);
        cost2.setProject(project2);
    }

    private static void initCurrencies() {
        currency1.setCurrencyName("RON");
        currency2.setCurrencyName("EUR");
    }

    private static void initWallets() {
        wallet1.setWalletName("Wallet1");
        wallet1.setDescription("Description1");
        wallet1.setAllocatedMoney(20000.00);
        wallet1.setCurrency(currency1);

        wallet2.setWalletName("Wallet2");
        wallet2.setDescription("Description2");
        wallet2.setAllocatedMoney(20000.00);
        wallet2.setCurrency(currency2);
    }

    private static void initMonths() {
        january.setMonthName("January");
        february.setMonthName("February");
        march.setMonthName("March");
        april.setMonthName("April");
        may.setMonthName("May");
        june.setMonthName("June");
        july.setMonthName("July");
        august.setMonthName("August");
        september.setMonthName("September");
        october.setMonthName("October");
        november.setMonthName("November");
        december.setMonthName("December");
    }

    private static void initProjects() {
        project1.setProjectName("Project1");
        project1.setDescription("Description1");
        project1.setIssueDate(Date.valueOf(LocalDate.now()));
        project1.setClosingDate(Date.valueOf(LocalDate.now().plusMonths(3)));
        project1.setEstimatedPrice(5000.00);
        project1.setActualPrice(8000.00);
        project1.setDifference(project1.getActualPrice() - project1.getEstimatedPrice());
        project1.setComments("Comment1");
        project1.setPortfolio(portfolio1);
        project1.setWallet(wallet1);
        project1.setCosts(Arrays.asList(cost1, cost2));
    }

    private static void initProjectCosts() {
        projectCost1.setProject(project1);
        projectCost1.setResource(resource1);
        projectCost1.setResourceCost(5000.00);

        projectCost2.setProject(project2);
        projectCost2.setResource(resource2);
        projectCost2.setResourceCost(8000.00);
    }

    private static void initProjectMonths() {
        projectMonth1.setProject(project1);
        projectMonth1.setMonth(june);

        projectMonth2.setProject(project1);
        projectMonth2.setMonth(july);

        projectMonth3.setProject(project1);
        projectMonth3.setMonth(august);

    }

    private static void initPortfolios() {
        portfolio1.setPortfolioName("Portfolio1");
        portfolio1.setDescription("Description1");
        portfolio1.setIssueDate(Date.valueOf(LocalDate.now()));
        portfolio1.setUser(user1);

        portfolio2.setPortfolioName("Portfolio2");
        portfolio2.setDescription("Description2");
        portfolio2.setIssueDate(Date.valueOf(LocalDate.now()));
        portfolio2.setUser(user1);
    }

    private static void initUser() {
        user1.setUsername("userr1@gmail.com");
        user1.setPassword(BCrypt.hashpw("userr123", BCrypt.gensalt()));
        user1.setFirstName("User1");
        user1.setLastName("User1");
        user1.setBirthDate(Date.valueOf(LocalDate.now().minusYears(20)));
        user1.setGender(false);
        user1.setPhoneNumber("0757383838");
    }
}
