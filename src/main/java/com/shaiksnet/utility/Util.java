package com.shaiksnet.utility;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static com.shaiksnet.utility.Global_Variables.*;

public class Util {

    public static String getProperty(String key) {
        String result = null;
        try{
            String filePath = System.getProperty("user.dir")+"/src/test/resources/cucumber.properties";
            Properties property = new Properties();
            FileInputStream file = new FileInputStream(filePath);
            property.load(file);
            result = property.getProperty(key); // Directly returns a String or null
        } catch (IOException e) {
            // Logging the exception with a more detailed message
            System.err.println("An IOException occurred while retrieving the property: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public static String getUserName() {
        return getProperty("browserstack.user");
    }

    public static String getAccessKey() {
        return getProperty("browserstack.key");
    }

    // Sets an implicit wait time for the given WebDriver instance.
    public static void implicitWait(WebDriver driver) {
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Util.getProperty(psLbl_defaultimplicitywait))));
        } else {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
    }

    //Switches to a window based on the given index.
    public static void switchToWindow(WebDriver driver, String index) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }

        try {
            // Get all window handles
            java.util.Set<String> windowHandles = driver.getWindowHandles();

            // Convert the set to a list for index-based access
            java.util.List<String> handlesList = new java.util.ArrayList<>(windowHandles);

            // Check if the index is within range
            int windowIndex = Integer.parseInt(index);
            if (windowIndex < 0 || windowIndex >= handlesList.size()) {
                throw new IllegalArgumentException("Window index out of range");
            }

            // Switch to the window at the specified index
            driver.switchTo().window(handlesList.get(windowIndex));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid window index format. Must be an integer.", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to switch to the specified window", e);
        }
    }

    //Retrieves the XPath for a given class name and key.
    public static String getXpath(final String pageName ,final String elementLocator) throws IOException {
        Properties property = new Properties();
        String pagePropertyFile = pageName+".properties";
        InputStream input = Util.class.getClassLoader().getResourceAsStream(pagePropertyFile);

        if (input != null) {
            property.load(input);
        }else {
            throw new FileNotFoundException(pagePropertyFile+"not found in the class path");
        }
            String xpath = property.getProperty(elementLocator);
        return xpath;
    }
    public static void waitForPageToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}
