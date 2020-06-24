# "Vivy Mobile & API Automation"
* This repository contains tests for Mobile and API validations

### Project Description
* Written in Appium, Java, TestNG & Maven

### Setup
* Install [Appium Server]
* Install [Java 8]
* Install [Maven]

### Package Details
* core (src/main/java): It has 2 base classes. One for Mobile to perform a core Android execution and another for API
* pageObjects (src/main/java): To store page objects with the aligned functionalities
* apiTests (src/test/java): List of API tests
* mobileTests (src/test/java): List of mobile tests
* resources : To store global properties 

### Mobile Tests
* Verification of invalid Vivy login
* Verification of valid Vivy login
### API Tests
* Fetch Doctors and Subsequent-Doctors endpoint to make sure no duplicates

### Xml files
* testngAPI.xml
* testngMobile.xml

###Instructions / Inputs
* Please provide details such as deviceName and valid credentials in the global.properties file
* If the execution is on a real device where SMS generates the program will enter the SMS. If its emulator you have 7 seconds of static sleep to enter it manually

### Run tests: Mobile, API
* Execution gets triggered with the help of profiles in pom.xml (<id>Mobile</id> & <id>API</id>)
* `mvn test -PMobile`
* `mvn test -PAPI`


### Approach in solving
Using TestNG makes tests to organise and execute much easier way
TestNg uses xml files to trigger tests which can be called through pom.xml
