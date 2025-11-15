# QA Task — Swagger API & Freshnesecom E2E Tests

This repository contains **two separate automated testing projects**:

1. **Swagger Notes API tests**  
   - API tests for a Swagger-based Notes service covering:
    User registration & login
    Auth token handling
    CRUD operations on Notes
   - Implemented using **Java + Rest Assured + TestNG + Allure**.


   Tech stack:
    - **Java**
    - **Rest Assured**
    - **TestNG**
    - **Faker**
    - **Dotenv**
    - **Allure Reporting**


2. **Freshnesecom E2E tests**  
   - UI + API end-to-end tests for the Freshnesecom storefront, Includes:
    Add Product flow
    Product Search
    Scrolling & Assertions

   - Implemented using **Java + Selenium WebDriver + TestNG + Allure**.
   
   Tech stack:
    - **Java**
    - **Selenium WebDriver**
    - **TestNG**
    - **Dotenv**
    - **Java Faker**
    - **Allure Reporting**



Both projects share the same Gradle/TestNG setup but live in **separate packages** and can be run independently.

---

## 📁 Project Structure

src/
    ├── main/
    
    │   ├── java/
    
    │   │   ├── api/                      # API client layer and Notes & Users API 
    
    │   │   ├── e2ePages/                 # Page Objects for Freshnesecom
    
    │   │   
    
    ├── test/
    │   ├── java/
    │   │   ├── base/                     # BaseTest, shared setup
    │   │   └── tests/
    │   │       ├── apiTests/             # Swagger Notes API tests (NotesE2ETests, Users tests)
    │   │       └── e2eTests/             # Freshnesecom UI tests (AddProductsTest, SearchProductsTest)
    │   └── resources/
    │       └── testdata/                 # Images, JSON payloads, etc. (e.g., dummy.jpg)
    .env                                  # Environment variables (URLs, creds, tokens)

---
# Getting Started
1. Clone the Repository
    git clone https://github.com/Toka-Mostafa/QA-Task-UI-API-Tests.git
    cd <this-repo>

2. Requirements
    Java 17
    Gradle
    TestNG
    Rest Assured
    Chrome / ChromeDriver
    Allure CLI if you want to generate reports locally

3. Configure Environment
    Create a .env file in the project root (if not already present) and Copy values from env_example.

4. Running Tests
    Run all tests (Swagger + Freshnesecom) : ./gradlew clean test
    Run only Swagger Notes API tests: ./gradlew test --tests "tests.apiTests.*"
    Run only Freshnesecom UI tests: ./gradlew test --tests "tests.e2eTests.*"

---
# Allure Reporting

Allure results are saved to: build/allure-results/

---
# GitHub Actions (GRADLE)

This repo contains a workflow that:
    Sets up Java
    Runs Gradle tests
    Generates Allure report
    Publishes report to GitHub Pages
    Workflow: .github/workflows/tests.yml

---
# Summary
This repository demonstrates:
    Strong separation between UI & API automation
    Page Object Model (POM)
    Gradle-based Java automation
    Complete CI integration with Allure reporting
