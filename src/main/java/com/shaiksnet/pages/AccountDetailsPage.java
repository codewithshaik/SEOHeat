package com.shaiksnet.pages;

import com.shaiksnet.utility.DataManager;
import com.shaiksnet.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.*;

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
//                //-- DSA & Problem Solving Keywords --//
//                "Data Structures", "Algorithms", "Problem Solving", "Time Complexity", "Space Complexity",
//                "Arrays", "Strings", "Linked Lists", "Trees", "Graphs", "Recursion", "Backtracking",
//                "Dynamic Programming", "Greedy Algorithms", "HashMap", "Stack", "Queue", "Heap", "Binary Search",
//                "BFS", "DFS", "Two Pointers", "Sliding Window", "Bit Manipulation", "Sorting", "Searching",
//                "LeetCode", "HackerRank", "Codeforces", "Algorithm Optimization", "Coding Challenges"
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
              Thread.sleep(2000); // Wait for the element to be visible
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

    public void userUploadsTheResumeToNaukriProfile() {
        try{
            logger.info("In userUploadsTheResumeToNaukriProfile Started");
            String path = Util.getProperty("resumePath");

            // Get all files in the folder
            File folder = new File(System.getProperty("user.dir")+path);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf") || name.toLowerCase().endsWith(".doc") || name.toLowerCase().endsWith(".docx"));

            if (files == null || files.length == 0) {
                logger.error("No resume files found in folder: " + path);
                return;
            }

            // Pick a random resume
            File randomResume = files[new Random().nextInt(files.length)];
            logger.info("Selected Resume: " + randomResume.getAbsolutePath());
            DataManager.setString("randomResume", randomResume.getAbsolutePath().toString());



            WebElement uploadResumeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"updateResumeBTn")));

            // 🔓 Make it visible
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('style', 'display:block !important; visibility:visible !important; opacity:1 !important; position:relative; z-index:9999;');",
                    uploadResumeButton
            );

            // Wait for the element to be visible

            System.out.println("Is file input displayed? " + uploadResumeButton.isDisplayed());
            System.out.println("Is file input enabled? " + uploadResumeButton.isEnabled());


            uploadResumeButton.sendKeys(randomResume.getAbsolutePath());
            Thread.sleep(3000);




        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void theUserShouldSeeTheUpdatedNaukriProfile() {
        try{

            String resumeNameUploaded = DataManager.getString("randomResume");
            resumeNameUploaded= resumeNameUploaded.replaceAll(".*\\((\\d+)\\)\\.pdf$", "$1");

            WebElement resumeTitle = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"resumeTitle")));

            String resumeNameInNaukri =resumeTitle.getText().trim();
            resumeNameInNaukri= resumeNameInNaukri.replaceAll(".*?(\\d+)\\.pdf$", "$1");

            System.out.println(resumeNameInNaukri+resumeNameUploaded);

            if (resumeNameUploaded.equals(resumeNameInNaukri)) {
                logger.info("Resume uploaded successfully: " + resumeNameInNaukri);
            } else {
                Assert.assertTrue(" Resume name last character mismatch! Expected: "
                        + resumeNameInNaukri + ", but got: "
                        + resumeNameUploaded, false);
            }

        }catch (Exception e){
            logger.error("Failed to verify updated Naukri profile", e);

        }
    }

    public void theuserapplyforjobsinnaukri() {
        try{
            logger.info("In theuserapplyforjobsinnaukri started");
            Util.waitForPageToLoad(driver);

            Thread.sleep(2000); // Wait for the page to load

            WebElement jobsTab = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"jobs")));
            Util.waitUntilElementIsClickable(driver, jobsTab);
            jobsTab.click();

            Thread.sleep(2000); // Wait for the jobs page to load

            List<WebElement> jobSelectBoxList = driver.findElements(By.xpath(Util.getXpath(getClass().getSimpleName(),"jobCheckboxList")));
            int count=0;
             JavascriptExecutor js = (JavascriptExecutor) driver;
            List<WebElement> shuffled = new ArrayList<>(jobSelectBoxList);
            Collections.shuffle(shuffled);

            for (WebElement job : shuffled) {
                if (count == 5) break;

                try {
                    if (job.isDisplayed() && job.isEnabled()) {
                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", job);
                        try {
                            job.click(); // Try normal click first
                        } catch (ElementClickInterceptedException e) {
                            js.executeScript("arguments[0].click();", job); // Fallback to JS click
                        }
                        Thread.sleep(1000);
                        count++;
                    }
                } catch (Exception e) {
                    System.out.println("Failed to click a checkbox: " + e.getMessage());
                    // Continue to next checkbox
                }

            }

            WebElement ApplyButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"applyBtn")));
            ApplyButton.click();

            Thread.sleep(2000); // Wait for the apply modal to appear

            WebElement chatExitButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"exitBtn")));
            if (chatExitButton.isDisplayed()) {
                chatExitButton.click();
            }

