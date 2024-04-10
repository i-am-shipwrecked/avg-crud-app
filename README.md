## Overview

This project is a Spring Boot application that serves as a backend for a final project. It provides RESTful APIs for managing projects, tasks, and users. Below is a brief overview of the main controllers in the application:

- **Projects Controller:** Manages operations related to projects, including creation, retrieval, updating, and deletion. It also allows adding tasks to a project.

- **Tasks Controller:** Handles tasks within projects, including retrieval, creation, updating, and deletion.

- **Users Controller:** Manages user-related operations such as user registration, retrieval, updating user profile, updating password, and user deletion.

## Technologies Used

The project utilizes the following technologies and dependencies:

- **Spring Boot:** The application is built using the Spring Boot framework, providing a robust and convention-over-configuration development approach.

- **Spring Data JPA:** For easy interaction with the database, allowing seamless handling of data.

- **H2 Database:** An in-memory database for development and testing purposes.

- **Hibernate Validator:** Used for validating entities.

- **Lombok:** Simplifies Java code by eliminating boilerplate code for getters, setters, constructors, etc.

- **Springfox and Springdoc:** Utilized for generating and serving OpenAPI (Swagger) documentation for the RESTful APIs.

## Build and Run

To build and run the project, ensure you have [Maven](https://maven.apache.org/) installed.

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`

API documentation, powered by Swagger, can be accessed at `http://localhost:8080/swagger-ui.html` and `http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs`

Access to the H2 database console is available after running the project. You can access it via the endpoint: `http://localhost:8080/h2-console`

## Projects Controller

### Create a New Project
- **Endpoint:** `/projects/api/createProject`
- **HTTP Method:** POST
- **Summary:** Create a new project with the provided details.
- **Request Body:** JSON representation of the project (id, name, description, beginning, ending, tasksList, users).
- **Tasks and Users:** Tasks and users associated with the project can be specified.
- **Response:** No specific response body. Project is created and saved.

### Get All Projects
- **Endpoint:** `/projects/api/getAllProjects`
- **HTTP Method:** GET
- **Summary:** Retrieve a list of all projects.
- **Response:** List of projects.

### Update Project by ID
- **Endpoint:** `/projects/api/projects/updateProjectById/{id}`
- **HTTP Method:** PUT
- **Summary:** Update an existing project identified by its ID.
- **Request Body:** JSON representation of the updated project details.
- **Response:** No specific response body. Project is updated and saved.

### Delete Project by ID
- **Endpoint:** `/projects/api/projects/deleteProjectById/{id}`
- **HTTP Method:** DELETE
- **Summary:** Delete an existing project identified by its ID.
- **Response:** No specific response body. Project is deleted.

### Get Project by ID
- **Endpoint:** `/projects/api/projects/getProjectById/{id}`
- **HTTP Method:** GET
- **Summary:** Retrieve a project by its ID.
- **Response:** Project details if found, otherwise returns a 404 response.

### Add Task to Project
- **Endpoint:** `/projects/api/projects/addTaskToProject/{projectId}`
- **HTTP Method:** POST
- **Summary:** Add a task to a project identified by its ID.
- **Request Body:** JSON representation of the task to be added.
- **Response:** No specific response body. Task is added to the project.

## Tasks Controller

### Get Tasks by Project ID
- **Endpoint:** `/tasks/api/projects/getTasksByProjectId/{id}`
- **HTTP Method:** GET
- **Summary:** Retrieve all tasks associated with a project identified by its ID.
- **Response:** List of tasks.

### Create a New Task
- **Endpoint:** `/tasks/api/projects/{id}/tasks`
- **HTTP Method:** POST
- **Summary:** Create a new task associated with a project identified by its ID.
- **Request Body:** JSON representation of the task details.
- **Response:** Created task details.

### Delete Task by ID (within a Project)
- **Endpoint:** `/tasks/api/projects/{id}/tasks/{taskId}`
- **HTTP Method:** DELETE
- **Summary:** Delete a task identified by its ID within a project identified by its ID.
- **Response:** No specific response body. Task is deleted.

### Get Task by ID (within a Project)
- **Endpoint:** `/tasks/api/projects/{projectId}/tasks/{taskId}`
- **HTTP Method:** GET
- **Summary:** Retrieve a task by its ID within a project identified by its ID.
- **Response:** Task details if found, otherwise returns a 404 response.

### Update Task (within a Project)
- **Endpoint:** `/tasks/api/projects/{projectId}/tasks/{taskId}`
- **HTTP Method:** PUT
- **Summary:** Update a task identified by its ID within a project identified by its ID.
- **Request Body:** JSON representation of the updated task details.
- **Response:** Updated task details.

## Users Controller

### Register a New User
- **Endpoint:** `/users/api/register`
- **HTTP Method:** POST
- **Summary:** Register a new user with a username and password.
- **Request Body:** JSON representation of the user details (username, password).
- **Response:** Created user details.

### Get User by ID
- **Endpoint:** `/users/api/user/{id}`
- **HTTP Method:** GET
- **Summary:** Retrieve user details by user ID.
- **Response:** User details if found, otherwise returns a 404 response.

### Update User's Username by ID
- **Endpoint:** `/users/api/user/{id}/profile`
- **HTTP Method:** PUT
- **Summary:** Update a user's username identified by its ID.
- **Request Body:** JSON representation of the updated user details.
- **Response:** Updated user details.

### Update User's Password by ID
- **Endpoint:** `/users/api/user/password/{id}`
- **HTTP Method:** PUT
- **Summary:** Update a user's password identified by its ID.
- **Request Body:** New password as a plain string.
- **Response:** Updated user details.

### Delete User by ID
- **Endpoint:** `/users/api/user/{id}`
- **HTTP Method:** DELETE
- **Summary:** Delete a user identified by its ID.
- **Response:** No specific response body. User is deleted.
