@Naukri
Feature: Naukri Apply mate
  I want to use this template for naukri daily login and resume

  Background:
    Given the user logs into naukri.com


  Scenario: updating naukri profile with keywords, job application and resume
    When the user update naukri keywords
      | Resume Headline | key Skills  |
      | Karate          | API Testing |
      | CI/CD           | Maven       |
      | MongoDB         | GitHub      |
      | UI Automation   | POM         |

    When the user updates naukri job search
      | jobSearch     |
      | software jobs |

    And the user login into flowCv
      | imoxygod@gmail.com |
      | $password          |

    And user update the resume and download
    Then user uploads the resume to naukri profile
    Then the user should see the updated naukri profile

