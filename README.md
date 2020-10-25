# Rest API For Studies with Spring, Spring Security, Spring Data, JPA

This is the REST API backend for a project of good practices and integrations of a Rest API.

The application is educational in nature.

It was developed in Spring Boot and exploring Spring concepts: Security, Data, in addition to JPA, Jackson and others.

The image below describes a diagram of project entities:

![flow](https://user-images.githubusercontent.com/29001162/97117472-c6489e80-16e2-11eb-8b80-f6c0cff87c42.png)

## How to build the project

1. Install Apache Maven and configure it in your PATH
2. Run, at the Terminal or Command Prompt `mvn exec: java`

To know that everything happened successfully, the last lines of the terminal must be identical to these:

``
Hibernate: values next value for hibernate_sequence
``

``Hibernate: insert into person (address, hobbies, name, id) values (?,?,?,?)
``

To stop the application press `CTRL + C`

### By your favorite IDE

1. Import the project as a Maven project
2. Run the `FullstackSpringbootApplication.java` class in the` com.matheusicaro.course.fullstack` package


## API documentation

Once the API is started you can access [http: // localhost: 8080 / swagger-ui.htm] (http: // localhost: 8080 / swagger-ui.htm)
to see your documentation.

This documentation can only be accessed with the application running.

## Doubts

Insert an _Issue_ in this project :-)