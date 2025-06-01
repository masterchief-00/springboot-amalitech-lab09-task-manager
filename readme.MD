# LAB: Task Management API

## Overview
A simple Task Manager application built with Spring Boot. This project demonstrates a layered architecture using Controllers, Services, and Domain models, and exposes RESTful APIs for managing tasks, projects, and users.

## 🛠 Features
- Manage tasks, projects, and users
- RESTful API endpoints
- Layered architecture (Controllers, Services, Domain)
- Exception handling
- Configurable components and utilities

## ▱ Component Diagram
![diagram.png](src%2Fmain%2Fresources%2Fdiagram.png)

## ⚙️ Database (In-memory)
The app persists data using hashmaps, below is the description of the entities:
- `employees (id, first_name,last_name,email)`
- `projects (id, title, description, employee_id, due, created_at, updated_at)`
- `tasks (id, title, description, employee_id, due priority, status)`


## Swagger Documentation
All endpoints are well described on the Swagger UI documentation on - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## 💻 Requirements
- Java 17
- Maven
- (Optional) Docker

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/masterchief-00/springboot-amalitech-lab09-task-manager.git
   cd springboot-amalitech-lab09-task-manager
   ```

2. Build the project:
   ```sh
   ./mvnw clean install   
   ```

3. Run the application:
   ```sh
   ./mvnw spring-boot:run
   ```
The app will start on - [http://localhost:8080](http://localhost:8080).

### Or, do it with docker
1. Build the project:
   ```sh
   docker build -t springboot-taskmanager-dockerized . 
   ```  
2. Run the image:
    ```
   docker run -p 8080:8080 springboot-taskmanager-dockerized
   ```

Good stuff👍🏼