# Gateway Aggregator notes

---

## Problem Statement

```mermaid
graph LR

A((Client / Webrowser)) --> Service_1[Service]
A((Client / Webrowser)) --> Service_2[Service]
A((Client / Webrowser)) --> Service_3[Service]
A((Client / Webrowser)) --> Service_4[Service]


```

- The issues we are trying to solve with this pattern
  - More network calls
  - Browser limits - could ask client to configure but we want to avoid that.
  - Increased Latency
  - Complex aggregation logic on the frontend

## The Aggregator Pattern

```mermaid
graph LR

B[Aggregator]

    A((Client / Webrowser)) --> B[Aggregator] --> Service_1[Service]
    B[Aggregator]--> Service_2[Service]
    B[Aggregator]--> Service_3[Service]
    B[Aggregator]--> Service_4[Service]
    
    
```

- Notice - all the backend logic is hidden and the aggregator service acts like a proxy or a facade
- From the client point of view it is just one call.