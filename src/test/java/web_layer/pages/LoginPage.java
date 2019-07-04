package web_layer.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.CSS, using = "#username")
    private WebElement inputUserName;

    @FindBy(how = How.CSS, using = "#password")
    private WebElement inputPassword;

    @FindBy(how = How.CSS, using = ".btn-primary")
    private WebElement submitCredentials;

    @FindBy(how = How.CSS, using = ".invalid")
    private WebElement invalidFields;

    @FindBy(how = How.CSS, using = "a[href=\"#!/login\"]")
    private WebElement logout;

    private void enterUserName(String userName){
        inputUserName.sendKeys(userName);
    }

    private void enterPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void submitCredentials( ){
        submitCredentials.click();
    }

    public void login(String userName, String password){
        enterUserName(userName);
        enterPassword(password);
        submitCredentials();
    }

    public void logout(){
        logout.click();
    }

    public boolean invalidFields() { return invalidFields.isDisplayed(); }
}
