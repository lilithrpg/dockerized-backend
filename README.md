# User Management API

## Requirements

- Docker
- Docker Compose
- Maven

## Setup

1. Clone the repository using
    ```
    git clone https://github.com/lilithrpg/dockerized-backend.git
    ```
2. Go to the file folder
3. Build and run the application:
    ```bash
    ./build.sh
    ```

4. The application services will be accessible at the following ports:
* User Service: http://localhost:8080
* Notification Service: http://localhost:8081
* MailHog (SMTP server): http://localhost:8025

## Testing

Run tests using:
```bash
mvn test
```

## API Endpoints

### User Service
* Register User: POST /v1/user
* Edit User: PUT /v1/user
* Get User: GET /v1/users/{id}
* Get All Users: GET /v1/user
* Delete User: DELETE /v1/user/{id}

### Email Service
* Get Notification: GET /v1/notification/{id}
* Create Notification Template: POST /v1/notification//create-template
* Send Notification: GET /v1/notification/send

### Additional Information
* This project uses a fake SMTP server (MailHog) for email notifications. 
* Each service can be packaged and run via Docker.