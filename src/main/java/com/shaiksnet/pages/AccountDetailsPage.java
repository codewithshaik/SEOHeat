package com.shaiksnet.pages;

import com.shaiksnet.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AccountDetailsPage {
    private final WebDriver driver;
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public static String getFixedLengthSkillsString(int targetLength) {
        List<String> allSkills = Arrays.asList(
                "Java", "Core Java", "Selenium WebDriver", "TestNG", "JUnit", "Cucumber", "REST Assured", "Postman",
                "Karate", "API Testing", "Automation Testing", "Manual Testing", "SDET", "Agile", "Scrum", "POM",
                "BDD", "TDD", "Maven", "Gradle", "Jenkins", "Git", "GitHub", "Bitbucket", "CI/CD", "DevOps", "Docker",
                "Kubernetes", "Linux", "MySQL", "MongoDB", "Oracle", "Swagger", "JSON", "XML", "YAML", "STLC", "SDLC",
                "JIRA", "Confluence", "XPath", "Playwright", "Cypress", "Appium", "BrowserStack", "Sauce Labs",
                "Bug Tracking", "Test Strategy", "Test Planning", "Smoke Testing", "Regression Testing",
                "Functional Testing", "Cross Browser Testing", "Test Reporting", "Version Control"
        );

        Collections.shuffle(allSkills);

        StringBuilder result = new StringBuilder();
        for (String skill : allSkills) {
            if (result.length() + skill.length() + 2 > targetLength) break; // +2 for ", "
            if (result.length() > 0) result.append(", ");
            result.append(skill);
        }

        return result.toString();
    }

    public static String get245Chars() {
        String skills245 = getFixedLengthSkillsString(245);
        return skills245 ;
    }

    public  void theUserUpdateNaukriKeywords() {
        try {
              logger.info("In theUserUpdateNaukriKeywords started");
              WebElement viewProfile = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"viewProfile")));
              viewProfile.click();
           WebElement naukriSkill =  driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"updateHeadLine")));
           naukriSkill.click();
           WebElement naukriSkillInput = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"skillInput")));
           naukriSkillInput.clear();
           String  skills = get245Chars();
           naukriSkillInput.sendKeys(skills);
              WebElement naukriSkillAddButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"naukriSkillSaveBtn")));
            naukriSkillAddButton.click();

            //key skills update
            WebElement keySkill =  driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"keySkills")));
            Thread.sleep(2000);
            keySkill.click();
            Thread.sleep(2000);
            WebElement keySkillInput = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"keySkillInput")));
            //removeskills
            Thread.sleep(3000); // Wait for skills to load

            int skillCount = driver.findElements(By.xpath(Util.getXpath(getClass().getSimpleName(), "removeSkills"))).size();

            for (int i = 0; i < skillCount; i++) {
                WebElement removeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(), "removeSkills")));
                removeButton.click();
                Thread.sleep(300); // Allow DOM to refresh after each removal
            }
            keySkillInput.sendKeys(skills);
            Thread.sleep(2000);
            WebElement keySkillAddButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"keySkillSaveBtn")));
            keySkillAddButton.click();



            logger.info("In theUserUpdateNaukriKeywords completed");

        } catch (Exception e) {
            logger.error("Failed to update Naukri keywords", e);
        }
    }
}
