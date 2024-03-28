# Apache Camel integrated with Spring Boot Polling Example  

## TOC

- [0 Introduction and goal of the project](#0-introduction-and-goal-of-the-project) <br/>
- [1 Running the application](#1-running-the-application) <br/>
  - [1.1 Docker Compose Run](#11-docker-compose-run) <br/>
  - [1.2 IDE Run](#12-ide-run) <br/>
- [2 Testing the application](#2-testing-the-application) <br/>
  - [2.1 Testing via Postman](#21-testing-via-postman) <br/>
  - [2.2 Testing via Curl](#22-testing-via-curl) <br/>

## 0 Introduction and goal of the project

The aim of this project is to demonstrate the followings;

- An Apache Camel poller integrated with Spring Boot
- A Spring Boot backend API that serves the persisted polled documents

The tech stack is as follows;

- Spring Boot
- Apache Camel
- MySQL Database

The application is dockerized.

Functionally the application is controlled via Postman. The controller has two operations;

- A GET endpoint to retrieve the persisted governmental public documentations
- A POST endpoint to trigger the polling of the governmental public documentations

To give a demonstration, I'm using officially public documentation of the Dutch goverment, called as "OfficielebekendMakingen", based on [OASIS standard](http://docs.oasis-open.org/ns/search-ws/sruResponse)

Camel Routes are consuming the XML data, parsing with the use of XPath, and persisting the parsed data to the mysql database.

Here is an overview of the structure of the project;

![01_diagram.png](https://github.com/bzdgn/camel-boot-polling-example/blob/main/misc/01_diagram.png)

[Go back to TOC](#toc)


## 1 Running The Application

In this part I'm going to show you how to run the project via docker file.

## 1.1 Docker Compose Run

We have two components, the mysql databse and the backend.

Simply the steps are as follows;

- Build the docker image of the backend
- Create the network bridge
- Run the whole stack via docker compose

1. Build the docker image of the backend;

`docker build --tag=oep-poller:latest .`

2. Create the network;

`docker network create -d bridge poller-app-net`

3. Run the yaml file for whole stack;

`docker compose up`


[Go back to TOC](#toc)

## 1.2 IDE run

In this scenario, we only need the database, thus we are only going to start the database. Still it's important 

1. Create the network;

`docker network create -d bridge poller-app-net`

2. Run the yaml file for only database;

`docker compose up -d db`

3. Run the application via IDE;

[Go back to TOC](#toc)


## 2 Testing the application

In this section, I'm going to show you how you can test the running application.

When the application is running, you can see the swagger API documentation via `` as follows;

![02_swagger.png](https://github.com/bzdgn/camel-boot-polling-example/blob/main/misc/02_swagger.png)

[Go back to TOC](#toc)


## 2.1 Testing via Postman

If you have [Postman](https://www.postman.com/) which is a mainstream tool to test web-service endpoints, you can import the [Postman Collection][index.html](https://github.com/bzdgn/camel-boot-polling-example/blob/misc/Camel-Poller.postman_collection.json) from the [misc](https://github.com/bzdgn/camel-boot-polling-example/blob/misc/) directory.

Once you do it, you can trigger the polling process via the POST operation, and/or retrieve the the polled persisted data via the GET operation;

1. Triggering the route;

This is done via a POST operation, and the payload should be as follows;

```json
{
    "dtDate": "2024-03-27",
    "dtModified": "2024-03-27"
}
```

**dtDate**: Stands for the since date of the documents. If empty or null, the default value will be used.
**dtModified**: Stands for the since modified date of the documents. If empty or null, the default value will be used.

When you are done with Postman, the POST operation should succeed as below;

![03_postman_post.png](https://github.com/bzdgn/camel-boot-polling-example/blob/main/misc/03_postman_post.png)

2. Retrieving the data;

Retrieve is simple, you should be able to call the GET operation as follows;

![04_postman_get.png](https://github.com/bzdgn/camel-boot-polling-example/blob/main/misc/04_postman_get.png)

[Go back to TOC](#toc)


## 2.2 Testing via curl

Testing via curl is easy;

1. Triggering the route;

In windows the following curl command should work;

`curl -d "{\"dtDate\":\"2024-03-27\",\"dtModified\":\"2024-03-27\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/poll`

In *nix systems, the following should also work;

`curl -d '{"dtDate":"2024-03-27","dtModified":"2024-03-27"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/v1/poll`

2. Retrieving the data;

In both Windows and *nix systems, the following curl command should work;

`curl -H "Content-Type: application/json" -X GET http://localhost:8080/api/v1/documents`


[Go back to TOC](#toc)


