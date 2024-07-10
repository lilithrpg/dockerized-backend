#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

echo "Building user-management..."
cd user-management
./mvnw clean package -DskipTests
cd ..

echo "Building email-service..."
cd notification-api
./mvnw clean package -DskipTests
cd ..

echo "Starting all services with Docker Compose..."
docker-compose up --build

echo "To stop the services, run 'docker-compose down'"