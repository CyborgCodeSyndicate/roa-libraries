# ROA Test Framework Archetype

## Table of Contents
- [Archetype Setup](#archetype-setup)
    - [Remote Catalog URL](#remote-catalog-url)
- [Project Creation Methods](#project-creation-methods)
    - [IntelliJ IDEA Integration](#-instructions-how-to-create-a-new-project-from-archetype-via-intellij)
    - [Command Line Creation](#instructions-on-how-to-create-project-from-archetype-via-command-line)
- [Generation Matrix](#generation-matrix)
- [Environment Configuration](#environment-configuration)
- [What Gets Generated (Based on Your Configuration)](#what-gets-generated-based-on-your-configuration)
    - [Module Selection (modules)](#module-selection-modules)
    - [Implementation Style (implementationStyle)](#implementation-style-implementationstyle)
    - [UI Components (uiComponents)](#ui-components-uicomponents)
    - [Database Type (dbType)](#database-type-dbtype)
- [Generated Files & Customization Guide](#generated-files--customization-guide)
    - [API Components](#api-when-api-module-selected)
    - [UI Components](#ui-when-ui-module-selected)
    - [DB Components](#db-when-db-module-selected)
    - [Common Components](#common-always-generated)
- [Post-Generation Checklist](#post-generation-checklist)
- [Example Tests](#example-tests)

---

## Archetype Setup

To use this archetype easily in your IDE or via CLI without remembering long repository URLs, add our remote catalog.

### Remote Catalog URL

Update the {version} with a deployed archetype catalog version from gh-pages.
You can find all deployed versions under the **roa-archetype-catalog** folder.
Select one and then populate {version} in the url below when creating the remote catalog.

**Example Version Format:** `1.2.3`, `1.0.0`, `2.0.0` ...

**REMOTE CATALOG URL:**

```
https://cyborgcodesyndicate.github.io/roa-libraries/roa-archetype-catalog/{version}/archetype-catalog.xml
```

**Example (with real version):**

```
https://cyborgcodesyndicate.github.io/roa-libraries/roa-archetype-catalog/1.2.4/archetype-catalog.xml
```

---

## Project Creation Methods

### 💻 Instructions how to create a new project from archetype via IntelliJ

1. Go to **File** → **New** → **Project**
2. Select **Maven Archetype**
3. Click the **Manage Catalogs...** button
4. In the dialog that appears:
    - Click **Add (+)** and paste the Remote Catalog URL
    - Give it a name
    - Click **OK** to save the catalog
5. Back in the "New Project" dialog:
    - Select your newly added catalog from the **Catalog** dropdown
    - The `roa-archetype` should appear in the list below
    - Select it to see the available versions
6. Configure your project (Advanced options):
    - **GroupId**: Your organization's group ID (e.g., com.mycompany)
    - **ArtifactId**: Your project's name
    - **Version**: Select the desired version
    - **Additional Properties**: Customize as needed:
        - `modules`: Select which testing technologies to include (API, UI, DB). Can be a combination of them or a single one.
        - `environments`: Add your environments here or leave empty if working on only one env (ex. uat, qa, test, dev)
        - `implementationStyle`: Choose between BASIC, ADVANCED, or AI
        - `dbType`: Select your database type if using DB module (ex. H2, POSTGRES, MYSQL...)
        - `uiComponents`: Choose UI components to include (BUTTON, SELECT, INPUT, TABLE)
7. Click **Next** to proceed with project generation
8. After generation build the newly created project

---

### Instructions on how to create project from archetype via command line

**Using Remote Catalog (Recommended):**

Replace `{version}` with the actual version from gh-pages (e.g., 1.2.3):

**Windows (Powershell):**

```bash
mvn "archetype:generate" `
  "-DarchetypeGroupId=io.cyborgcode.roa" `
  "-DarchetypeArtifactId=roa-archetype" `
  "-DarchetypeVersion={version}" `
  "-DgroupId=com.mycompany" `
  "-DartifactId=my-tests" `
  "-Dversion=1.0-SNAPSHOT" `
  "-Dpackage=com.mycompany" `
  "-Dmodules=API,UI,DB" `
  "-Denvironments=qa,uat" `
  "-DimplementationStyle=ADVANCED" `
  "-DdbType=POSTGRES" `
  "-DuiComponents=BUTTON,INPUT,SELECT,TABLE" `
  "-B"
```

**Linux/Mac (Bash):**

```bash
mvn archetype:generate \
  -DarchetypeGroupId=io.cyborgcode.roa \
  -DarchetypeArtifactId=roa-archetype \
  -DarchetypeVersion={version} \
  -DgroupId=com.mycompany \
  -DartifactId=my-tests \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=com.mycompany \
  -Dmodules=API,UI,DB \
  -Denvironments=qa,uat \
  -DimplementationStyle=ADVANCED \
  -DdbType=POSTGRES \
  -DuiComponents=BUTTON,INPUT,SELECT,TABLE \
  -B
```

**Example Command (with real version):**

Replace `{version}` with `1.2.3`:

```bash
mvn "archetype:generate" `
  "-DarchetypeGroupId=io.cyborgcode.roa" `
  "-DarchetypeArtifactId=roa-archetype" `
  "-DarchetypeVersion=1.2.3" `
  "-DgroupId=com.mycompany" `
  "-DartifactId=my-tests" `
  "-Dversion=1.0-SNAPSHOT" `
  "-Dpackage=com.mycompany" `
  "-Dmodules=API,UI,DB" `
  "-Denvironments=qa,uat" `
  "-DimplementationStyle=ADVANCED" `
  "-DdbType=POSTGRES" `
  "-DuiComponents=BUTTON,INPUT,SELECT,TABLE" `
  "-B"
```

---

## Generation Matrix

| Property | Description | Allowed values | Default |
|----------|-------------|----------------|---------|
| modules | Capabilities to include | API, UI, DB (comma-separated) | API,UI,DB |
| implementationStyle | Test data/preconditions bundle | BASIC, ADVANCED, AI | ADVANCED |
| dbType | DB flavor (when DB selected) | POSTGRES, MYSQL, H2, ORACLE, SQLSERVER, MARIADB | POSTGRES |
| environments | Target environments (comma-separated) | any string | qa,uat |
| uiComponents | UI element families (when UI selected) | BUTTON, INPUT, SELECT, TABLE (comma-separated) | BUTTON,INPUT,SELECT,TABLE |
| groupId | Maven groupId | any | (required) |
| artifactId | Maven artifactId | any | (required) |
| version | Project version | any | 1.0-SNAPSHOT |
| package | Base package for sources | any | matches groupId |

---

## Environment Configuration

When you specify environments (e.g., `-Denvironments=qa,uat`), the following files are generated for each environment:
- `config-{env}.properties` - Environment-specific configuration
- `test-data-{env}.properties` - Environment-specific test data

**Example:** For `-Denvironments=qa,uat`, you get:
- `config-qa.properties`, `config-uat.properties`
- `test-data-qa.properties`, `test-data-uat.properties`

---

## What Gets Generated (Based on Your Configuration)

The archetype generates different files based on the parameters you provide during project creation. This section explains what you get for each configuration option.

### Module Selection (modules)

**Parameter:** `-Dmodules=API,UI,DB`

The `modules` parameter determines which testing capabilities are included. You can select one or more (comma-separated).

| Module | When Selected | What You Get |
|--------|---------------|--------------|
| **API** | `-Dmodules=API` | API endpoints, DTOs, authentication client, API-specific test examples |
| **UI** | `-Dmodules=UI` | UI elements, component services, login flows, UI-specific test examples |
| **DB** | `-Dmodules=DB` | Database connections, query enums, DB-specific test examples |
| **Combination** | `-Dmodules=API,UI,DB` | All above + CustomService showing cross-module workflows (only for BASIC/ADVANCED styles) |

**Dependencies added to pom.xml:**
- `API` → `api-interactor-test-framework-adapter`
- `UI` → `ui-interactor-test-framework-adapter`
- `DB` → `db-interactor-test-framework-adapter`

---

### Implementation Style (implementationStyle)

**Parameter:** `-DimplementationStyle=BASIC|ADVANCED|AI`

The implementation style determines the data management structure and example complexity in your project.

#### BASIC

**Structure:**

```
common/
  ├── base/
  │   └── Rings.java
  ├── service/
  │   └── CustomService.java
  └── data/
      └── test_data/
          ├── Data.java
          └── DataProperties.java
```

**Use when:** You only need simple test data from properties files without data factories

**You get:**
- Config accessors (`Data.java`, `DataProperties.java`)
- Basic service structure (`CustomService.java`, `Rings.java`)
- Example tests demonstrating framework usage

**You DON'T get:** Data creators, cleaners, or preconditions

---

#### ADVANCED (Default)

**Structure:**

```
common/
  ├── base/
  │   └── Rings.java
  ├── service/
  │   └── CustomService.java
  └── data/
      ├── creator/
      │   └── DataCreator.java
      ├── cleaner/
      │   └── DataCleaner.java
      ├── preconditions/
      │   └── Preconditions.java
      └── test_data/
          ├── Data.java
          └── DataProperties.java
```

**Use when:** You need full test data management with factories, cleanup hooks, and reusable precondition flows

**You get:**
- Full data management suite with example implementations
- `CustomService` with example domain flows
- Example tests demonstrating `@Craft`, `@Journey`, `@Ripper`, and authentication annotations

**Features:** `@Craft`, `@Journey`, `@Ripper` annotations work out-of-the-box with provided examples

---

#### AI

**Structure:**

```
common/
  ├── base/
  │   └── Rings.java
  └── data/
      ├── creator/
      │   └── DataCreator.java (empty enum structure)
      ├── cleaner/
      │   └── DataCleaner.java (empty enum structure)
      ├── preconditions/
      │   └── Preconditions.java (empty enum structure)
      └── test_data/
          ├── Data.java
          └── DataProperties.java
```

**Use when:** You want AI to generate implementations from instructions for your Application Under Test

**You get:**
- Enum structures with no implementations (empty)
- Basic configuration classes (`Data.java`, `DataProperties.java`)
- Component folder structure (if UI components selected) but no implementation classes
- Only base elements (e.g., `Tables.java` enum, no `ExampleTableModel.java`)

**You DON'T get:**
- `CustomService` class
- Component implementation classes (e.g., no `ButtonExampleImpl.java`, `InputExampleImpl.java`)
- UI table models (no `ExampleTableModel.java`)
- Example tests

**Benefit:** Clean structure ready for AI code generation based on your AUT specifications. AI can populate the enum implementations and create the service/component classes based on your instructions.

---

### UI Components (uiComponents)

**Parameter:** `-DuiComponents=BUTTON,INPUT,SELECT,TABLE` (or subset or empty)

UI component generation is **optional and selective**. You only get classes for the components you specify. If you leave this parameter empty, only `AppUiService` and authentication classes are generated.

#### Component Generation Matrix

**For BASIC and ADVANCED styles:**

| Component | Parameter | Generated Classes | Location |
|-----------|-----------|-------------------|----------|
| **BUTTON** | `BUTTON` | `ButtonFields.java` | `ui/elements/` |
|  |  | `ButtonFieldTypes.java` | `ui/types/` |
|  |  | `ButtonExampleImpl.java` | `ui/components/button/` |
| **INPUT** | `INPUT` | `InputFields.java` | `ui/elements/` |
|  |  | `InputFieldTypes.java` | `ui/types/` |
|  |  | `InputExampleImpl.java` | `ui/components/input/` |
| **SELECT** | `SELECT` | `SelectFields.java` | `ui/elements/` |
|  |  | `SelectFieldTypes.java` | `ui/types/` |
|  |  | `SelectExampleImpl.java` | `ui/components/select/` |
| **TABLE** | `TABLE` | `Tables.java` | `ui/elements/` |
|  |  | `ExampleTableModel.java` | `ui/model/table/` |

**For AI style:**

| Component | Parameter | Generated Classes | Location |
|-----------|-----------|-------------------|----------|
| **BUTTON** | `BUTTON` | `ButtonFields.java` | `ui/elements/` |
|  |  | `ButtonFieldTypes.java` | `ui/types/` |
|  |  | *(empty folder)* | `ui/components/button/` |
| **INPUT** | `INPUT` | `InputFields.java` | `ui/elements/` |
|  |  | `InputFieldTypes.java` | `ui/types/` |
|  |  | *(empty folder)* | `ui/components/input/` |
| **SELECT** | `SELECT` | `SelectFields.java` | `ui/elements/` |
|  |  | `SelectFieldTypes.java` | `ui/types/` |
|  |  | *(empty folder)* | `ui/components/select/` |
| **TABLE** | `TABLE` | `Tables.java` | `ui/elements/` |
|  |  | *(no model class generated)* | — |

#### Examples

**Only INPUT and BUTTON (ADVANCED style):**

```bash
-DuiComponents=INPUT,BUTTON -DimplementationStyle=ADVANCED
```

**Result:**

```
ui/
  ├── elements/
  │   ├── InputFields.java
  │   └── ButtonFields.java
  ├── types/
  │   ├── InputFieldTypes.java
  │   └── ButtonFieldTypes.java
  └── components/
      ├── input/
      │   └── InputExampleImpl.java
      └── button/
          └── ButtonExampleImpl.java
```

**Only INPUT and BUTTON (AI style):**

```bash
-DuiComponents=INPUT,BUTTON -DimplementationStyle=AI
```

**Result:**

```
ui/
  ├── elements/
  │   ├── InputFields.java
  │   └── ButtonFields.java
  ├── types/
  │   ├── InputFieldTypes.java
  │   └── ButtonFieldTypes.java
  └── components/
      ├── input/     (empty folder)
      └── button/    (empty folder)
```

**No UI components (just authentication/service):**

```bash
-DuiComponents=
```

**Result:** Only `AppUiService.java` and authentication classes, no element enums

**All components (default):**

```bash
-DuiComponents=BUTTON,INPUT,SELECT,TABLE
```

**Result:** All element enums, types, and component implementations (or empty folders for AI style)

---

### Database Type (dbType)

**Parameter:** `-DdbType=POSTGRES|MYSQL|H2|ORACLE|SQLSERVER|MARIADB`

Only affects the DB module configuration when `-Dmodules=DB` is selected.

- Sets the default database type in `Databases.java`
- Configures connection string examples in `config-{env}.properties`
- Does not add JDBC driver dependencies (you add these manually)

---

## Generated Files & Customization Guide

The archetype generates a compile-ready scaffold with example implementations (for BASIC/ADVANCED) or empty structures (for AI). The exact files depend on your [configuration parameters](#what-gets-generated-based-on-your-configuration).

Below are the key files organized by module.

### API (when API module selected)

**Always generated:**

- **ExampleEndpoints.java** (`api/`): Registry of endpoints (method, path, headers, defaults). Replace with your real API endpoints.
- **ExampleRequestDto.java** (`api/dto/request/`): Request models. Replace with your API payloads.
- **ExampleResponseDto.java** (`api/dto/response/`): Response models. Replace with your API responses.
- **ExampleAuthenticationClient.java** (`api/authentication/`): How to obtain auth headers or tokens. Implement your authentication logic.
- **ExampleCredentials.java** (`api/authentication/`): Supplies credentials from config. Add your API credentials.

---

### UI (when UI module selected)

**Always generated:**

- **AppUIService.java** (`ui/`): UI service facade providing access to component services.
- **ExampleAppUiLogin.java** (`ui/authentication/`): Browser login flow steps. Implement your login sequence.
- **ExampleCredentials.java** (`ui/authentication/`): UI login credentials source. Add your UI credentials.
- **RequestsInterceptor.java** (`ui/interceptor/`): Capture or inspect network calls during UI tests. Configure URL patterns to intercept.

**Conditionally generated** (based on `-DuiComponents` and `-DimplementationStyle` parameters):

| If you selected | Implementation Style | You get | What to customize |
|-----------------|---------------------|---------|-------------------|
| `BUTTON` | BASIC or ADVANCED | `ButtonFields.java` (elements)<br>`ButtonFieldTypes.java` (types)<br>`ButtonExampleImpl.java` (components/button) | Replace with your button locators and behaviors |
| `BUTTON` | AI | `ButtonFields.java` (elements)<br>`ButtonFieldTypes.java` (types)<br>`ui/components/button/` (empty folder) | Implement component class based on your AUT |
| `INPUT` | BASIC or ADVANCED | `InputFields.java` (elements)<br>`InputFieldTypes.java` (types)<br>`InputExampleImpl.java` (components/input) | Replace with your input field locators and types |
| `INPUT` | AI | `InputFields.java` (elements)<br>`InputFieldTypes.java` (types)<br>`ui/components/input/` (empty folder) | Implement component class based on your AUT |
| `SELECT` | BASIC or ADVANCED | `SelectFields.java` (elements)<br>`SelectFieldTypes.java` (types)<br>`SelectExampleImpl.java` (components/select) | Replace with your dropdown locators and selection strategies |
| `SELECT` | AI | `SelectFields.java` (elements)<br>`SelectFieldTypes.java` (types)<br>`ui/components/select/` (empty folder) | Implement component class based on your AUT |
| `TABLE` | BASIC or ADVANCED | `Tables.java` (elements)<br>`ExampleTableModel.java` (model/table) | Replace with your table locators and create models for table-driven tests |
| `TABLE` | AI | `Tables.java` (elements) | No model class generated; create based on your needs |

**Note:** If you don't select a component, those classes won't be generated. You can add them manually later if needed.

---

### DB (when DB module selected)

**Always generated:**

- **Databases.java** (`db/`): DB connection registry (driver, URL, credentials). Configure your database connections.
- **ExampleDbQueries.java** (`db/queries/`): Named SQL queries with parameters like `{userId}`. Replace with your actual queries.

**Database type** is pre-configured based on `-DdbType` parameter, but you can change it.

---

### Common (always generated)

**Always generated:**

- **Rings.java** (`common/base/`): Service registry entry points (API, UI, DB, CUSTOM). Wire your custom services here.
- **Data.java** (`common/data/test_data/`): Typed test data accessors. Add your test data properties.
- **DataProperties.java** (`common/data/test_data/`): Config property keys. Define your configuration keys.

**Conditionally generated** (based on `-DimplementationStyle` parameter):

| Implementation Style | Additional Files | What You Get |
|---------------------|------------------|--------------|
| **BASIC** | `CustomService.java` | Multi-step workflows across modules with example implementations |
| **ADVANCED** (default) | `CustomService.java`<br>`DataCreator.java`<br>`DataCleaner.java`<br>`Preconditions.java` | Full service and data management suite with example implementations showing factory methods, cleanup logic, and reusable precondition flows |
| **AI** | `DataCreator.java` (empty)<br>`DataCleaner.java` (empty)<br>`Preconditions.java` (empty) | Empty enum structures ready for AI implementation. No `CustomService.java` generated. |

**Note:** AI style does not generate `CustomService.java` — implement this based on your AUT requirements.

---

## Post-Generation Checklist

- Set config in `src/main/resources/config.properties` (base URLs, DB, auth).
- Replace `ExampleEndpoints` and DTOs with your real API models.
- Update UI element classes and `ExampleAppUiLogin` for your app screens and auth.
- Define real queries in `ExampleDbQueries` and connections in `Databases`.
- **For BASIC/ADVANCED styles:** Review and customize example implementations in data creators, cleaners, preconditions, and custom service.
- **For AI style:** Implement empty enum structures and create missing service/component classes based on your AUT.
- Remove or disable example tests until wired to real systems (`@Disabled` or delete). **Note:** AI style does not generate example tests.
- Run `mvn test` to confirm the scaffold compiles; expect to adjust or disable examples until your real endpoints, UI locators, and DB are configured.

---

## Example Tests

### For BASIC and ADVANCED Implementation Styles

After generating your project with BASIC or ADVANCED style, you'll find example tests demonstrating framework usage for the modules you selected:

#### API Tests (GettingStartedApiTest.java)

Shows how to:
- Use `@AuthenticateViaApi` for API authentication
- Use `@Craft` annotation with `DataCreator` for test data
- Use `@Journey` for preconditions
- Use `@Ripper` for cleanup
- Make API requests with `quest.use(RING_OF_API).requestAndValidate(...)`
- Work with `Late<T>` parameters for runtime-dependent data
- Validate status, headers, and response bodies

**Example structure:**
```java
@API
@DisplayName("EXAMPLE - Getting started API test (delete me)")
public class GettingStartedApiTest extends BaseQuest {

    @Test
    @Regression
    @AuthenticateViaApi(credentials = ExampleCredentials.class, type = ExampleAuthenticationClient.class)
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, order = 1)
    @Ripper(targets = {DataCleaner.Data.EXAMPLE_CLEANUP})
    void exampleAPITest(Quest quest,
                        @Craft(model = DataCreator.Data.EXAMPLE_MODEL) ExampleRequestDto payload) {
        quest.use(RING_OF_API)
            .requestAndValidate(
                ExampleEndpoints.EXAMPLE_POST,
                payload,
                Assertion.builder().target(STATUS).type(IS).expected(SC_CREATED).build()
            )
            .complete();
    }

    @Test
    void customFlowDemonstration(Quest quest) {
        // Commented example showing RING_OF_CUSTOM usage
    }
}
```

---

#### UI Tests (GettingStartedUiTest.java)

Shows how to:
- Use `@AuthenticateViaUi` for UI authentication with session caching
- Use `@InterceptRequests` for network traffic interception
- Use `@Journey` for preconditions
- Use `@Ripper` for cleanup
- Navigate and interact with UI components using `quest.use(RING_OF_UI)`
- Work with buttons, inputs, selects, and tables
- Validate intercepted requests

**Example structure:**
```java
@UI
@DisplayName("EXAMPLE - Getting started UI test (delete me)")
public class GettingStartedUiTest extends BaseQuest {

    @Test
    @Regression
    @AuthenticateViaUi(credentials = ExampleCredentials.class, type = ExampleAppUiLogin.class)
    @InterceptRequests(requestUrlSubStrings = {RequestsInterceptor.Data.EXAMPLE_INTERCEPT})
    @Journey(value = Preconditions.Data.EXAMPLE_PRECONDITION, order = 1)
    @Ripper(targets = {DataCleaner.Data.EXAMPLE_CLEANUP})
    void exampleUITest(Quest quest) {
        quest.use(RING_OF_UI)
            .browser().navigate(getUiConfig().baseUrl())
            .button().click(ButtonFields.LOGIN_BUTTON)
            .input().insert(InputFields.USERNAME, "example_username")
            .select().selectOption(SelectFields.GENERIC_SELECT, "example_select")
            .getInterceptor().validateResponseHaveStatus(
                RequestsInterceptor.EXAMPLE_INTERCEPT.getEndpointSubString(), 2, true)
            .complete();
    }

    @Test
    void customFlowDemonstration(Quest quest) {
        // Commented example showing RING_OF_CUSTOM usage
    }
}
```

---

#### DB Tests (GettingStartedDbTest.java)

Shows how to:
- Use `@DB` annotation to enable database capabilities
- Execute queries using `quest.use(RING_OF_DB).query(...)`
- Pass parameters to queries with `.withParam(...)`
- Validate query results using JSONPath expressions
- Access query responses from storage

**Example structure:**
```java
@DB
@DisplayName("EXAMPLE - Getting started DB test (delete me)")
public class GettingStartedDbTest extends BaseQuest {

    @Test
    @Regression
    void exampleDBTest(Quest quest) {
        quest.use(RING_OF_DB)
            .query(ExampleDbQueries.SIMPLE_QUERY.withParam("id", 1))
            .complete();
    }

    @Test
    void customFlowDemonstration(Quest quest) {
        // Commented example showing RING_OF_CUSTOM usage
    }
}
```

---

#### What to Do with Example Tests

**These tests include TODO comments:** `// TODO: EXAMPLE TEST — delete this class after reviewing.`

**Review the examples to learn:**
- How to structure your tests
- Which annotations to use
- How to chain Quest operations
- Pattern for data management with `@Craft`, `@Journey`, `@Ripper`

**Then:**
- Delete or `@Disable` the example tests
- Create your own tests based on your application under test
- Reuse the patterns shown in the examples

---

### For AI Implementation Style

**No example tests are generated** when using AI style. The structure is ready for AI to generate tests based on your Application Under Test specifications.

You should:
1. Implement the empty enum structures (`DataCreator`, `DataCleaner`, `Preconditions`)
2. Create `CustomService` if needed for your workflows
3. Implement component classes if UI components were selected
4. Write tests from scratch or have AI generate them based on your AUT instructions

---
