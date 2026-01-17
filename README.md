# LibreNMS Automation Test POC (Katalon Studio)
 
## Candidate
Nguyen Thi Thuy Ngan
 
## Application Under Test
https://demo.librenms.org  
Credentials: demo / demouser
 
---
 
## 1. Test Strategy
 
This Proof of Concept focuses on solving three main automation challenges:
 
1. Handling dynamic data in tables without relying on static indexes
2. Validating visual integrity of dashboard components beyond DOM existence
3. Injecting dynamic test data from external APIs to avoid hardcoded values
 
The Overview Dashboard and Devices page were chosen because they represent
data-intensive and frequently changing UI areas.
 
---
 
## 2. Test Cases Overview
 
### TC01 – Dynamic Device Table Validation
- Navigate to Devices page
- Dynamically locate a device by OS keyword
- Validate data without relying on fixed row/column indexes
 
### TC02 – Dashboard Visual Integrity Validation
- Navigate to Overview dashboard
- Validate dashboard loads real content
- Detect broken or empty visual components
 
### TC03 – Hybrid Data Injection (API Driven)
- Fetch dynamic data from public API
- Inject data into LibreNMS UI
- Validate system behavior using non-hardcoded data
 
---
 
## 3. Framework Design
 
- Tool: Katalon Studio
- Pattern: Independent test case execution
- Data Handling: API-based and runtime-driven
- Assertions: Custom assertions to reduce flaky failures
 
---
 
## 4. Test Execution Evidence
 
Execution reports and screenshots are available in the following folder:

Reports/

Example evidence file:

- evidence_test_suite_pass.png
 
---
 
## 5. How to Run
 
1. Open the project in Katalon Studio

2. Run `TS_LibreNMS_POC` or any individual test case

3. Review execution results and screenshots in the `Reports/` folder
 