# db-interactor-test-framework-adapter

<!-- Quick jump -->
**Start here:** [Usage — Quick Start (step-by-step)](#usage)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Structure](#structure)
- [Architecture](#architecture)
    - [Class Diagram](#class-diagram)
    - [Execution Flow](#execution-flow)
      - [Adapter Runtime Flow](#adapter-runtime-flow)
- [Usage](#usage)
    - [Step 1 — Install](#step-1--install)
    - [Step 2 — Configure environment](#step-2--configure-environment)
    - [Step 3 — Enable the Adapter on Tests](#step-3--enable-the-adapter-on-tests)
    - [Step 4 — Run Queries Fluently](#step-4--run-queries-fluently)
    - [Step 5 — Validate Query Responses](#step-5--validate-query-responses)
    - [Step 6 — Wait/Retry Until Data Appears](#step-6--waitretry-until-data-appears)
    - [Step 7 — Use Hooks (optional)](#step-7--use-hooks-optional)
- [Annotations & Hooks](#annotations--hooks)
- [Retry Helpers](#retry-helpers)
- [Allure Reporting](#allure-reporting)
- [Adapter Configuration](#adapter-configuration)
- [Dependencies](#dependencies)
- [Author](#author)

---

## Overview
The **db-interactor-test-framework-adapter** layers **test-facing ergonomics** on top of the core
**db-interactor** module. It introduces:

- a fluent service (`DatabaseServiceFluent`) for chaining `query`, `query(jsonPath)`, `queryAndValidate`, `validate`, and `retryUntil`,
- Allure bridges (`AllureDbClientManager`, `RelationalDbClientAllure`, `QueryResponseValidatorAllureImpl`) that attach executed SQL, timings and validation targets to reports,
- JUnit 5 enablement via the `@DB` annotation and extensions (`DbTestExtension`, `DbHookExtension`),
- hook processing through `@DbHook`/`@DbHooks` -> `DbHookFlow` (BEFORE/AFTER class),
- handy retry helpers (`RetryConditionDb`) for eventual consistency scenarios,
- and Spring auto-configuration to wire the Allure-enabled components by default.

The result is **declarative, traceable, and robust** database automation that integrates cleanly with the ROA (Ring of Automation) quest/service model while staying test-framework agnostic.

## Features
- **Fluent chaining:** `DatabaseServiceFluent` -> `query`, `query(jsonPath, type)`, `queryAndValidate`, `validate`, `retryUntil`.
- **Allure integration:**
    - `AllureDbClientManager` (creates `RelationalDbClientAllure`),
    - `RelationalDbClientAllure` (attaches SQL + duration + rows),
    - `QueryResponseValidatorAllureImpl` (attaches validation data).
- **JUnit 5 bootstrap:** `@DB` applies `DbTestExtension` (closes connections) + `DbHookExtension` (runs hooks), scans `.db`.
- **Hooks:** `@DbHook` / `@DbHooks` driving enum-backed `DbHookFlow` implementations.
- **Retry helpers:** `RetryConditionDb` (`queryReturnsRows`, `queryReturnsValueForField`) to combine with `retryUntil(...)`.
- **Spring auto-config:** `DbTestFrameworkAutoConfiguration` exposes @Primary beans for Allure client manager and validator.
- **Storage integration:** results are stored under `StorageKeysDb.DB` keyed by your query enum.

## Structure
- `allure` — `AllureDbClientManager`, `RelationalDbClientAllure`, `QueryResponseValidatorAllureImpl`
- `annotations` — `DB`, `DbHook`, `DbHooks`
- `config` — `DbTestFrameworkAutoConfiguration`
- `extensions` — `DbHookExtension`, `DbTestExtension`
- `hooks` — `DbHookFlow<T>`
- `retry` — `RetryConditionDb`
- `service.fluent` — `DatabaseServiceFluent`
- `storage` — `StorageKeysDb`

> **Depends on:** all types from **db-interactor** (`DatabaseService`, `DbQuery`, `QueryResponse`, etc.).

## Architecture

### Class Diagram
```mermaid
classDiagram
  direction LR

  DbClientManager <|-- AllureDbClientManager
  RelationalDbClient <|-- RelationalDbClientAllure
  QueryResponseValidatorImpl <|-- QueryResponseValidatorAllureImpl

  class DatabaseServiceFluent {
    +query(query)
    +query(query, jsonPath, type)
    +queryAndValidate(query, assertions)
    +validate(response, assertions)
    +retryUntil(retryCondition, maxWait, retryInterval)
    #getDatabaseService() : DatabaseService
  }

  DatabaseServiceFluent --> DatabaseService : uses

  DbTestFrameworkAutoConfiguration ..> AllureDbClientManager : provides
  DbTestFrameworkAutoConfiguration ..> QueryResponseValidatorAllureImpl : provides

  DbHookExtension ..> DbHook : reads
  DbHookExtension ..> DbHookFlow : executes

  DbTestExtension ..> BaseDbConnectorService : closes

  RetryConditionDb ..> DatabaseService : via conditions

  class DB
  class DbHook
  class DbHooks

  note for DB "Annotation marker for DB tests"
  note for DbHook "Repeatable annotation for DB hooks"
  note for DbHooks "Container for DbHook"
```

### Execution Flow
#### Adapter Runtime Flow
```mermaid
sequenceDiagram
  autonumber
  participant Test as JUnit Test (@DB)
  participant Ext as DbTestExtension
  participant HookEx as DbHookExtension
  participant Fluent as DatabaseServiceFluent
  participant Svc as DatabaseService
  participant CM as AllureDbClientManager
  participant DB as JDBC/DB

  Test->>HookEx: BEFORE class hooks (@DbHook when=BEFORE)
  HookEx->>Svc: resolve via Spring (auto-configured)
  Test->>Fluent: query(UserQueries.GET_BY_ID.withParam("id", 1))
  Fluent->>Svc: query(...)
  Svc->>CM: getClient(dbConfig)
  CM->>DB: open/get connection
  Svc->>DB: execute SQL
  DB-->>Svc: rows (QueryResponse)
  Svc-->>Fluent: QueryResponse (Allure attachments)
  Test->>HookEx: AFTER class hooks (@DbHook when=AFTER)
  Ext-->>DB: close all connections (after all tests)
```

---

## Usage

### Step 1 — Install
Add the adapter **and** the core module:
```xml
<dependency>
  <groupId>io.cyborgcode.roa</groupId>
  <artifactId>db-interactor-test-framework-adapter</artifactId>
  <version>${revision}</version>
  <scope>test</scope>
</dependency>
```

### Step 2 — Configure environment
The adapter relies on the same **Owner** configuration as `db-interactor`. Create `src/test/resources/config.properties` and run with `-Ddb.config.file=db-config`:

```properties
project.package=com.mycompany.myapp
db.default.type=POSTGRES
db.default.host=localhost
db.default.port=5432
db.default.name=appdb
db.default.username=app
db.default.password=secret
# Optional (overrides host/port/name)
# db.full.connection.string=jdbc:postgresql://localhost:5432/appdb
```

When Spring is present, `DbTestFrameworkAutoConfiguration` will register the Allure-enabled beans automatically.

### Step 3 — Enable the Adapter on Tests
Annotate your JUnit test class with `@DB` to activate extensions and scanning.

```java
@DB
class UsersDbTests {
  // your tests...
}
```

### Step 4 — Run Queries Fluently
<pre><code>
.query(Queries.QUERY_ORDER.withParam("id",
                  retrieve(responseBodyExtraction(ENDPOINT_EXAMPLE, "$.id"), Long.class)))
            .validate(retrieve(Queries.QUERY_ORDER, QueryResponse.class),
                  Assertion.builder().target(NUMBER_ROWS).type(IS).expected(3).soft(true)
                        .build())
</code></pre>

### Step 5 — Validate Query Responses

<pre><code>
 .validate(retrieve(StorageKeysDb.DB, QUERY_ORDER, QueryResponse.class),
                  Assertion.builder()
                        .target(QUERY_RESULT).key(DbResponsesJsonPaths.PRODUCT_BY_ID.getJsonPath(1))
                        .type(CONTAINS_ALL).expected(List.of(order.getProduct())).soft(true)
                        .build(),
                  Assertion.builder()
                        .target(QUERY_RESULT).key(DbResponsesJsonPaths.LOCATION_BY_ID.getJsonPath(1))
                        .type(CONTAINS_ALL).expected(List.of(order.getLocation())).soft(true)
                        .build()
            )
)
</code></pre>

### Step 6 — Wait/Retry Until Data Appears
With the adapter you can combine `RetryConditionDb` and your framework's retry utility.

```java
import java.time.Duration;

// using a generic RetryUtils from your framework:
String ok = RetryUtils.retryUntil(
  Duration.ofSeconds(30),
  Duration.ofSeconds(2),
  () -> db.retryUntil(queryReturnsRows(UserQueries.GET_BY_ID.withParam("id", 42)),
                      Duration.ofSeconds(30),
                      Duration.ofSeconds(2)),
  res -> true // condition already enforced inside
);
```

### Step 7 — Use Hooks (optional)
Run DB setup/cleanup once per class with `@DbHook` and your enum-backed `DbHookFlow` implementations.

```java
@DB
@DbHook(when = HookExecution.BEFORE, type = "SEED_BASELINE")
@DbHook(when = HookExecution.AFTER,  type = "CLEANUP")
class OrdersDbTests { /* ... */ }
```

## Annotations & Hooks
- `@DB` — applies JUnit 5 extensions for DB tests and scans the adapter packages (`com.theairebellion.zeus.db`).
- `@DbHook(when, type, arguments, order)` / `@DbHooks` — run **BEFORE/AFTER** class hook flows; implement `DbHookFlow` to register custom database hook logic (executed with a shared `Map<Object,Object>` and a `DatabaseService`).

## Retry Helpers
`RetryConditionDb` provides ready-made `RetryCondition`s to use with `DatabaseServiceFluent.retryUntil(...)`:
- `queryReturnsRows(query)` — polls until the query returns at least one row.
- `queryReturnsValueForField(query, jsonPath, expected)` — polls until the extracted field (via JSONPath) equals `expected`.

## Allure Reporting
- **Queries:** `RelationalDbClientAllure` (extends `RelationalDbClient`) attaches executed SQL, duration, and result snapshot.
- **Validations:** `QueryResponseValidatorAllureImpl` (extends `QueryResponseValidatorImpl`) attaches the **validation target map** (data extracted for assertions).
- **Client Manager:** `AllureDbClientManager` (extends `DbClientManager`) ensures Allure-enhanced clients are created and reused.

## Adapter Configuration
```java
@Configuration
@ComponentScan(basePackages = "io.cyborgcode.roa.db")
public class DbTestFrameworkAutoConfiguration {

  @Bean
  @Primary
  public DbClientManager dbClientManager(AllureDbClientManager allureDbClientManager) {
    return allureDbClientManager;
  }

  @Bean
  @Primary
  public QueryResponseValidator queryResponseValidator(QueryResponseValidatorAllureImpl impl) {
    return impl;
  }
}
```

---

## Dependencies
- `io.cyborgcode:db-interactor` *(required)*
- `io.qameta.allure:allure-java-commons` *(optional — base Allure attachments)*
- `io.qameta.allure:allure-junit5` *(optional — Allure + JUnit 5 bridge)*
- `org.springframework:spring-context` *(optional — DI / auto-config)*
- `org.springframework:spring-test` *(tests — JUnit 5 SpringExtension)*
- `org.junit.jupiter:junit-jupiter` *(tests)*
- `org.assertj:assertj-core` *(tests — used by SoftAssertions in examples)*
- `com.fasterxml.jackson.core:jackson-databind` *(usually transitive via `db-interactor`; add explicitly if your BOM doesn’t manage it)*
- `com.jayway.jsonpath:json-path` *(usually transitive via `db-interactor`; add explicitly if your BOM doesn’t manage it)*
- `org.projectlombok:lombok` *(optional — codegen)*
- **JDBC driver** for your DB, e.g.:
  - `org.postgresql:postgresql`
  - `mysql:mysql-connector-j`

---

## Author
**Cyborg Code Syndicate**
