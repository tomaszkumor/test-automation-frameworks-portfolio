# Java - Playwright - Test Automation Framework

## Overview
This repository contains a comprehensive test automation framework built in Java, designed for automated testing of:
- Web application (Playwright)
- REST API (Playwright)

This framework is intended as a **portfolio project** demonstrating practical knowledge of test automation architecture, design patterns, configuration management, and scalability considerations commonly used in real-world QA Automation projects.

## How to run

### Clone repository:
```bash
git clone https://github.com/tomaszkumor/test-automation-frameworks-portfolio.git
```
### Install dependencies:
```bash
cd test-automation-frameworks-portfolio
mvn clean install
```
### Run
#### API tests:

```bash
mvn -pl java-playwright-test-automation-framework test -Dsurefire.suiteXmlFiles=src/test/resources/testSuites/api/PET_STORE.xml -Denvironment=ENV1 -Dplatform=api -Dapi.debug=false
```

#### Web tests:

```bash
mvn -pl java-playwright-test-automation-framework test -Dsurefire.suiteXmlFiles=src/test/resources/testSuites/web/PHP_TRAVELS.xml -Denvironment=ENV1 -Dplatform=web -Dweb.engine=firefox -Dweb.remote=false -Dweb.debug=false -Dweb.headless=false
```

### Generate Allure report:
```bash
allure serve java-playwright-test-automation-framework/allure-results
```

## Technology stack

- **Language:** Java 21
- **Build Tool:** Maven 3.9.x
- **Test Framework:** TestNG
- **Web Automation:** Playwright 1.58.x
- **API Automation:** Playwright 1.58.x
- **Reporting:** Allure
- **Logging:** Log4j2
- **CI/CD readiness:** Yes

## Design patterns & architectural concepts

### The framework applies multiple design patterns:

- Fluent Page Object
- Page Factory
- Singleton (driver and configuration management)
- Builder (test data and request objects)

### General architectural considerations:
- Centralized driver management
- Centralized actions management
- Clear separation of test logic and page logic
- Reusable utilities and helpers
- Scalable structure for future extensions

### API testing architectural considerations:
- POJO-based serialization/deserialization
- Independent test execution
- Possible support for E2E-style API flows (e.g. create → validate → delete resource)

### Web testing architectural considerations:
- Chromium, Firefox and Webkit browser engines support
- Remote tests execution support
- Parallel execution support
- Headless mode support
- Separate web Page Object models

## Configuration management

The framework uses **YAML-based configuration files** for runtime control.

### Configuration capabilities include:
- Test type selection: `web`, `api`
- Environment selection (e.g. test / staging)
- Browser settings (headless, parallel execution)
- Environment-specific data (URLs, credentials)

No Maven or TestNG parameters are required — all runtime behavior is driven via configuration files.

## Project structure:

```
test-automation-frameworks-portfolio
 ├── java-selenium-test-automation-framework
 ├── java-playwright-test-automation-framework
 │    ├── allure-results  
 │    ├── src
 │    │    ├── main
 │    │    │     ├── java
 │    │    │     │     ├── actions
 │    │    │     │     ├── actionsFactory
 │    │    │     │     ├── config
 │    │    │     │     ├── constants
 │    │    │     │     ├── dataProviders
 │    │    │     │     ├── listeners
 │    │    │     │     ├── models
 │    │    │     │     │     ├── api
 │    │    │     │     │     └── web
 │    │    │     │     ├── playwrightFactory
 │    │    │     │     └── utils
 │    │    │     └── resources
 │    │    │           ├── api
 │    │    │           ├── filesPaths
 │    │    │           ├── settings
 │    │    │           ├── users
 │    │    │           └── BasicSettings.yaml
 │    │    └── test
 │    │          ├── java
 │    │          │     ├── baseTest
 │    │          │     └── tests
 │    │          │           ├── api
 │    │          │           └── web
 │    │          └── resources
 │    │                ├── testSuites
 │    │                │     ├── api
 │    │                │     └── web
 │    │                └── log4j2.xml
 │    ├── pom.xml (java-playwright-test-automation-framework module POM)
 │    └── README.md (java-playwright-test-automation-framework README file)
 ├── pom.xml (parent POM for multi-module project)
 └── README.md (main overview README file)
```

## Reporting & logging

- Allure Reports for detailed test results
- Log4j2 for structured logging
- Automatic retry mechanism for flaky tests
- TestNG listeners integrated

## Web tests independence

Web tests are fully independent.

## API tests independence

API tests may follow ordered execution when validating business flows
(e.g. resource creation and cleanup).

## Extensibility & future improvements

Possible extensions:
- Maven profiles for test selection
- CI pipeline integration (GitHub Actions / Jenkins)
- Enhanced API authentication support

## Disclaimer

Web tests in this framework were originally developed against an older version
of the PHPTravels demo website. Since then, the live demo site has undergone a complete
redesign, which changed whole page structure.

As a result, tests relying on the live PHPTravels site fail and not run as expected.

The purpose of this repository is to **demonstrate test automation architecture,
design patterns, configuration management, and best practices**, not to provide
fully passing tests against the current live PHPTravels site.

All configuration values, URLs, and credentials are mock or non-sensitive.