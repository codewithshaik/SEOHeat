package com.shaiksnet.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.shaiksnet.utility.Global_Variables.*;
import static org.jsoup.helper.Validate.fail;
import com.shaiksnet.utility.Util;
import com.shaiksnet.utility.Global_Variables.*;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(){

        try{
            logger.info("In OpenUrl");
            String url = Util.getProperty(psLbl_defaultimplicitywait);
            driver.get(url);
            logger.info("OpenUrl Completed");

        }catch (Exception e){
            e.printStackTrace();
            fail("step: openUrl failed");
        }
    }

    public void enteringUserId(String userId){
        try{
            logger.info("In enteringUserId");
            Util.implicitWait(driver);
            Util.switchToWindow(driver,"0");
            driver.navigate().refresh();

            WebElement element = driver
                    .findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"SignInButton")));
            element.click();

        }catch (Exception e){
            e.printStackTrace();
            fail("step: enteringUserId failed");
        }
    }

    public void userLoginIntoCheckUsVisaSlotsNet() {
        try{

            Util.implicitWait(driver);
            openUrl();


        }catch (Exception e){
            fail("Step: userLoginIntoCheckUsVisaSlotsNet failed");
        }





    }
    public class employee {

    }
}