//            WebElement homeButton = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"homeBtn")));
            Thread.sleep(1000);
//            homeButton.click();
            driver.navigate().back();
            Thread.sleep(1000); // Wait for the home page to load
            driver.navigate().back();
             // Wait for the home page to load


            logger.info("In theuserapplyforjobsinnaukri completed");


        }catch(Exception e){
            logger.error("Failed to apply for jobs in Naukri", e);
            Assert.fail("Failed to apply for jobs in Naukri: " + e.getMessage());
        }





    }

    public void userMakeAVisitInGitAndLinkedIn() {
        try {
            logger.info("In userMakeAVisitInGitAndLinkedIn started");

            List<String> urls = Arrays.asList(
                    "https://github.com/Mrshaik-hub",
                    "linkedin", // trigger element click when this is found
                    "https://medium.com/@shaikmahaboobsubhani00"
            );

            int repeatCount = 40;
            Random random = new Random();

            List<Dimension> screenSizes = Arrays.asList(
                    new Dimension(1280, 800), // Desktop
                    new Dimension(375, 667),  // Mobile
                    new Dimension(768, 1024)  // Tablet
            );

            for (int i = 0; i < repeatCount; i++) {
                for (String url : urls) {
                    try {
                        Dimension screenSize = screenSizes.get(random.nextInt(screenSizes.size()));
                        driver.manage().window().setSize(screenSize);

                        if (url.contains("linkedin")) {
                            // ✅ Just find and click the LinkedIn button
                            WebElement linkedInBtn = driver.findElement(By.xpath(
                                    Util.getXpath(getClass().getSimpleName(), "linkedinBtn")
                            ));

                            ((JavascriptExecutor) driver).executeScript(
                                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                                    linkedInBtn
                            );
                            Thread.sleep(1000);
                            linkedInBtn.click();
                            logger.info("Clicked on LinkedIn button");
                        } else {
                            // Normal visit and scroll
                            driver.get(url);
                            logger.info("Visited: " + url);

                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            int scrollSteps = 3;
                            for (int j = 0; j < scrollSteps; j++) {
                                int y = (j + 1) * (int) (driver.manage().window().getSize().getHeight() * 0.5);
                                js.executeScript("window.scrollTo(0, arguments[0]);", y);
                                Thread.sleep(1000 + random.nextInt(500));
                            }

                            // Optionally click a random visible link
//                            List<WebElement> links = driver.findElements(By.xpath("//a[string-length(@href) > 5]"));
//                            if (!links.isEmpty()) {
//                                WebElement randomLink = links.get(random.nextInt(links.size()));
//                                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", randomLink);
//                                Thread.sleep(500);
//                                try {
//                                    randomLink.click();
//                                    driver.navigate().back();
//                                } catch (Exception ignored) {}
//                            }
                        }

                        // Wait 5–10 seconds
                        Thread.sleep(5000 + random.nextInt(5000));

                    } catch (Exception e) {
                        logger.warn("Failed during visit/click for: " + url + " | " + e.getMessage());
                    }
                }
            }

            if (driver != null) {
                driver.quit();
            }

            logger.info("GitHub, LinkedIn & Medium visits completed");

        } catch (Exception e) {
            logger.error("Failed in SEO visit method", e);
            Assert.fail("SEO automation failed: " + e.getMessage());
        }
    }








}
