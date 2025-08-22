# Orchestrator pattern notes

---

### Overview - Service Orchestrator

- Aggregator - additional business logic to provide a workflow

```mermaid

graph LR
    Z[Order]
    A[Orchestrator]
    F[(Data Base)]

    subgraph Services
        B[Product]
        C[Payment]
        D[Inventory]
        E[Shipping]

    end

    Z ---> A
    A ----> B
    A ----> C
    A ----> D
    A ----> E
    Z -----> F

```

### Design

| product-service | user-service | inventory-service | shipping-service | Order status | Orchestrator Actions                   |
|-----------------|--------------|-------------------|------------------|--------------|----------------------------------------|
| Success         | Success      | Success           | Success          | Success      |                                        |
| 404             |              |                   |                  |              | 404                                    |
| Success         |              |                   |                  |              |                                        |
| Success         | Failed       | Success           | Success          | Failed       | restore inventory <br> cancel shipping |
| Success         | Success      | Failed            | Success          | Failed       | refund user <br> cancel shipping       |
| Success         | Success      | Success           | Failed           | Failed       | refund user <br> restore inventory     |

---

### Service layer implementation

```mermaid
graph TD
    A[Order Orchestration]

    subgraph service
        B[order-fulfillment]
        C[order-cancellation]
    end

    subgraph orchestrator
        D[orchestrator]
        E[payment-orchestrator]
        F[inventory-orchestrator]
        G[shipping-orchestrator]
    end

    subgraph client
        H[userClient]
        I[inventoryClient]
        J[shippingClióent]
    end

    A --> B
    A --> C
    B --> D
    C --> D
    D --> E --> H
    D --> F --> I
    D --> G --> J
%% Define styles
    classDef highlight stroke: #ff0000, stroke-width: 3px;
    class A highlight;


```