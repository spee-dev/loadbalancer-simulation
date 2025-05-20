# Load Balancer Simulator
## About
Load Balancer Simulator is a robust Spring Boot application designed to mimic real-world load balancing behavior across multiple service instances. It supports advanced load balancing strategies such as Round Robin, Least Connections, and Weighted balancing. The project also includes health checks, service registration, request tracing, rate limiting, and integrates AI for predictive scaling and anomaly detection.

Ideal for developers and DevOps engineers looking to understand or implement load balancing and microservice routing logic in enterprise-grade applications.

## Features
- Service Registration: Register multiple service instances dynamically.
- Health Check Scheduler: Automatically monitor service health.
- Multiple Load Balancing Strategies: Round Robin, Least Connections, Weighted.
- Request Dispatching: Route client requests intelligently based on strategy.
- Active Connection Tracking: Manage and release connections effectively.
- Rate Limiting: Prevent overload by limiting requests per service.
- Request Tracing & Metrics: Collect latency and usage data.
- Circuit Breaker: Handle failing services gracefully.
- AI-powered Predictions(optional): Predictive load distribution using AI models.
- Swagger API Documentation: Interactive API testing and documentation.

## Architecture & Design
- Modular package structure:
  - config — Spring and resilience configurations.
  - controller— REST endpoints for services and dispatch.
  - service— Business logic including load balancing and AI integration.
  - strategy— Load balancing algorithms.
  - entity & repository — JPA entities and database operations.
  - scheduler — Background tasks for health checks and cleanup.
  - exception — Custom exception handling.
  - dto — Data transfer objects for API communication.


