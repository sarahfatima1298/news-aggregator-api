Here’s a **README** tailored to your existing project:

---

# News Aggregator API

## Overview

The **News Aggregator API** is a Spring Boot application that allows users to register, log in, and manage their news preferences. It utilizes **JWT** for secure token-based authentication and integrates with external news APIs to provide user-specific news articles.

## Features

### 1. **User Authentication**
- `POST /api/register`: Register a new user.
- `POST /api/login`: Authenticate a user and issue a JWT token.

### 2. **News Preferences**
- `GET /api/preferences`: Retrieve the logged-in user's news preferences.
- `PUT /api/preferences`: Update the logged-in user's preferences.

### 3. **News Articles**
- `GET /api/news`: Fetch personalized news articles from external APIs based on user preferences.

## Technologies Used

- **Spring Boot**: Core framework.
- **Spring Security**: JWT-based authentication.
- **Gradle**: Build tool.
- **In-Memory Database**: Stores user data and preferences.
- **External News APIs**: Integrates with services like NewsAPI, GNews API, etc.
- **Spring WebClient**: Fetches news articles asynchronously.

## Running the Application

### Prerequisites

- **Java 17** or later
- **Gradle** installed

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/news-aggregator-api.git
   ```
2. Build the project:
   ```bash
   ./gradlew build
   ```
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## API Endpoints

1. **User Registration**  
   `POST /api/register`  
   Example Payload:
   ```json
   {
     "email": "user@example.com",
     "password": "securePassword"
   }
   ```

2. **User Login**  
   `POST /api/login`  
   Example Request:
   ```
   /api/login?email=user@example.com&password=securePassword
   ```

3. **Retrieve Preferences**  
   `GET /api/preferences`  
   Requires Bearer Token in Authorization Header.

4. **Update Preferences**  
   `PUT /api/preferences`  
   Example Payload:
   ```json
   ["Technology", "Health"]
   ```

5. **Fetch News**  
   `GET /api/news`  
   Fetches articles based on the user's preferences.

## External APIs

- **NewsAPI**: Provides articles from various sources.

> Sign up on these platforms to obtain API keys.

## Testing

Use **Postman** or **cURL** to validate API functionality.

## License

This project is open-source and available under the [MIT License](LICENSE).