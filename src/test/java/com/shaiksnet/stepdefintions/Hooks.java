package com.shaiksnet.stepdefintions;

import com.shaiksnet.utility.Util;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.openqa.selenium.devtools.v129.storage.Storage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Hooks {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    Random random = new Random();
    List<String> userAgents = Arrays.asList(
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64)",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X)",
            "Mozilla/5.0 (Linux; Android 10)"
    );

    List<String> proxies = Arrays.asList(
            "103.25.23.78:8080",
            "129.213.108.191:3128",
            "85.195.104.71:80"
            // Add more proxy IPs here
    );
    public String getRandomUserAgent() {
        return userAgents.get(random.nextInt(userAgents.size()));
    }

    public String getRandomProxy() {
        return proxies.get(random.nextInt(proxies.size()));
    }


    @Before
    public void setup(Scenario scenario) {
        String scenarioName = FilenameUtils.getBaseName(scenario.getUri().toString());
        String oldScenarioName = Util.getProperty("scenarioName");
        boolean headlessoption = Boolean.parseBoolean(Util.getProperty("HeadlessMode"));
        String currentTag = System.getProperty("cucumber.filter.tags");

        //proxy



        if (driver == null || !scenarioName.equals(oldScenarioName)) {
            if (driver != null) {
                driver.quit(); // Close the old session
                logger.info("Closed existing browser session");
            }

            // Setup new session
            logger.info("Starting new browser session for scenario: " + scenarioName);


            String host = Util.getProperty("Host");
            String driverPath = Util.getProperty("driverPath");
            String project = Util.getProperty("project");
            String os = System.getProperty("BrowserStack_os");
            String osVersion = System.getProperty("BrowserStack_osVersion");
            String browser = System.getProperty("BrowserStack_browser");
            String browserVersion = System.getProperty("BrowserStack_browserVersion");
            int defaultImplicitWait = Integer.parseInt(Util.getProperty("defaultimplicitywait"));

            if (host.equalsIgnoreCase("Local")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+driverPath);
                ChromeOptions options = new ChromeOptions();
                if(headlessoption){
                    options.addArguments("--headless=new");
                }

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("plugins.always_open_pdf_externally", true);
                driver = new ChromeDriver(options);
                if(currentTag.contains("git")) {
                    Proxy proxy = new Proxy();
                    proxy.setHttpProxy(getRandomProxy()).setSslProxy(getRandomProxy());
                    options.setProxy(proxy);
                }
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("user-agent=" + getRandomUserAgent());


            } else if (host.equalsIgnoreCase("BrowserStack")) {
                ChromeOptions options = new ChromeOptions();
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browserstack.debug", "false");
                caps.setCapability("browserstack.seleniumLogs", "false");
                caps.setCapability("browserstack.video", "true");
                caps.setCapability("browserstack.maskCommands", "true");
                caps.setCapability("browserstack.local", "true");
                caps.setCapability("browserstack.localIdentifier", "Local");
                caps.setCapability("browserstack.user", Util.getUserName());
                caps.setCapability("browserstack.key", Util.getAccessKey());
                caps.setCapability("build", project);
//                caps.setCapability("os", os);
//                caps.setCapability("os_version", osVersion);
//                caps.setCapability("browser", browser);
//                caps.setCapability("browser_version", browserVersion);
                caps.setCapability("idleTimeout", "12000");
                caps.setCapability("name", scenarioName);
                driver = new ChromeDriver(options);
            }

            // Set default implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofDays(defaultImplicitWait));
            driver.manage().window().maximize();
            logger.info("Browser setup completed");
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser session terminated");
        }
    }




    public static WebDriver getDriver() {
        return driver;
    }
}
