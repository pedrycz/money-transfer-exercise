# Money transfer exercise

Coding task: "Design and implement a RESTful API (including data model and the backing implementation) for money transfers between accounts."

### Used technologies

- Java 8
- Dropwizard
- Hibernate
- H2
- Flyway

### Requirements

- Java 8

### Launch

`mvnw exec:java`

### Endpoints description

- `POST /account` creates new account and returns its id
- `GET /account/{id}` returns account details, example output:
```json
{"id": 24, "fromTransfers": [{"id": 1}, {"id": 2}], "toTransfers": [{"id": 4}], "balance": 10}
```
- `POST /transfer` creates new money transfer and returns its id, example input:
```json
{"from": {"id": 1}, "to": {"id": 2}, "amount": 500}
```
- `GET /transfer/{id}` returns money transfer details, example output:
```json
{"id": 1, "from": {"id": 1}, "to": {"id": 2}, "amount": 500}
```

### Current Design
- account represented as simple entity with just one column: `id`
- money transfer represented as entity with 4 columns: `id`, `from`, `to`, `amount` (`from` and `to` define money transfer sides, they are indexed to speed up select operation)
- `balance` field in account entity generated dynamically basing on money transfers connected with certain account
- `amount` represented and stored as high precision integer, scale has been not defined as it can be different for different currencies
- `amount` must be positive

### Possible improvements for future
- multi currency support: additional entity `currency` referenced by additional column in `transfer`
- account ownership: additional entity `owner` referenced by additional column in `account`
- money deposit and withdrawal: two additional entities (`deposit`, `withdrawal`) analogical to `transfer`
- balance caching (when its dynamic generation will become too time consuming): additional column in `account` triggered and updated on insert info `transfer` (`deposit`, `withdrawal`)
- debt prevention or limit (currently not implemented because there is no possibility to fund account): simple condition in `TransferService` implementation
- money transfer reverse: additional `reversed` flag in `transfer` and REST endpoint able to set this flag
- money transfer confirmation: additional `confirmed` flag in `transfer` and REST endpoint able to set this flag
- money transfer scheduling: additional `go_live` column in `transfer`
- integration testing using: https://www.dropwizard.io/0.9.2/docs/manual/testing.html#integration-testing