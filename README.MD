# Simple Rest Service

## Prerequisites
Java 11 

For example, Oracle JDK:
https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html

## Installation
### Clone project into folder using command in terminal
<code>git clone https://github.com/Jevgenijs92/simple-rest-service.git </code>

### Adjust allowed origin in application.properties file to match REST client
#### port 4200 is default port for Angular projects
<code>cors.allowed.origin=http://localhost:4200 </code>

### To start the server, navigate to project folder and run command
<code>./mvnw spring-boot:run </code>

### You can now access local server, for example at:
http://localhost:8080/api-docs

#### For more info on the Client part check my other project simple-rest-client
https://github.com/Jevgenijs92/simple-rest-client





