package testsuit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

public class Testsuit extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        // * Enter “tomsmith” username
        sendKeysToElement(By.xpath("//input[@id='username']"), "tomsmith");


        //   * Enter “SuperSecretPassword!” password
        sendKeysToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword!");

        //* Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        //* Verify the text “Secure Area”
        String accmess = getTextFromElement(By.xpath("//div[@class='example']"));

        String expmess = "Secure Area\n" +
                "Welcome to the Secure Area. When you are done click logout below.\n" +
                "Logout";

        Assert.assertEquals(expmess, accmess);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //* Enter “tomsmith1” username
        sendKeysToElement(By.xpath("//input[@id='username']"), "tomsmith1");

        // * Enter “SuperSecretPassword!” password
        sendKeysToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword!");

        //* Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));


        // * Verify the error message “Your username is invalid!”
        String accmess = getTextFromElement(By.xpath("//div[@id='flash']"));
        String expmess = "Your username is invalid!\n" +
                "×";

        Assert.assertEquals(expmess, accmess);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //  * Enter “tomsmith” username
        sendKeysToElement(By.xpath("//input[@id='username']"), "tomsmith");

        //* Enter “SuperSecretPassword” password
        sendKeysToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword");


        //* Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        //* Verify the error message “Your password is invalid!”
        String accmess =getTextFromElement(By.xpath("//div[@class='flash error']"));

        String expmess = "Your password is invalid!\n" +
                "×";

        Assert.assertEquals(expmess, accmess);

    }

    @After
    public void teardown() {
        closeBrowser();
    }


}
