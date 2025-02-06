# MICROFRAMEWORKS

For this project we made a dinamic HTTP server implementation using technologies like Java, JavaScript, CSS, HTML and REST request without using frameworks like Spring Boot, just a simple server. All of this divided by directories.



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

![](/images/1.1.png)
![](/images/1.2.png)
![](/images/1.3.png)
![](/images/1.4.png)
![](/images/2.1.png)
![](/images/2.2.png)
![](/images/2.3.png)
![](/images/2.4.png)

### Prerequisites

You need to install the following tools to run the project:
1. Java

   Try using the following command
    ```
    java -version
    ```
    You should see something like this:

    ![](/images/3.png)

2. Maven

   We need maven too, so use the following command.

    ```
    mvn -version
    ```
    You should see something like this:
    
    ![](/images/4.png)

3. Git

   To finish, we must have git. You can check it with this command.
    ```
    git --version
    ```
    It should appear something like this:
    
    ![](/images/5.png)

### Installing

1. First, we need to open a terminal and put the following command to clone the project:

    ```
    git clone https://github.com/JeissonCasallas09/AREP-02-MICROFRAMEWORKS
    ```
2. Open the folder with the project in a new terminal and build it with the following command:
    ```
    mvn package
    ```
    This message will tell you that it was successfull:

     ![](/images/6.png)

3. Now just run the project using:
    ```
    mvn exec:java
    ```
    The project is now running:

    ![](/images/7.png)
    
4. Enter from your browser to the local server with port 8080

 ![](/images/8.png)


## Architecture


#### Overview
The architecture of this project is structured into three main components: WebServer, Application, and Static Files. Each of these plays a specific role in handling HTTP requests, processing responses, and serving content.

![](/images/10.png)
![](/images/11.png)

#### 1. Static Files
This section includes resources that do not require server-side processing:

* Styles: CSS files for defining the visual appearance of the application.
* Script: JavaScript files that add client-side interactivity.
* Index: The main HTML page that serves as the entry point for users.

#### 2. WebServer
This module is responsible for handling HTTP requests and responses. It includes the following key components:

* HttpServer: The core server that processes incoming requests and routes them to the appropriate handlers.
* HttpRequest: Represents an HTTP request, including the requested URL and any parameters sent.
* HttpResponse: Handles the response that will be returned to the client, including headers and content.


#### 3. Application
This module represents the backend logic of the web application. It interacts with the WebServer to process application-specific logic and return appropriate responses.

#### Communication Flow

1. A client sends an HTTP request (e.g., accessing a webpage or an API endpoint).
2. The HttpServer receives the request and forwards it to the corresponding handler.
3. If it is an API request, the WebApplication processes the logic and returns a response.
4. If it is a request for static content (HTML, CSS, JS), the HttpServer retrieves the file and sends it back.
5. The HttpResponse constructs the HTTP response and sends it to the client.

This modular design allows flexibility in handling dynamic API requests while efficiently serving static content.

## Running the tests

The project includes unit tests to validate the functionality of the HTTP server, ensuring correct behavior when handling static files, REST endpoints, and content types. Below is an explanation of each test:

#### 1. testPiEndpoint ✅

__Description:__ Verifies that the /pi endpoint correctly returns the value of π.

Input: GET request to /pi.

Expected Output: Response with status 200 OK and the value 3.141592653589793.

#### 2. testHelloEndpoint ✅

__Description:__ Verifies that the /hello endpoint returns the message "hello world!".

Input: GET request to /hello.

Expected Output: Response with status 200 OK and the message "hello world!".

#### 3. testHelloGetEndpoint ✅

__Description:__ Verifies that the /helloget endpoint returns a personalized greeting with the provided name parameter.

Input: GET request to /helloget with name=John.

Expected Output: Response with status 200 OK and the message "Hello John".

#### 4. testHelloPostEndpoint ✅

__Description:__ Verifies that the /hellopost endpoint returns a personalized greeting with the provided name parameter.

Input: GET request to /hellopost with name=Jane.

Expected Output: Response with status 200 OK and the message "Hello Jane".

#### 5. testInvalidEndpoint ✅

__Description:__ Verifies that an invalid route returns a 404 Not Found error.

Input: GET request to /invalid.

Expected Output: Response with status 404 Not Found.

#### 6. testHelloGetWithoutName ✅

__Description:__ Verifies the behavior of the /helloget endpoint when the name parameter is not provided.

Input: GET request to /helloget without parameters.

Expected Output: Response with status 200 OK and the message "Hello null".

#### 7. testResponseContentTypeJson ✅

__Description:__ Verifies that the server response has the application/json content type.

Input: GET request to /hello.

Expected Output: Response with status 200 OK and header Content-Type: application/json.

![](/images/9.png)

These tests validate the core functionality of the HTTP server, ensuring it properly serves static files, REST responses, and handles content type detection effectively. 🚀

## Conclusions

This project implements a web server in Java with the ability to handle different endpoints and process HTTP requests. Through development and unit testing, the following conclusions have been reached:

__1. Web Server Functionality__

* The server successfully handles HTTP GET and POST requests.
* The route-based structure allows for managing multiple endpoints with dynamic responses.

__2. Handling Request Parameters__

 * The server can extract and utilize request parameters, enabling personalized responses based on the provided information.

__3. HTTP Status Code Management__

* The server correctly returns HTTP status codes, such as 200 OK for successful responses and 404 Not Found for non-existent routes.

__4. Response Validation__

* Tests confirm that response content matches expectations, validating the functionality of each endpoint.

* The presence of the Content-Type: application/json header ensures proper content interpretation.

__5. Error Handling and Robustness__

* Tests have been implemented to handle scenarios where parameters are missing, ensuring that the server responds in a controlled and predictable manner.

This project provides insights into how an HTTP server works in Java, reinforcing the use of design patterns and best practices in web application development.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Control
* [GIT](https://git-scm.com) - Versioning


## Versioning

versioning made it by [GitHub](http://git-scm.com).

## Authors

* **Jeisson Steban Casallas Rozo** - [JeissonCasallas09](https://github.com/JeissonCasallas09)

* __Date:__ 06/02/2025

## License

This project is licensed by ECI.

