@NaukriLogin
Feature: Naukri Apply mate
  I want to use this template for naukri daily login and resume

  Background:
    #Given the user logs into naukri.com

 @Naukri
  Scenario: updating naukri profile with keywords, job application and resume
    When the user apply for jobs in naukri
    When the user update naukri keywords
    And the user login into flowCv
    And user update the resume and download
    And user uploads the resume to naukri profile
    Then the user should see the updated naukri profile

    @Git
    Scenario: visit git hub and linkedIn
    Then the user visit the git hub and linkedIn

