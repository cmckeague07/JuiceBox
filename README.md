JuiceBox Automation Framework  
**A full-stack Serenity BDD automation suite covering functional, API, security, and performance testing for OWASP Juice Shop.**  

![CI Status](https://github.com/cmckeague07/JuiceBox/actions/workflows/ci.yml/badge.svg)

---

### Overview
JuiceBox is a complete end-to-end **Java-based automation framework** built around the [OWASP Juice Shop](https://owasp.org/www-project-juice-shop/) vulnerable web app.  
It simulates the structure of an enterprise-grade QA regression suite, integrating UI, API, and non-functional testing under a single reporting and execution pipeline.

The project was designed as a personal showcase and learning framework to demonstrate:
- Scalable automation design using **Serenity BDD + Cucumber + JUnit + Selenium + RestAssured**
- A full test lifecycle: **functional**, **security**, **resilience**, **accessibility**, and **performance**
- Clear reporting and reproducibility through Serenity and Lighthouse audits  

---

###  Architecture Overview
```
JuiceBox-Automation/
│
├── src/test/java/
│   ├── actions/                 # Encapsulated UI + API interaction logic
│   ├── stepdefinitions/         # Cucumber step bindings
│   ├── runners/                 # Serenity test runners
│
├── src/test/resources/features/ # Feature files grouped by domain
│
├── reports/
│   ├── serenity/                # Serenity HTML dashboard
│   └── lighthouse/              # Lighthouse performance & accessibility audits
│
├── pom.xml                      # Maven build configuration
├── serenity.properties           # Serenity configuration and browser setup
├── Juicebox Startup Steps.txt    # Local setup guide for running tests
│
└── scripts/
    └── run_lighthouse.bat             # CLI shortcut for Lighthouse audits
```

---

| Category                        | Description                                                             |
| ------------------------------- | ------------------------------------------------------------------------|
|  **Functional Testing**       | Login, basket, checkout, and form validation flows                        |
|  **API Testing**              | Basket add/remove validation using RestAssured                            |
|  **Security Testing**         | CSP, X-Frame-Options, authentication redirects, and input sanitization    |
|  **Resilience Testing**       | Basket persistence after refresh and validation handling                  |
|  **Accessibility Testing**    | Lighthouse CLI integration for accessibility scoring                      |
|  **Performance Testing**      | Lighthouse audits for LCP, FCP, TBT, and CLS metrics                      |
|  **Reporting**                | Serenity BDD dashboard + Lighthouse JSON/HTML reports                     |
|  **Scalable Design**          | Modular class structure (26+ Java classes) with reusability and isolation |


---

## CI/CD Pipeline
JuiceBox is integrated with **GitHub Actions** for continuous integration.

On every push to `master`, the pipeline automatically:
- Spins up a fresh OWASP Juice Shop instance via Docker
- Registers the test user via the Juice Shop API
- Executes the full Serenity BDD suite in headless Chrome


**17 scenarios total | 13 passing in CI | 4 documented known defects**

### Known Defects (tagged `@knownDefect`, excluded from CI)

| Scenario | Reason |
|----------|--------|
| Redirect unauthenticated user to login page | Juice Shop returns a 403 page instead of redirecting to `/#/login` |
| Missing Content-Security-Policy header | Juice Shop does not serve a CSP header by default |
| XSS input not properly escaped | Search field blocks script execution but does not HTML-encode the input |
| Basket contents persist after page refresh | Basket resets on refresh — identified as a Juice Shop application bug |

These scenarios remain in the suite as live defect records and are fully documented. They are excluded from the CI run via Cucumber tag filtering (`@All and not @knownDefect`) rather than deleted, preserving them as evidence of identified issues.

### How to Run

####  Prerequisites
- Java 17 or higher  
- Maven 3.9+  
- Node.js (for running Juice Shop locally)

### Setting Up OWASP Juice Shop Locally
Before running the automation suite, you’ll need to host the [OWASP Juice Shop](https://owasp.org/www-project-juice-shop/) application locally.

####  Install Node.js
Download and install Node.js (LTS version recommended) from:
 [https://nodejs.org/](https://nodejs.org/)

Confirm installation:
node -v
npm -v

####  Download OWASP Juice Shop

Clone or download the Juice Shop repository:
git clone https://github.com/juice-shop/juice-shop.git
cd juice-shop

####  Install and Run

Install dependencies and start the app:
npm install
npm start

The application will launch at:
http://localhost:3000/

You can now execute all functional and performance tests against this local instance.

####  Run Serenity Tests
mvn clean verify
```
The Serenity HTML report will be generated under:
```
target/site/serenity/index.html
```

#### Run Lighthouse Accessibility & Performance Audit
run_lighthouse.bat
```
Outputs:
```
reports/lighthouse/lighthouse-report.html
reports/lighthouse/lighthouse-report.json
```

---

###  Example Reports

**Serenity Dashboard (Functional + API + Security)**
<img width="1906" height="935" alt="image" src="https://github.com/user-attachments/assets/054f5caa-21e5-4676-89fa-f13ec93da47e" />


**Lighthouse Accessibility & Performance Report**

<img width="872" height="744" alt="image" src="https://github.com/user-attachments/assets/1bac224e-a06c-4dc8-bfc6-989ce5db772c" />


---

###  Highlights & Learnings
This project was built over six months (May–Nov 2025) with CI/CD integration added in March 2026, it covers the full QA lifecycle including:
- Framework design & abstraction principles  
- Synchronization handling and shared state logic  
- Serenity BDD reporting customization  
- Integration of accessibility and performance auditing into automation scope  
- Identification of real OWASP Juice Shop bug (basket persistence resets post-refresh)


---

###  Tech Stack
- **Language:** Java 17  
- **Frameworks:** Serenity BDD, Cucumber, JUnit  
- **UI:** Selenium WebDriver  
- **API:** RestAssured  
- **Performance & Accessibility:** Lighthouse CLI  
- **Build Tool:** Maven  
- **Reporting:** Serenity BDD HTML, Lighthouse JSON/HTML

---

### Version
**v3.0 – CI/CD Integration Complete**  
Released: *March 2026*

---

###  Author
**Conor McKeague**  
Senior Software Quality Engineer |
Contact via LinkedIn | 
https://www.linkedin.com/in/conor-mckeague-646310131/

