import Data_Layer.Domain.*;

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

    static {
        user1 = new User();
        portfolio1 = new Portfolio();
        project1 = new Project();
        resource1 = new Resource();
        wallet1 = new Wallet();
        cost1 = new Cost();
        currency1 = new Currency();
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
    }

    private static void initResources() {
        resource1.setResourceName("Resource1");
        resource1.setUsage("Usage1");

        resource1.setResourceName("Resource2");
        resource1.setUsage("Usage2");
    }


    private static void initPortfolios() {
        portfolio1.setPortfolioName("Portfolio1");
        portfolio1.setDescription("Descriere1");
        portfolio1.setIssueDate(Date.valueOf(LocalDate.now()));
        portfolio1.setUser(user1);
        portfolio1.setProjects(Arrays.asList(project1, project2));
    }


}
