# Rate Limiter notes

---

- Server Side Rate Limiter:
  - To limit the number of requests being served by a server node
  - To protect system resources from overload
  - Very important for CPU intensive tasks
- Client Side Rate Limiter
  - To limit the number of requests sent by a client to a server - "upstream calls"
  - To reduce the cost / respect the contract

---

- Distributed rate limiter *Not to be confused with an application feature like below*
  - Guest user - 5 / sec
  - Standard user 50 / sec
  - Super user 500 / sec
- *See Redis / caching for this style of rate limiting*