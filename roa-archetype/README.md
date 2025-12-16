# ROA Test Framework Archetype

## Table of Contents
- [Quick Start](#quick-start)
- [Generation Matrix](#generation-matrix)
- [Examples](#examples)
- [Output Expectations](#output-expectations)
- [What You Get](#what-you-get)
- [Key Classes (What to Customize)](#key-classes-what-to-customize)
- [Post-Generation Checklist](#post-generation-checklist)
- [Example Test](#example-test)

## Quick Start
CLI (batch, API+UI+DB, advanced data, Postgres):
```bash
mvn archetype:generate ^
  -DarchetypeGroupId=io.cyborgcode.roa.example ^
  -DarchetypeArtifactId=roa-archetype ^
  -DarchetypeVersion=1.2.0 ^
  -DarchetypeRepository=https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries ^
  -DgroupId=com.mycompany ^
  -DartifactId=my-tests ^
  -Dversion=1.0-SNAPSHOT ^
  -Dpackage=com.mycompany ^
  -Dmodules=API,UI,DB ^
  -DcommonFeatures=ADVANCED ^
  -DdbType=POSTGRES ^
  -DuiComponents=BUTTON,INPUT,SELECT ^
  -B
```

IntelliJ: New Project -> Maven Archetype -> Manage Catalogs -> add catalog URL -> select `roa-archetype` -> fill properties -> Finish.

## Generation Matrix
| Property | Description | Allowed values | Default |
| --- | --- | --- | --- |
| modules | Capabilities to include | API, UI, DB (comma-separated) | API,UI,DB |
| commonFeatures | Test data/preconditions bundle | BASIC, ADVANCED | BASIC |
| dbType | DB flavor (when DB selected) | POSTGRES, MYSQL, H2, ORACLE, SQLSERVER, MARIADB | POSTGRES |
| uiComponents | UI element families (when UI selected) | BUTTON, INPUT, SELECT (comma-separated) | BUTTON,INPUT,SELECT |
| groupId | Maven groupId | any | (required) |
| artifactId | Maven artifactId | any | (required) |
| version | Project version | any | 1.0-SNAPSHOT |
| package | Base package for sources | any | matches groupId |

## Examples
API-only, basic:
```bash
mvn archetype:generate -DarchetypeGroupId=io.cyborgcode.roa.example -DarchetypeArtifactId=roa-archetype -DarchetypeVersion=1.2.0 -DarchetypeRepository=https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries -DgroupId=com.myco -DartifactId=api-tests -Dpackage=com.myco -Dmodules=API -DcommonFeatures=BASIC -B
```

UI-only with buttons+inputs:
```bash
mvn archetype:generate -DarchetypeGroupId=io.cyborgcode.roa.example -DarchetypeArtifactId=roa-archetype -DarchetypeVersion=1.2.0 -DarchetypeRepository=https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries -DgroupId=com.myco -DartifactId=ui-tests -Dpackage=com.myco.ui -Dmodules=UI -DuiComponents=BUTTON,INPUT -B
```

Full stack, advanced data, MySQL:
```bash
mvn archetype:generate -DarchetypeGroupId=io.cyborgcode.roa.example -DarchetypeArtifactId=roa-archetype -DarchetypeVersion=1.2.0 -DarchetypeRepository=https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries -DgroupId=com.myco -DartifactId=full-tests -Dpackage=com.myco.tests -Dmodules=API,UI,DB -DcommonFeatures=ADVANCED -DdbType=MYSQL -DuiComponents=BUTTON,INPUT,SELECT -B
```

## Output Expectations
- Only selected modules generate code. If you omit UI, no UI packages appear. If you set `uiComponents=BUTTON`, only button-related classes are created (no input/select classes). Likewise, `modules=API,DB` skips UI entirely.
- Package structure follows your `package` value under `src/main/java` and `src/test/java`.
- Example tests and `Example*` classes are templates that compile; replace or delete them when wiring to a real app (they are not meant to hit real systems as-is).

## What You Get
A compile-ready scaffold with examples you are expected to replace:
- API example flows (request/validate/chain) targeting sample endpoints
- UI example flows (browser control, elements, waits) using sample selectors
- DB example flows (queries, data checks) against sample schemas
- Custom flow samples that show how to compose API/UI/DB steps

## Key Classes (What to Customize)
### API (when API selected)
- ExampleEndpoints.java: Registry of endpoints (method, path, headers, defaults).
- ExampleRequestDto.java / ExampleResponseDto.java: Request/response models for your API payloads.
- ExampleAuthenticationClient.java: How to obtain auth headers or tokens (login flow).
- ExampleCredentials.java: Supplies credentials from config.

### UI (when UI selected)
- InputFields.java / ButtonFields.java / SelectFields.java: Your page elements by type.
- InputFieldTypes.java / ButtonFieldTypes.java / SelectFieldTypes.java: Behavior variants (text, password, primary, icon, single-select, multi-select, etc.).
- ExampleAppUiLogin.java: Browser login flow steps.
- ExampleCredentials.java: UI login credentials source.
- ExampleTableModel.java: Form auto-fill via @InsertionElement.
- RequestsInterceptor.java: Capture or inspect network calls during UI tests.
- SharedUi.java: Common UI helpers (waits, popups, etc.).

### DB (when DB selected)
- Databases.java: DB connection registry (driver, URL, credentials).
- ExampleDbQueries.java: Named SQL queries with parameters like {userId}.

### Common (always)
- Rings.java: Service registry entry points (API, UI, DB, CUSTOM).
- CustomService.java: Multi-step workflows across modules.
- DataProperties.java / Data.java: Typed config keys and accessors.
- DataCreator.java / DataCleaner.java / Preconditions.java (when commonFeatures=ADVANCED): Factories, cleanup hooks, reusable setup.

## Post-Generation Checklist
- Set config in src/main/resources/config.properties (base URLs, DB, auth).
- Replace ExampleEndpoints and DTOs with your real API models.
- Update UI element classes and ExampleAppUiLogin for your app screens and auth.
- Define real queries in ExampleDbQueries and connections in Databases.
- Decide on data features: flesh out DataCreator/DataCleaner/Preconditions or remove if unused.
- Remove or disable example tests until wired to real systems (@Disabled or delete).
- Run mvn test to confirm the scaffold compiles; expect to adjust or disable examples until your real endpoints, UI locators, and DB are configured.

## Example Test
```java
@Test
void createUser(Quest quest, @Craft(model = DataCreator.Data.USER_DATA) UserDto user) {
    quest.use(RING_OF_API)
        .requestAndValidate(
            UserEndpoints.CREATE_USER,
            user,
            Assertion.builder().target(STATUS).type(IS).expected(201).build()
        )
        .complete();
}
```
