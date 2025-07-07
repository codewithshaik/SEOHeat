package com.shaiksnet.stepdefintions;

import com.shaiksnet.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageStep {


    private final LoginPage LoginPage = new LoginPage(Hooks.getDriver());

    @Then("the user logs into US Check visa slots with valid credentials")
    public void the_user_logs_into_US_Check_visa_slots_with_valid_credentials(){
        LoginPage.userLoginIntonaukri();
    }

    @Then("the user logs into naukri.com")
    public void the_user_logs_into_naukri_com(){
        LoginPage.userLoginIntonaukri();
    }

}
