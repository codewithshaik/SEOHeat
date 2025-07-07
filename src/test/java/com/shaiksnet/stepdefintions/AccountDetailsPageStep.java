package com.shaiksnet.stepdefintions;

import com.shaiksnet.pages.AccountDetailsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

public class AccountDetailsPageStep {
    private final AccountDetailsPage accountDetailsPage = new AccountDetailsPage(Hooks.getDriver());


    @When("the user update naukri keywords")
    public void theUserUpdateNaukriKeywords(DataTable dataTable) {
        AccountDetailsPage.theUserUpdateNaukriKeywords(dataTable);
    }

    @When("the user updates naukri resume")
    public void theUserUpdatesNaukriResume(DataTable dataTable) {

    }

    @When("the user updates naukri profile")
    public void theUserUpdatesNaukriProfile(DataTable dataTable) {

    }

    @When("the user updates naukri job preferences")
    public void theUserUpdatesNaukriJobPreferences() {

    }

    @When("the user updates naukri job alerts")
    public void theUserUpdatesNaukriJobAlerts() {

    }

    @When("the user updates naukri job applications")
    public void theUserUpdatesNaukriJobApplications() {

    }

    @When("the user updates naukri job search")
    public void theUserUpdatesNaukriJobSearch(DataTable dataTable) {

    }

    @Then("the user should see the updated naukri profile")
    public void theUserShouldSeeTheUpdatedNaukriProfile() {
    }

    @And("user update the resume and download")
    public void userUpdateTheResumeAndDownload() {
        
    }

    @Then("user uploads the resume to naukri profile")
    public void userUploadsTheResumeToNaukriProfile() {
    }
}
