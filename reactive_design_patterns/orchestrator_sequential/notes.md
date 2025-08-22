# Sequential Orchestration patter

---

## Chained Pattern

- No special aggregator
- Any service can assume the role of aggregation

```mermaid
graph LR
    
    A[service a]
    B[service b]
    C[service c]
    D[service d]
    
    A --> B --> C --> D

```

## Pros and Cons

- Pros
  - Easy to implement
- Cons
  - Increased Latency
  - Very difficult to debug
  - Very difficult to maintain / implement change in requirements

## Orchestrator for chained workflows

- Orchestrator

```mermaid

graph LR

A[orchestrator]
B[service-a]
C[service-a]
D[service-a]
E[service-a]

A --> B
A --> C
A --> D
A --> E

```