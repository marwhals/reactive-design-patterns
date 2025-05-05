# 🧠 Reactive Design Patterns – Framework Mapping (Tables Only)

This document includes a categorized list of **Reactive Design Patterns** with descriptions and framework/tool support.

---

## 🧩 Service Composition & Coordination Patterns

| Pattern                | Description                                                     | Supported Frameworks / Tools                                              |
|------------------------|-----------------------------------------------------------------|---------------------------------------------------------------------------|
| **Gateway Aggregator** | Aggregates responses from multiple services at the API gateway. | Spring Cloud Gateway, Netflix Zuul, AWS API Gateway, Kong, GraphQL BFF    |
| **Scatter-Gather**     | Sends requests in parallel and merges results.                  | Akka Streams, Spring WebFlux, Node.js (`Promise.all`), AWS Step Functions |
| **Orchestrator**       | Manages control flow between services.                          | Temporal, Camunda, Netflix Conductor, Spring Orchestration                |
| **Splitter**           | Splits a message into parts for processing.                     | Apache Camel, Spring Integration, Akka Streams                            |

---

## 🛡️ Resiliency Patterns

| Pattern             | Description                                      | Supported Frameworks / Tools                                            |
|---------------------|--------------------------------------------------|-------------------------------------------------------------------------|
| **Circuit Breaker** | Stops calls to a failing service temporarily.    | Resilience4j, Spring Cloud Circuit Breaker, Istio, Hystrix (deprecated) |
| **Bulkhead**        | Isolates failures by partitioning service calls. | Resilience4j, Akka, Istio                                               |
| **Retry**           | Retries a failed operation.                      | Spring Retry, Resilience4j, Reactor Retry, Akka                         |
| **Fallback**        | Returns a default value when a service fails.    | Resilience4j, Hystrix, Spring Cloud Gateway                             |
| **Timeout**         | Limits time spent waiting for a response.        | Spring WebFlux, Vert.x, RxJava, Resilience4j                            |
| **Rate Limiter**    | Throttles incoming requests.                     | Resilience4j, Envoy, Istio, API Gateway, Kong                           |
| **Backpressure**    | Manages load by signaling upstream.              | Reactor, RxJava, Akka Streams, gRPC, Vert.x                             |

---

## 🚀 Performance & Scalability Patterns

| Pattern            | Description                                                  | Supported Frameworks / Tools                |
|--------------------|--------------------------------------------------------------|---------------------------------------------|
| **Event Sourcing** | Stores state changes as a sequence of events.                | Axon Framework, Eventuate, Akka Persistence |
| **CQRS**           | Separates command (write) and query (read) responsibilities. | Axon, Lagom, Eventuate                      |
| **Sharding**       | Partitions data or actors.                                   | Akka Cluster Sharding, Kafka, Cassandra     |
| **Load Balancing** | Distributes load across instances.                           | Kubernetes, Istio, Envoy, Ribbon (Netflix)  |

---

## 🔁 Messaging & Communication Patterns

| Pattern                       | Description                                | Supported Frameworks / Tools                  |
|-------------------------------|--------------------------------------------|-----------------------------------------------|
| **Message Queue / Broker**    | Decouples producers and consumers.         | Kafka, RabbitMQ, NATS, AWS SQS                |
| **Publish-Subscribe**         | Broadcasts events to multiple subscribers. | Kafka, Redis Streams, MQTT, Pub/Sub           |
| **Event-Driven Architecture** | Services react to events.                  | Kafka, EventBridge, Spring Cloud Stream, Akka |
| **Message Routing**           | Routes messages based on logic.            | Apache Camel, Spring Integration, MuleSoft    |
| **Message Filtering**         | Filters incoming messages by rules.        | AWS EventBridge, Kafka Streams                |
| **Competing Consumers**       | Multiple consumers share the workload.     | Kafka, RabbitMQ, SQS, Azure Functions         |
| **Dead Letter Queue (DLQ)**   | Captures unprocessable messages.           | Kafka, RabbitMQ, AWS SQS, Event Hubs          |

---

## 📦 Data Management Patterns

| Pattern                        | Description                                | Supported Frameworks / Tools             |
|--------------------------------|--------------------------------------------|------------------------------------------|
| **Materialized View**          | Precomputed data for fast reads.           | Kafka Streams, Redis, Elasticsearch      |
| **Cache Aside / Read-Through** | Loads cache on demand.                     | Spring Cache, Redis, Hazelcast, Caffeine |
| **Transactional Outbox**       | Ensures reliable messaging with DB writes. | Debezium, Spring Outbox, Eventuate       |
| **Saga**                       | Coordinates distributed transactions.      | Axon, Temporal, Camunda, Spring Sagas    |

---

## 🧠 Observability & Monitoring Patterns

| Pattern                 | Description                         | Supported Frameworks / Tools             |
|-------------------------|-------------------------------------|------------------------------------------|
| **Health Check**        | Reports service status.             | Spring Boot Actuator, Kubernetes Probes  |
| **Metrics Collection**  | Gathers performance data.           | Prometheus, Micrometer, New Relic        |
| **Centralized Logging** | Collects logs in a single location. | ELK Stack, Fluentd, Loki                 |
| **Tracing**             | Tracks requests across services.    | OpenTelemetry, Jaeger, Zipkin, AWS X-Ray |
| **Dashboarding**        | Visualizes metrics and status.      | Grafana, Kibana, Prometheus UI           |

---

## 🧱 Structural & Concurrency Patterns

| Pattern                  | Description                              | Supported Frameworks / Tools               |
|--------------------------|------------------------------------------|--------------------------------------------|
| **Reactive Composition** | Combines async flows (zip, merge, etc.). | Project Reactor, RxJava, Kotlin Coroutines |
| **Supervisor Hierarchy** | Parents supervise child lifecycles.      | Akka                                       |
| **Actor Model**          | Actors encapsulate state and behavior.   | Akka, Microsoft Orleans, Erlang            |
| **Event Loop**           | Non-blocking single-threaded model.      | Node.js, Vert.x, Netty                     |

---