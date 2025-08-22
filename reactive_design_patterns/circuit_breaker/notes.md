# Circuit Breaker Notes

---

## Circuit Breaker Terms

| State     | Description                                                          |
|-----------|----------------------------------------------------------------------|
| Closed    | Dependent service is up. All requests are sent                       |
| Open      | Dependent service is down. Requests are not sent                     |
| Half-open | Dependent service might be up. Only a few requests are sent to check |

---

Spring cloud circuit breaker docs - https://spring.io/projects/spring-cloud-circuitbreaker/

---

### Summary

- Allows the client service to operate normally when the upstream service is not healthy
- You can use it along with Retry + Timeout
- Resilience4j
  - Spring, reactor support
  - Ratelimiter, Bulkhead etc
  - Config using YAML --- can be overriden using a config bean