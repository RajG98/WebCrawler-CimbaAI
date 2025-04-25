# Web Crawler - CimbaAI

A Kubernetes-based web crawler project developed to crawl websites and store logs in a PostgreSQL database. The project is composed of multiple microservices including a React frontend, Spring Boot backend, and a FastAPI service. The entire stack is deployed using Helm charts, with Docker Compose for local development.

## Features

- **Frontend (React)**: Provides a user interface to interact with the web crawler.
- **Backend (Spring Boot)**: Handles the crawling logic and interacts with the database to store logs.
- **FastAPI**: Serves as an API for the crawling process.
- **PostgreSQL**: Stores crawling logs and results.
- **Ingress Controller**: Exposes the frontend and backend services to external access using a custom domain.

## Architecture

This project consists of the following components:

- **Frontend**: A React app that communicates with the backend to trigger crawling operations and display logs.
- **Backend**: A Spring Boot application that performs the crawling operations and returns results.
- **FastAPI**: A FastAPI service that interacts with the backend to manage the crawling operations.
- **PostgreSQL**: Stores the crawling logs in a relational database.
- **Ingress**: Exposes the frontend and backend services through a Kubernetes ingress, accessible via a custom domain.

## Requirements

- Docker
- Kubernetes (Minikube or any other)
- Helm
- kubectl
- PostgreSQL (for storing logs)
- Java 11 or later (for Spring Boot backend)
- Python 3.x (for FastAPI service)
- Node.js (for React frontend)

## Setup

### Local Development with Docker Compose

To run the project locally using Docker Compose, follow these steps:

1. **Clone the repository**:

   ```bash
   git clone https://github.com/RajG98/WebCrawler-CimbaAI.git
   cd WebCrawler-CimbaAI
   ```

2. **Build the Docker images**:

   Ensure that you have the necessary Docker images for each component (frontend, backend, fastapi, postgres).

   ```bash
   docker-compose build
   ```

3. **Start the services**:

   Run the following command to start the application using Docker Compose.

   ```bash
   docker-compose up
   ```

   This will start the following services:

   - Frontend: React app running on port 3000
   - Backend: Spring Boot app running on port 8081
   - FastAPI: FastAPI app running on port 8000
   - PostgreSQL: PostgreSQL container running on port 5432

4. **Access the application**:
   - Frontend: Open your browser and navigate to [http://localhost:3000](http://localhost:3000)
   - Backend: Open Postman or any API client to interact with [http://localhost:8081/api](http://localhost:8081/api)

### Kubernetes Setup with Helm

1. **Install Helm**:

   If you don't have Helm installed, follow the [installation guide](https://helm.sh/docs/intro/install/) for your platform.

2. **Deploy the application on Kubernetes**:

   - Ensure your Kubernetes cluster is running (Minikube, Docker Desktop, etc.).
   - Install the Ingress controller (if not already installed):

   ```bash
   kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
   ```

3. **Install the Helm Chart**:

   Navigate to the `helm` directory and run:

   ```bash
   helm install web-crawler ./web-crawler-chart
   ```

4. **Configure the Ingress**:

   If you are using a local setup, modify the `/etc/hosts` file to map `webcrawler.local` to `127.0.0.1`:

   ```bash
   127.0.0.1 webcrawler.local
   ```

   Then, you can access the application at [http://webcrawler.local](http://webcrawler.local).

## Services

- **Frontend**: React app to interact with the backend and trigger crawling actions.
- **Backend**: Handles API calls for crawling websites and stores logs in PostgreSQL.
- **FastAPI**: A Python service to manage the crawling process.
- **PostgreSQL**: Stores logs of the crawled URLs.

## API Endpoints

### Backend API

- **GET /logs**: Returns all crawl logs stored in the PostgreSQL database.
- **POST /crawl**: Triggers the crawling process for the provided URL.

### Example Request to Crawl

```bash
POST http://localhost:8081/api/crawl?url=https://example.com
```
