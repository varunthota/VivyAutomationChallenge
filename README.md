# "Vivy Mobile & API Automation"
* This repository contains tests for Mobile and API validations

### Project Description
* Mobile Automation is done using Appium, Java & TestNG
* API Automation is done using Rest Assured, Java & TestNG

### Approach in solving
My initial approach started with exploring the App manually to list out the challenges in the flow
* SMS Validation is the on challenging part where I've accessed the notifications to grab the pin

### Setup
* Install [Appium Server]
* Install [Java 8]
* Install [Maven]

### Package Details
* core (src/main/java): It has 2 base classes. One for Mobile to perform Android execution and another base class for API
* pageObjects (src/main/java): To store page objects with the aligned functionalities
* apiTests (src/test/java): List of API tests
* mobileTests (src/test/java): List of mobile tests
* resources : To store global properties 

### Mobile Tests
* Verification of invalid Vivy login
* Verification of valid Vivy login
### API Tests
* Fetch Doctors and Subsequent-Doctors endpoint to make sure no duplicates

###Instructions / Inputs
* Please provide details such as deviceName and valid credentials in the global.properties file
* If the execution is on a real device where SMS generates the program will enter the SMS. If its emulator you have 7 seconds of static sleep to enter it manually

### Test Execution Xml files with respective Test class details
* testngAPI.xml
* testngMobile.xml

### Run tests: Mobile, API
* Execution gets triggered with the help of profiles provided in pom.xml
Profile Names : Mobile & API
* `mvn test -PMobile`
* `mvn test -PAPI`
