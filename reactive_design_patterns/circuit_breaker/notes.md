# Circuit Breaker Notes

---

## Circuit Breaker Terms

| State     | Description                                                          |
|-----------|----------------------------------------------------------------------|
| Closed    | Dependent service is up. All requests are sent                       |
| Open      | Dependent service is down. Requests are not sent                     |
| Half-open | Dependent service might be up. Only a few requests are sent to check |
