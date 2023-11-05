# AutomationExercise E-Commerce Automation Framework

## Overview
Automated testing framework for the AutomationExercise e-commerce 
application built with Selenium WebDriver, Java, TestNG and Maven.

## Framework Architecture
- **Design Pattern:** Page Object Model with PageFactory
- **Test Framework:** TestNG
- **Build Tool:** Maven
- **Reporting:** Extent Reports
- **Logging:** Log4j2
- **Grid:** Selenium Grid with Docker

## Tech Stack
| Tool | Version |
|------|---------|
| Java | 21 |
| Selenium | 4.41.0 |
| TestNG | 7.12.0 |
| Maven | 3.x |
| ExtentReports | 5.1.2 |
| Log4j2 | 2.25.4 |

## Project Structure
src/test/java/
├── pageObjects/     → Page classes (POM)
├── testBase/        → BaseClass and BasePage
├── testCases/       → Test classes
└── utilities/       → Reports, Excel reader, DataProviders

## Test Cases
| Test | Description | Group |
|------|-------------|-------|
| TC001 | User Registration | Regression |
| TC002 | User Login | Sanity, Regression |
| TC003 | Login Data Driven | Datadriven |
| TC004 | Verify All Products and Product Detail Page | Regression |
| TC005 | Search Product | Regression |
| TC006 | Verify Cart Page | Regression |
| TC007 | Register While Checkout | Regression |
| TC008 | Register Before Checkout | Regression |
| TC009 | Login While Checkout | Regression |

## Prerequisites
- Java 21
- Maven
- Chrome/Firefox/Edge browser
- Docker Desktop (for Grid execution)

## How to Run

### Run locally:
```bash
double click run_local.bat
```
or
```bash
mvn test
```
```

### Run on Docker Grid:
```bash
double click run_grid.bat
```
or
```bash
docker-compose up -d
mvn test
docker-compose down
```

### Run specific groups:
```bash
mvn test -Dgroups=Sanity
mvn test -Dgroups=Regression
mvn test -Dgroups=Datadriven
```

## Configuration
Update `test/resources/config.properties`:
```properties
execution_env=local   # change to remote for Grid
browser=chrome
os=windows
baseURL=https://automationexercise.com/
```

## Reports
- Extent Reports generated in `/reports` folder
- Auto opens in browser after test execution
- Screenshots automatically captured on failure

## CI/CD
- Jenkins pipeline configured for automated execution
- Docker Compose for Selenium Grid setup
- Supports parallel cross-browser execution