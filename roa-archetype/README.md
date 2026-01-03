# ROA Test Framework Archetype

## Table of Contents
- [Archetype Setup](#archetype-setup)
- [IntelliJ IDEA Integration](#-instructions-how-to-create-a-new-project-from-archetype-via-intellij)
- [Command Line Creation](#instructions-on-how-to-create-project-from-archetype-via-command-line)
- [Generation Matrix](#generation-matrix)
- [Environment Configuration](#environment-configuration)
- [What You Get](#what-you-get)
- [Key Classes (What to Customize)](#key-classes-what-to-customize)
- [Post-Generation Checklist](#post-generation-checklist)
- [Example Test](#example-test)

## Archetype Setup

To use this archetype easily in your IDE or via CLI without remembering long repository URLs, add our remote catalog.

### Remote Catalog URL 
`Update the {version} with a deployed archetype catalog version from gh-pages. 
You can find all deployed versions under the` **roa-archetype-catalog** `folder.
Select one and then populate {version} in the url below when creating the remote catalog.`

**REMOTE CATALOG URL**
```
https://cyborgcodesyndicate.github.io/roa-libraries/roa-archetype-catalog/{version}/archetype-catalog.xml
```

### 💻 Instructions how to create a new project from archetype via IntelliJ
1. Go to **File** -> **New** -> **Project**
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
        - `modules`: Select which components to include (API, UI, DB)
        - `environments`: Add your environments here or leave empty if working on only one env (ex. uat, qa, test, dev)
        - `implementationStyle`: Choose between BASIC, ADVANCED, or AI
        - `dbType`: Select your database type if using DB module (ex. H2, POSTGRES, MYSQL...)
        - `uiComponents`: Choose UI components to include (BUTTON, SELECT, INPUT, TABLE)
7. Click **Next** to proceed with project generation
8. After generation build the newly created project


## Instructions on how to create project from archetype via command line
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
  -DimplementationStyle=ADVANCED ^
  -DdbType=POSTGRES ^
  -DuiComponents=BUTTON,INPUT,SELECT,TABLE ^
  -B
```

Or using the catalog URL (update the {workflow.run_number} with a real number from gh-pages):
```bash
mvn archetype:generate ^
  -DarchetypeCatalog=https://cyborgcodesyndicate.github.io/roa-libraries/{workflow.run_number}/roa-archetype-catalog/archetype-catalog.xml ^
  -DgroupId=com.mycompany ^
  -DartifactId=my-tests ^
  -Dversion=1.0-SNAPSHOT ^
  -Dpackage=com.mycompany ^
  -Dmodules=API,UI,DB ^
  -DimplementationStyle=ADVANCED ^
  -DdbType=POSTGRES ^
  -DuiComponents=BUTTON,INPUT,SELECT ^
  -B
```

## Generation Matrix
| Property | Description | Allowed values | Default                   |
| --- | --- | --- |---------------------------|
| modules | Capabilities to include | API, UI, DB (comma-separated) | API,UI,DB                 |
| implementationStyle | Test data/preconditions bundle | BASIC, ADVANCED, AI | ADVANCED                  |
| dbType | DB flavor (when DB selected) | POSTGRES, MYSQL, H2, ORACLE, SQLSERVER, MARIADB | POSTGRES                  |
| environments | Target environments (comma-separated) | any string | qa,uat                    |
| uiComponents | UI element families (when UI selected) | BUTTON, INPUT, SELECT, TABLE (comma-separated) | BUTTON,INPUT,SELECT,TABLE |
| groupId | Maven groupId | any | (required)                |
| artifactId | Maven artifactId | any | (required)                |
| version | Project version | any | 1.0-SNAPSHOT              |
| package | Base package for sources | any | matches groupId           |

## Environment Configuration

When you specify environments (e.g., `-Denvironments=qa,uat`), the following files are generated for each environment:
- `config-{env}.properties` - Environment-specific configuration
- `test-data-{env}.properties` - Environment-specific test data

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
- DataCreator.java / DataCleaner.java / Preconditions.java (when implementationStyle=ADVANCED): Factories, cleanup hooks, reusable setup.

## Post-Generation Checklist
- Set config in src/main/resources/config.properties (base URLs, DB, auth).
- Replace ExampleEndpoints and DTOs with your real API models.
- Update UI element classes and ExampleAppUiLogin for your app screens and auth.
- Define real queries in ExampleDbQueries and connections in Databases.
- Decide on data features: flesh out DataCreator/DataCleaner/Preconditions or remove if unused.
- Remove or disable example tests until wired to real systems (@Disabled or delete).
- Run mvn test to confirm the scaffold compiles; expect to adjust or disable examples until your real endpoints, UI locators, and DB are configured.
