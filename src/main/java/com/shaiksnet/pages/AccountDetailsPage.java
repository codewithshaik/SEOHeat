package com.shaiksnet.pages;

import com.shaiksnet.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;

import java.sql.Driver;

public class AccountDetailsPage {
    private final WebDriver driver;
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public  void theUserUpdateNaukriKeywords(DataTable dataTable) {
        try {
           WebElement naukriSkill =  driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"findNaukriSkill")));
           naukriSkill.click();
           WebElement naukriSkillInput = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"naukriSkillInput")));
              naukriSkillInput.sendKeys(dataTable.cell(0, 0));
              naukriSkillInput.sendKeys(dataTable.cell(1, 0), dataTable.cell(2, 0));
              WebElement naukriSkillAddButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"naukriSkillAddButton")));
              naukriSkillInput.click();

        } catch (Exception e) {
            logger.error("Failed to update Naukri keywords", e);
        }
    }
}
