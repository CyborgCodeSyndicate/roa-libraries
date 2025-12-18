# ROA Test Framework Archetype

## Table of Contents
- [Archetype Setup](#archetype-setup)
- [Quick Start](#quick-start)
- [Generation Matrix](#generation-matrix)
- [Examples](#examples)
- [Output Expectations](#output-expectations)
- [What You Get](#what-you-get)
- [Key Classes (What to Customize)](#key-classes-what-to-customize)
- [Post-Generation Checklist](#post-generation-checklist)
- [Example Test](#example-test)

## Archetype Setup

To use this archetype easily in your IDE or via CLI without remembering long repository URLs, add our remote catalog.

### Remote Catalog URL
```
https://cyborgcodesyndicate.github.io/roa-libraries/{workflow.run_number}/roa-archetype-catalog/archetype-catalog.xml
```

### 💻 IntelliJ Integration
1. Go to **File** -> **New** -> **Project**
2. Select **Maven Archetype**
3. Click the **Manage Catalogs...** button
4. In the dialog that appears:
    - Click **Add (+)** and paste the Remote Catalog URL
    - Give it a name (e.g., "CCS" or "ROA")
    - Click **OK** to save the catalog
5. Back in the "New Project" dialog:
    - Select your newly added catalog from the **Catalog** dropdown
    - The `roa-archetype` should appear in the list below
    - Select it to see the available versions
6. Configure your project:
    - **GroupId**: Your organization's group ID (e.g., com.mycompany)
    - **ArtifactId**: Your project's name
    - **Version**: Select the desired version
    - **Additional Properties**: Customize as needed:
        - `modules`: Select which components to include (API, UI, DB)
        - `commonFeatures`: Choose between BASIC or ADVANCED
        - `dbType`: Select your database type if using DB module
        - `uiComponents`: Choose UI components to include
7. Click **Next** to proceed with project generation
8. 
## Quick Start
CLI (batch, API+UI+DB, advanced data, Postgres):
```bash
mvn archetype:generate ^
  -DarchetypeGroupId=io.cyborgcode.roa.example ^
  -DarchetypeArtifactId=roa-archetype ^
  -DarchetypeVersion=2.0.0 ^
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

Or using the catalog URL:
```bash
mvn archetype:generate ^
  -DarchetypeCatalog=https://cyborgcodesyndicate.github.io/roa-libraries/roa-archetype-catalog/latest/archetype-catalog.xml ^
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

## Generation Matrix
| Property | Description | Allowed values | Default |
| --- | --- | --- | --- |
| modules | Capabilities to include | API, UI, DB (comma-separated) | API,UI,DB |
| commonFeatures | Test data/preconditions bundle | BASIC, ADVANCED | BASIC |
| dbType | DB flavor (when DB selected) | POSTGRES, MYSQL, H2, ORACLE, SQLSERVER, MARIADB | POSTGRES |
| environments | Target environments (comma-separated) | any string | QA,UAT |
| uiComponents | UI element families (when UI selected) | BUTTON, INPUT, SELECT (comma-separated) | BUTTON,INPUT,SELECT |
| groupId | Maven groupId | any | (required) |
| artifactId | Maven artifactId | any | (required) |
| version | Project version | any | 1.0-SNAPSHOT |
| package | Base package for sources | any | matches groupId |

## Environment Configuration

When you specify environments (e.g., `-Denvironments=QA,UAT`), the following files are generated for each environment:
- `config-{env}.properties` - Environment-specific configuration
- `test-data-{env}.properties` - Environment-specific test data

### Configuration Properties (`config-{env}.properties`)

The configuration properties vary based on the selected modules:

#### Common Properties
- `project.package` - Base package for the project

#### API Module (included when `modules` includes `API`)
- `api.restassured.logging.enabled` - Enable/disable REST Assured logging
- `api.restassured.logging.level` - Logging level for REST Assured
- `api.base.url` - Base URL for API endpoints

#### DB Module (included when `modules` includes `DB`)
- `db.default.name` - Default database name
- `db.default.type` - Database type (H2, MYSQL, POSTGRES, etc.)
- `db.default.username` - Database username
- `db.default.password` - Database password
- `db.full.connection.string` - Full JDBC connection string

#### UI Module (included when `modules` includes `UI`)
- `ui.base.url` - Base URL for the web application
- `browser.type` - Browser type (e.g., CHROME, FIREFOX)
- `browser.version` - Browser version (optional)
- `headless` - Run browser in headless mode
- `wait.duration.in.seconds` - Default wait timeout
- `remote.driver.url` - URL for remote WebDriver
- `screenshot.on.passed.test` - Take screenshots on test pass
- `use.shadow.root` - Enable/disable Shadow DOM support
- `use.wrap.selenium.function` - Enable/disable Selenium function wrapping

#### UI Components (included based on `uiComponents` selection)
- `input.default.type` - Default input type (if INPUT component is selected)
- `button.default.type` - Default button type (if BUTTON component is selected)
- `select.default.type` - Default select type (if SELECT component is selected)
- `table.default.type` - Default table type

### Test Data (`test-data-{env}.properties`)

The test data properties file includes environment-specific test data. The default template includes:

- `example.username` - Example username
- `example.value` - Example value

You can add your own test data properties as needed for your tests. These properties can be accessed in your tests using the `DataProperties` class.

### Example Environment Files

For `QA` environment with all modules enabled:

```properties
# config-qa.properties
project.package=com.mycompany
api.restassured.logging.enabled=true
api.base.url=https://qa-api.example.com
db.default.name=qa_database
db.default.type=MYSQL
db.default.username=qa_user
db.default.password=qa_password
ui.base.url=https://qa-ui.example.com
browser.type=CHROME
headless=true
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
