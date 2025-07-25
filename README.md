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


🔁 Jenkins Pipeline Breakdown

🔧 Tool Configuration

groovy
tools {
  maven 'maven'
  jdk 'jdk22'
}
🧠 Dynamic Setup Example

groovy
stage('Setup Domain') {
  steps {
    script {
      if (env.GIT_URL.contains('Mate')) {
        env.DOMAIN = "IS-${params.TAGNAME ?: ''}"
      }
    }
  }
}
🚀 Pipeline Stages
Stage	Purpose
🛠 Setup Domain	Configure environment dynamically
📥 Checkout Code	Clean clone GitHub repo with credentials
🧪 Start Tests	Executes test locally or Grid based on HOST
📊 Generate Reports	Publishes overview-features.html + fallback
📩 Email Notification	Sends execution summary + build logs via email

📬 Email Alert Format
🧾 Sample Email Body

html
<h2>ApplyMate Execution Summary</h2>
<table>
  <tr><td><b>Status:</b></td><td>SUCCESS</td></tr>
  <tr><td><b>Tag Run:</b></td><td>@regression</td></tr>
  <tr><td><b>Executed By:</b></td><td>youremail@gmail.com</td></tr>
</table>
<a href="${BUILD_URL}artifact/summary_report.html">📄 View HTML Report</a>


📎 Email includes:

✅ Build logs (attached + zipped)

✅ HTML report link

✅ Summary Table of key params

📸 Screenshots & Report Output
📷 Add your screenshots here like:

scss
![Dashboard](assets/dashboard.png)
![HTML Report](assets/cucumber-report.png)
🤖 Example Use Cases
Automating job submissions on Naukri

Daily scheduled resume updates

Recruiter workflow automation

Resume A/B testing via AI

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



