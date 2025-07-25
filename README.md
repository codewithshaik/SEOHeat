<h1 align="center">🚀 ApplyMate - Automating Naukri Job Applications with AI & Jenkins</h1>

<p align="center">
  <img src="https://media.giphy.com/media/UQYRxVkF0qRPVJzpV0/giphy.gif" width="250" alt="Automation GIF">
</p>

---

## 💡 What is ApplyMate?

**ApplyMate** is a smart automation framework that uses **Java**, **Selenium**, **Cucumber**, and **Maven** to apply for jobs on **Naukri.com** — fully automated!

It supports login via **Gmail or OTP**, executes tests on **local browsers or Selenium Grid**, and integrates with **Jenkins** for full CI/CD execution — complete with rich HTML reports and email notifications 📩.

---

## 🔧 Tech Stack

| Tool         | Description                                 |
|--------------|---------------------------------------------|
| 🧑‍💻 Java       | Language used for framework logic           |
| 🔨 Maven      | Build tool for dependency and test execution|
| 🧪 Cucumber   | BDD Framework for test scenarios            |
| 🧪 Selenium   | UI Automation engine                        |
| 🧪 JUnit      | Test execution and assertion                |
| ☁️ Jenkins    | CI/CD orchestration                         |
| 📊 HTML Reports | Visual output for test result analysis    |

---

## 🌐 Key Features

- ✅ Gmail or OTP login to **Naukri**
- ✅ Run tests locally or on **Selenium Grid**
- ✅ Fully **parameterized Jenkins pipeline**
- ✅ Execute **specific Cucumber tags**
- ✅ **Headless** or UI browser execution
- ✅ Auto-generated HTML reports 📊
- ✅ Email notifications with execution summary 📨

---

## 📁 Project Structure

ApplyMate/
├── Jenkinsfile
├── pom.xml
├── src/
│ ├── main/java/
│ │ ├── core/
│ │ ├── pages/
│ │ └── utils/
│ └── test/java/
│ ├── stepdefinitions/
│ └── runners/
├── target/ (generated reports)
└── README.md

## 🧪 Sample Maven Command (Local)

```bash
mvn clean install \
  -Denvironment=local \
  -DloginType=gmail \
  -DnaukriGmail=youremail@gmail.com \
  -DgmailAppPassword=yourAppPassword \
  -DotpNumber=9876543210 \
  -DNaukriPasswordBase64=encodedPwd \
  -DHeadlessMode=true \
  -Dcucumber.filter.tags="@smoke"
