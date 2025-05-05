# Reactive Design Patterns

*Diagrams created with AI Assistant / AI Assistants*

### Gateway Aggregator Pattern

```mermaid

graph TD
Client -->|Request| Gateway
Gateway -->|Request A| ServiceA
Gateway -->|Request B| ServiceB
Gateway -->|Request C| ServiceC
ServiceA -->|Response A| Gateway
ServiceB -->|Response B| Gateway
ServiceC -->|Response C| Gateway
Gateway -->|Aggregated Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#f9f,stroke:#333,stroke-width:2px;
    class ServiceA,ServiceB,ServiceC serviceNode;

```

### Scatter Gather Pattern

```mermaid

graph TD
Client -->|Request| Scatter
Scatter -->|Request 1| Service1
Scatter -->|Request 2| Service2
Scatter -->|Request 3| Service3
Service1 -->|Response 1| Gather
Service2 -->|Response 2| Gather
Service3 -->|Response 3| Gather
Gather -->|Aggregated Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef gatherNode fill:#cf9,stroke:#333,stroke-width:2px;
    class Service1,Service2,Service3 serviceNode;
    class Gather gatherNode;

```

### Orchestrator Pattern (For Parallel workflow)

```mermaid

graph TD
Client -->|Request| Orchestrator
Orchestrator -->|Task 1| Service1
Orchestrator -->|Task 2| Service2
Orchestrator -->|Task 3| Service3
Service1 -->|Result 1| Orchestrator
Service2 -->|Result 2| Orchestrator
Service3 -->|Result 3| Orchestrator
Orchestrator -->|Consolidated Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef orchestratorNode fill:#9cf,stroke:#333,stroke-width:2px;
    class Service1,Service2,Service3 serviceNode;
    class Orchestrator orchestratorNode;
```

### Orchestrator Pattern (For Sequential workflow)

```mermaid

graph TD
Client -->|Request| Orchestrator
Orchestrator -->|Invoke Chain 1| Service1
Service1 -->|Result 1| Orchestrator
Orchestrator -->|Invoke Chain 2| Service2
Service2 -->|Result 2| Orchestrator
Orchestrator -->|Invoke Chain 3| Service3
Service3 -->|Result 3| Orchestrator
Orchestrator -->|Chained Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef orchestratorNode fill:#9cf,stroke:#333,stroke-width:2px;
    class Service1,Service2,Service3 serviceNode;
    class Orchestrator orchestratorNode;
```

### Splitter Pattern

```mermaid

graph TD
Client -->|Request| Splitter
Splitter -->|Split Message 1| Service1
Splitter -->|Split Message 2| Service2
Splitter -->|Split Message 3| Service3
Service1 -->|Response 1| Aggregator
Service2 -->|Response 2| Aggregator
Service3 -->|Response 3| Aggregator
Aggregator -->|Aggregated Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#9cf,stroke:#333,stroke-width:2px;
    classDef splitterNode fill:#fc9,stroke:#333,stroke-width:2px;
    classDef aggregatorNode fill:#cf9,stroke:#333,stroke-width:2px;
    class Service1,Service2,Service3 serviceNode;
    class Splitter splitterNode;
    class Aggregator aggregatorNode;
```

---
## Resiliency Patterns

### Timeout Pattern

```mermaid
graph TD
Client -->|Request| Service
Service -->|Timeout Occurs| TimeoutHandler
TimeoutHandler -->|Fallback Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef timeoutHandlerNode fill:#f66,stroke:#333,stroke-width:2px;
    class Service serviceNode;
    class TimeoutHandler timeoutHandlerNode;
```

### Retry Pattern

```mermaid
graph TD
Client -->|Request| Service
Service -->|Failure| RetryHandler
RetryHandler -->|Retry Request| Service
RetryHandler -->|Max Retries Reached| FallbackService
Service -->|Success Response| Client
FallbackService -->|Fallback Response| Client

    %% Styling for clarity
    classDef serviceNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef retryHandlerNode fill:#6cf,stroke:#333,stroke-width:2px;
    classDef fallbackServiceNode fill:#f66,stroke:#333,stroke-width:2px;
    class Service serviceNode;
    class RetryHandler retryHandlerNode;
    class FallbackService fallbackServiceNode;
```

### Circuit Breaker Pattern

```mermaid


graph TD
    Client -->|Request| CircuitBreaker
    CircuitBreaker -->|Open? Check State| StateManager
    StateManager -->|Closed| Service
    StateManager -->|Open| FallbackService
    Service -->|Success Response| Client
    Service -->|Failure| CircuitBreaker
    CircuitBreaker -->|Threshold Breached| StateManager
    FallbackService -->|Fallback Response| Client
%% Styling for clarity
    classDef serviceNode fill: #f96, stroke: #333, stroke-width: 2px;
    classDef circuitBreakerNode fill: #6cf, stroke: #333, stroke-width: 2px;
    classDef fallbackServiceNode fill: #f66, stroke: #333, stroke-width: 2px;
    classDef stateManagerNode fill: #cf9, stroke: #333, stroke-width: 2px;
    class Service serviceNode;
    class CircuitBreaker circuitBreakerNode;
    class FallbackService fallbackServiceNode;
    class StateManager stateManagerNode;

```

### Rate Limiter Pattern

```mermaid


graph TD
    Client -->|Request| RateLimiter
    RateLimiter -->|Allowed| Service
    RateLimiter -->|Throttled| ThrottleHandler
    Service -->|Success Response| Client
    ThrottleHandler -->|Fallback Response| Client
%% Styling for clarity
    classDef serviceNode fill: #f96, stroke: #333, stroke-width: 2px;
    classDef rateLimiterNode fill: #6cf, stroke: #333, stroke-width: 2px;
    classDef throttleHandlerNode fill: #f66, stroke: #333, stroke-width: 2px;
    class Service serviceNode;
    class RateLimiter rateLimiterNode;
    class ThrottleHandler throttleHandlerNode;

```

### Bulkhead Pattern

```mermaid

graph TD
    Client -->|Request| BH[Bulkhead]
    BH -->|Isolate Partition 1| Service1
    BH -->|Isolate Partition 2| Service2
    BH -->|Isolate Partition 3| Service3
    Service1 -->|Response 1| BH
    Service2 -->|Response 2| BH
    Service3 -->|Response 3| BH
    BH -->|Aggregated Response| Client

%% Styling for clarity
    classDef serviceNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef bulkheadNode fill:#9cf,stroke:#333,stroke-width:2px;
    class Service1,Service2,Service3 serviceNode;
    class BH bulkheadNode;


```