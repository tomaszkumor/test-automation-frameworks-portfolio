# Test Automation Frameworks Portfolio

## Overview
This repository is a **portfolio of Java-based test automation frameworks**, demonstrating practical knowledge of automated testing architecture, design patterns, configuration management, and scalability considerations commonly used in real-world QA projects.

The project contains multiple test automation frameworks, each implemented as a **separate Maven module**, focusing on different automation technologies and testing types (web, mobile, API).

## Modules

### [Java Selenium Test Automation Framework](java-selenium-test-automation-framework/README.md)  
- Test automation for:
  - web application with Selenium
  - mobile application with Appium
  - REST API with RestAssured

### [Java Playwright Test Automation Framework](java-playwright-test-automation-framework/README.md)
- Test automation for:
  - web application with Playwright
  - REST API with Playwright

## Purpose
The goal of this portfolio is to demonstrate skills in QA automation:

- Designing and implementing scalable test frameworks
- Applying design patterns (Fluent Page Object Model, Singleton, Builder, etc.)
- Managing configurations and environments
- Writing reusable and maintainable test code
- Demonstrating cross-platform automation (web, mobile, API)

## Tested applications

Frameworks cover testing of:

- web application: PHPTravels
- REST API: PetStore
- mobile application: Wiki Alpha (read-only mode)

> Note: All applications are either demo, sandbox, or used in a safe/read-only mode. All configuration values, URLs, and credentials are mock or non-sensitive. No destructive operations are performed against production systems.

## Project structure

```
test-automation-frameworks-portfolio
 ├── java-playwright-test-automation-framework
 ├── java-selenium-test-automation-framework
 ├── pom.xml (parent POM for multi-module project)
 └── README.md (main overview README file)
```

- Each module has its **own README** file with details on:
    - How to run tests
    - Technology stack
    - Module structure
    - Reporting and logging
    - Configuration management

- The **parent POM** handles multi-module build and dependency management.

## Future modules

- macOS desktop application testing framework based on Appium
- BDD-style test automation framework based on Cucumber
- Possible CI/CD integration for automated reporting and builds

## Contact

- Author: Tomasz Kumor
- LinkedIn: [link](https://www.linkedin.com/in/tomasz-kumor-0bb9bb1aa/)

---

*This portfolio is continuously evolving as new frameworks are added.*
