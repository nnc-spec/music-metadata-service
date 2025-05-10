
# Music Metadata Service

This is a RESTful service for managing metadata of music tracks and artists. The project uses Spring Boot, JPA (Hibernate), and PostgreSQL, and is containerized with Docker for production-like deployment.

## Features

- Create, list, and delete artists
- Create tracks and list tracks by artist
- UUID-based ID system
- PostgreSQL database via Docker
- Layered architecture: Controller, Service, Repository
- Unit and integration testing using JUnit and Mockito

## Technologies Used

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- Maven
- JUnit 5
- Mockito
- Docker and Docker Compose

## Project Structure

```
src
├── main
│   ├── java/com/ice/music_metadata_service
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   └── resources
│       └── application.yml
└── test
    └── java/com/ice/music_metadata_service
```

## How to Run with Docker

### 1. Build and start the application with Docker Compose

```
docker-compose up --build
```

This command will:
- Build the Spring Boot application into a Docker image
- Start a PostgreSQL container (`db`) on port `5432`
- Start the application container (`app`) on port `8080`

### 2. Access the Service

Once the application is running, you can access the API at:

```
http://localhost:8080
```

### 3. Interacting with the API

You can use tools like Postman or curl to test the endpoints.

#### Create Artist

```
POST /artists
Content-Type: application/json

{
  "name": "Adele"
}
```

#### Create Track

```
POST /tracks
Content-Type: application/json

{
  "title": "Rolling In The Deep",
  "genre": "Pop",
  "length": 4,
  "artistId": "uuid-of-existing-artist"
}
```

#### Get Tracks by Artist

```
GET /tracks/by-artist/{artistId}
```

#### Get Artist of the Day

```
GET /artist-of-the-day
```

## Running Tests

To run all unit and integration tests:

```
mvn test
```

---

This setup ensures consistent behavior across environments and makes the application easier to deploy in production.


## API Documentation & Testing

Due to an issue with Swagger UI rendering in the current environment, all API endpoints were tested using **Postman**.

You can find the complete Postman collection under the [`postman/`](./postman/) directory.

```bash
postman/
└── Music Metadata Service.postman_collection.json
```

> 💡 Import this collection into Postman to test the full set of available endpoints, including:
> - Add New Track
> - Edit Artist Name
> - Fetch Artist Tracks
> - Artist of the Day  
