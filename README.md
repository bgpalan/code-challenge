# Introduction 
This is the name migration microservice project . This project is a spring boot application that exposes an endpoint 
that migrates the name to the following JSON format ```{
"salutation": null,
"firstname": "Frida",
"middlename": null,
"lastname": "Kahlo",
"suffix": null}```
### General info 
* Programming language used : Java 
* Framework used : Spring boot 

### Assumptions 
* Names are very tricky (It is different in different cultures ) and a perfect solution is not possible :) 
* The Supported language is only English 
* The Salutation is optional and I have assumed that the salutation should have minimum of 2 chars and max of 4 . Possible examples are Mr. / Miss. , It is Quite possible to have spaces before or after the dot
* Suffix optional and is at the end of the name starting with a comma and ending in a dot. Comma can start or end with space and there could be a space before or after the dot
* Name forms the middle of the string , Name is mandatory might have middle name as well as last name. I have assumed that the first name should have a min length of 5 characters
* The name is of the format ```Salutation. Firstname middleName Lastname , Suffix.```. Here Salutation, middleName, lastName and suffix are optional . Any name that does not match this is deemed as invalid 

## Setup

### Required Softwares 
This project requires the following software installed in the computer 
* JDK(> 1.8) 
* Apache maven 3
* git commandline client (for cloning the github project ) 

### Setup Instrctuctions 
* Ensure that you Dont have any local server running in the port 8080
* Clone the project repo ```git clone https://github.com/bgpalan/code-challenge.git```
* Once successfully cloned go to the ```code-challange``` directory simply type ```mvn clean install```
* This will compile the project , run all the unit test cases and produce the final jar 
* The jar will be located in the target folder (found under the ```code-challange/target/migration-service.jar```
* Start the application by typing ```java -jar migration-service.jar``` . This will start the application as a server listening to the port 8080.

### Using the service 
* Assuming that you have run the service locally , The service exposes the endpoint ```http://localhost:8080/api/v1/migrate```. This endpoint accepts only GET Requests
* This endpoint accepts a query param fullName. For example to migrate a name ```Rev. Martin Luther King,Jr.``` Open a browser and paste ```http://localhost:8080/api/v1/migrate?fullName=Rev. Martin Luther King,Jr.```
* You should see an output like this ```{"salutation":"Rev.","firstname":"Martin","middlename":"Luther","lastname":"King","suffix":"Jr."}```

### Test cases 
* This project is a webservice project , Hence I thought that it is a better approach to use unit test cases covering all the scenarios
* These cases are run automatically when you type mvn clean install . The Build will fail when the case fails 
* The test cases are located under src/test/java/MigrationServiceTest (There are about 11 cases) 

