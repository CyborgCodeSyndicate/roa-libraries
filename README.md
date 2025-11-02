# roa-libraries

## Automation Testing Libraries — Module Index

> Jump straight to a module:
- [API Interactor](./api-interactor/README.md)
- [API Adapter](./api-interactor-test-framework-adapter/README.md)
- [DB Interactor](./db-interactor/README.md)
- [DB Adapter](./db-interactor-test-framework-adapter/README.md)
- [UI Interactor](./ui-interactor/README.md)
- [UI Adapter](./ui-interactor-test-framework-adapter/README.md)
- [Assertions](./assertions/README.md)
- [Test Framework](./test-framework/README.md)

---

## Quick Start Paths
> Tip: Every module’s README starts with **“Usage — Quick Start”** so you can copy/paste minimal setup and run immediately.

- **Starting fresh with API tests?** Begin here → [API Interactor — Quick Start](./api-interactor/README.md#usage).
- **Using the fluent DSL already?** Add the adapter → [API Adapter](./api-interactor-test-framework-adapter/README.md#usage) and (optionally) the [Test Framework](../temp-project/test-framework/README.md).
- **Need DB checks?** Go to [DB Interactor](./db-interactor/README.md#usage) → then its [DB Adapter](./db-interactor-test-framework-adapter/README.md#usage).
- **Automating the UI?** See [UI Interactor](./ui-interactor/README.md#usage) → then [UI Adapter](./ui-interactor-test-framework-adapter/README.md#usage).

---

## Modules Overview

| Module | Description | Docs |
|---|---|---|
| **api-interactor** | Foundation for **declarative REST API testing** on Java 17. Model endpoints as code (`Endpoint<T>`), compose requests immutably (`ParametrizedEndpoint`), execute via pluggable `RestClient`, orchestrate with `RestService`, validate with `RestResponseValidator`. | [README](./api-interactor/README.md) |
| **api-interactor-test-framework-adapter** | Fluent adapter layer that integrates **api-interactor** with your higher-level test DSL (e.g., quest/OLYMPYS), providing expressive `request` / `requestAndValidate` chains. | [README](./api-interactor-test-framework-adapter/README.md) |
| **db-interactor** | Database interaction/testing toolkit (schemas, connections, query builders, validation helpers) designed to parallel the API interactor’s style. | [README](./db-interactor/README.md) |
| **db-interactor-test-framework-adapter** | Adapter that wires **db-interactor** into the fluent test framework for readable database test flows. | [README](./db-interactor-test-framework-adapter/README.md) |
| **ui-interactor** | Web UI automation layer (page/element abstractions, actions, waits) meant to be framework-agnostic and composable, analogous to the API/DB interactors. | [README](./ui-interactor/README.md) |
| **ui-interactor-test-framework-adapter** | Fluent adapter for **ui-interactor**, exposing high-level UI actions/assertions in the same DSL used across modules. | [README](./ui-interactor-test-framework-adapter/README.md) |
| **assertions** | Core **assertion/validation** library (assertion types, targets, builders, result model) used by API/DB/UI interactors. | [README](./assertions/README.md) |
| **test-framework** | Test orchestration primitives (runners, lifecycles, DSL entry points) that the adapters plug into to provide fluent scenarios. | [README](./test-framework/README.md) |
