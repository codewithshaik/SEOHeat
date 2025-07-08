package com.shaiksnet.pages;

import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.jsoup.helper.Validate.fail;
import com.shaiksnet.utility.Util;
import org.openqa.selenium.WebElement;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage {
    private final WebDriver driver;
    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(){

        try{
            logger.info("In OpenUrl");
            String url = Util.getProperty("loginUrl");
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
    private static String getTextFromMessage(Object content) throws Exception {
        if (content instanceof String) {
            return (String) content;
        } else if (content instanceof MimeMultipart) {
            MimeMultipart mimeMultipart = (MimeMultipart) content;
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                // You can parse HTML content here if needed
                String html = (String) bodyPart.getContent();
                result.append(html.replaceAll("\\<.*?\\>", "")); // strip HTML tags
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }

        return result.toString();
    }
    public String gmailOtpReader() throws Exception {
        String host = "imap.gmail.com";
        String username = "shaikmahaboobsubhani0777@gmail.com";
        String password = "lftp tyqq uhzn djvj"; // NOT your Gmail password!


        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props);
        Store store = session.getStore("imaps");
        store.connect(host, username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Get messages sorted newest to oldest
        Message[] messages = inbox.getMessages();

        String otp = null;
        for (int i = messages.length - 1; i >= 0; i--) {
            Message message = messages[i];
            String subject = message.getSubject();

            // Subject filter - change as needed
            if (subject != null && subject.toLowerCase().contains("otp") && subject.toLowerCase().contains("naukri")) {
                String emailBody = getTextFromMessage(message.getContent());
                //System.out.println("Email Content:\n" + emailBody);

                // Regex to match only the first 6-digit OTP
                Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
                Matcher matcher = pattern.matcher(emailBody);

                if (matcher.find()) {
                    otp = matcher.group();
                    System.out.println("Latest OTP: " + otp);
                    break; // Only first/latest OTP mail
                } else {
                    System.out.println("OTP not found in email body.");
                }
            }
        }

        inbox.close(false);
        store.close();
        return otp;

    }


    public void userLoginIntonaukri( ) {
        try{
            logger.info("userLoginIntonaukri started");
            String loginType = Util.getProperty("loginType");
            openUrl();
            Util.implicitWait(driver);
            if(!driver.getCurrentUrl().contains("homepage")){
                WebElement loginBtn = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"loginButton")));
                loginBtn.click();
                Thread.sleep(1000);

                if(loginType.equalsIgnoreCase("mail")|| loginType.equalsIgnoreCase("gmail")){
                    String encodedPassword = Util.getProperty("NaukriPasswordBase64");
                    System.out.println("Encoded password: " + encodedPassword);
                    byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
                    String decodedString = new String(decodedBytes);
                    System.out.println("Decoded string: " + decodedString);
                    Util.implicitWait(driver);

                    WebElement userName = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"username")));
                    WebElement password = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"password")));
                    userName.sendKeys(Util.getProperty("naukriGmail"));
                    password.sendKeys(decodedString);

                    WebElement submitBtn = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"logonBtn")));
                    submitBtn.click();
                } else if (loginType.equalsIgnoreCase("otp")) {
                    WebElement otpLogin = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"otpLoginBtn")));
                    otpLogin.click();
                    Util.implicitWait(driver);
                    WebElement otpPhoneNumber = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"mobNumberInput")));
                    otpPhoneNumber.sendKeys(Util.getProperty("otpNumber"));
                    Util.implicitWait(driver);
                    WebElement getOtpBtn = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"getOtpBtn")));
                    getOtpBtn.click();
                    Thread.sleep(5000);
                    gmailOtpReader();// Wait for OTP to be received
                    String otp = gmailOtpReader();
                    System.out.println("OTP received: " + otp);
                    WebElement otpInput = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"otpInput")));
                    otpInput.sendKeys(otp);

                }else{
                    fail("please provide Naukri login type in OTP or gmail only");
                }
                {

                }


                WebElement submitBtn = driver.findElement(By.xpath(Util.getXpath(getClass().getSimpleName(),"logonBtn")));
                submitBtn.click();

            }else {
                System.out.println("user already logged in");
            }


            logger.info("userLoginIntonaukri comple");
        }catch (Exception e){
            logger.error("Step: userLoginIntonaukri failed");
        }





    }
    public class employee {

    }
}
