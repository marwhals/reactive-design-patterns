# Bulkhead notes

---

- Allocating resources based on the API
- Limit the number of calls done concurrently

---

# RateLimiter vs Bulkhead

- RateLimiter
  - Number of requests per window of time
  - Reject other calls
- Bulkhead
  - Number of concurrent class
  - Queue other calls