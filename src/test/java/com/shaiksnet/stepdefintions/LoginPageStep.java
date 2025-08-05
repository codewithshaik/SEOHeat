package com.shaiksnet.stepdefintions;

import com.shaiksnet.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginPageStep {


    private final LoginPage LoginPage = new LoginPage(Hooks.getDriver());

    @Then("the user logs into naukri.com")
    public void the_user_logs_into_naukri_com() {
        LoginPage.userLoginIntonaukri();
    }

    @And("the user login into flowCv")
    public void theUserLoginIntoFlowCv() {
        LoginPage.userLoginIntoFlowCv();
    }

    @Given("the user get the SEOHeat URls")
    public void theUserGetTheSEOHeatURls() {
    }
}
