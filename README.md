# SpringChat: Multi-User Chat Application

SpringChat is a real-time chat application developed using Spring Boot, WebSocket, JPA, and JavaFX. The application allows users to join, chat, and leave chat rooms in real-time, providing a seamless and interactive chatting experience.

## Features

- Real-time chat functionality using WebSocket.
- User authentication and session management.
- Multiple chat rooms with the ability to create new ones.
- Message persistence using JPA for database interactions.
- A JavaFX client for a graphical user interface.

## Prerequisites

- Java 8 or later.
- Maven for building the project.
- PostgreSQL database for data storage.


## Database Setup

1. Install PostgreSQL on your local machine or server.
2. Create a new database for your application, run `schema.sql` script.
3. Configure the database connection in your `application.properties` file with the following settings: