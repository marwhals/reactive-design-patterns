# Retry pattern notes

---

- Transient failure
- In microservices we need to assume that the network is unreliable. I.e anything can happen

---

## Diagram

```mermaid

graph LR

    A[Client]
    B[Load Balancer]

    subgraph application_instances [Instances]
        C[Instance One]:::faulty
        D[Instance Two]
        E[Instance Three]
        F[Instance Four]
    end

    A <===> B
    B -.-|Attempt 1| C
    B ---|Attempt 2| D
    B --- E
    B --- F

%% Legend
    subgraph legend [Legend]
        L1[Red = Faulty Instance]:::faultyLegend
        L2[Normal = Healthy Instance]
    end

%% Styles
    classDef faulty fill:#ff4d4d,stroke:#000,stroke-width:1px;
    classDef faultyLegend fill:#ff4d4d,stroke:#000,stroke-width:1px;


```

---

### Summary

- Recover from Transient failure
- It might increase the overall response time
  - Important to set `Timeout`
- Do not retry for 4XX error