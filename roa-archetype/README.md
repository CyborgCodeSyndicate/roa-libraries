# ROA Test Framework Archetype

Quick-start your ROA test automation project. This archetype generates a complete, working test framework with everything wired up - you just need to point it at your application.

## What You Get

After generating a project, you'll have a ready-to-run test framework with:

### Test Structure
- **API Tests**: Make HTTP requests, validate responses, chain multiple calls
- **UI Tests**: Control browsers, interact with elements, verify page states  
- **Database Tests**: Run queries, validate data, set up test scenarios
- **Custom Flows**: Bundle complex operations into reusable workflows

### Key Classes (What to Customize)

#### API Module (if selected)

**Endpoints & Configuration**:
- `ExampleEndpoints.java` - Registry of your API endpoints. Define HTTP method, path, headers, and defaults for each endpoint (GET /users, POST /orders, DELETE /sessions, etc.)

**Data Models**:
- `ExampleRequestDto.java` - Request body models. Create one for each endpoint that accepts a payload (CreateUserRequest, UpdateOrderRequest, etc.)
- `ExampleResponseDto.java` - Response body models. Map these to your API's JSON responses for type-safe access

**Authentication**:
- `ExampleAuthenticationClient.java` - Handles getting auth tokens/cookies. Implements your login flow and returns the auth header
- `ExampleCredentials.java` - Provides username/password from config. Connects to your test data properties

#### UI Module (if selected)

**Page Elements** (define your app's UI):
- `InputFields.java` - Text inputs (username field, search box, email input, etc.)
- `ButtonFields.java` - Clickable elements (submit button, cancel link, menu items, etc.)
- `SelectFields.java` - Dropdowns (country selector, status filter, etc.)

**Element Types** (customize how elements behave):
- `InputFieldTypes.java` - Input variants (standard text, password-masked, autocomplete, etc.)
- `ButtonFieldTypes.java` - Button variants (primary, secondary, icon-only, etc.)
- `SelectFieldTypes.java` - Select variants (single, multi-select, searchable, etc.)

**Authentication & Flows**:
- `ExampleAppUiLogin.java` - Your UI login workflow. Fill in the steps to log into your app through the browser
- `ExampleCredentials.java` - UI credentials provider. Same as API but for browser login

**Advanced Features**:
- `ExampleTableModel.java` - Form auto-fill models. Annotate fields with `@InsertionElement` to auto-fill entire forms
- `RequestsInterceptor.java` - Network capture. Intercept AJAX calls during UI tests to validate API responses
- `SharedUi.java` - Reusable UI functions. Common operations like "wait for spinner" or "dismiss popup"

#### DB Module (if selected)

**Configuration**:
- `Databases.java` - Database connection registry. Maps database names to JDBC drivers and connection strings

**Queries**:
- `ExampleDbQueries.java` - SQL query registry. Define all queries here with named parameters like `{userId}`, then use `.withParam("userId", 123)` in tests

#### Common Module

**Configuration & Shared Logic** (Always included):
- `Rings.java` - Service registry. Central access to all test capabilities (RING_OF_API, RING_OF_UI, RING_OF_DB, RING_OF_CUSTOM)
- `CustomService.java` - Complex workflows. Bundle multi-step operations that span UI, API, and DB
- `DataProperties.java` - Strongly-typed config. Define keys for test data like usernames, API keys, feature flags
- `Data.java` - Config accessor. Single entry point to get config values

**Test Data Management** (Requires `commonFeatures=ADVANCED`):
- `DataCreator.java` - Test data factories. Create instances of DTOs, models, or any test objects with randomized or specific values
- `DataCleaner.java` - Cleanup operations. Delete created users, reset database state, clear caches
- `Preconditions.java` - Setup steps. Reusable preconditions like "create admin user" or "seed product catalog"

### Example Test

Here's what a test looks like:

```java
@Test
void createUser(Quest quest, 
                @Craft(model = DataCreator.Data.USER_DATA) UserDto user) {
    
    quest.use(RING_OF_API)
        .requestAndValidate(
            UserEndpoints.CREATE_USER,
            user,
            Assertion.builder().target(STATUS).type(IS).expected(201).build()
        )
        .complete();
}
```

## How to Generate Your Project

### Option 1: Command Line (Quick)

```bash
mvn archetype:generate \
  -DarchetypeGroupId=io.cyborgcode.roa.example \
  -DarchetypeArtifactId=roa-archetype \
  -DarchetypeVersion=1.2.0 \
  -DarchetypeRepository=https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries \
  -DgroupId=com.mycompany \
  -DartifactId=my-tests \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=com.mycompany \
  -Dmodules=API,UI,DB \
  -DcommonFeatures=ADVANCED \
  -DdbType=POSTGRES \
  -B
```

**What the parameters mean:**
- `modules` - Which test types you need: `API`, `UI`, `DB` (comma-separated)
- `commonFeatures` - `BASIC` or `ADVANCED` (adds data creators, preconditions, etc.)
- `dbType` - Your database: `POSTGRES`, `MYSQL`, `H2`, `ORACLE`, `SQLSERVER`, `MARIADB`
- `uiComponents` - UI elements to include: `BUTTON`, `INPUT`, `SELECT` (or combinations)
- `-B` - Batch mode (no prompts, uses defaults)

Drop `-B` if you want interactive prompts where you can just hit Enter to accept defaults.

### Option 2: IntelliJ IDEA (Visual)

1. **Set up Maven catalog**:
   - File → New Project → Maven Archetype → Manage Catalogs
   - Add new catalog:
     - Name: `roa-archetype`
     - URL: `https://cyborgcodesyndicate.github.io/roa-libraries/test/roa-archetype/archetype-catalog.xml`
     - Type: Remote

2. **Create project**:
   - File → New Project → Maven Archetype
   - Select `roa-archetype` from catalog dropdown
   - Fill in your project details (groupId, artifactId, etc.)
   - Customize properties or use defaults
   - Click Finish

## Prerequisites

You'll need:
- **Maven** installed (`mvn -v` to check)
- **Access to ROA libraries** (GitHub Packages)
- **Maven settings.xml** with credentials:

```xml
<settings>
  <servers>
    <server>
      <id>roa-ghp</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_PAT</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>roa-ghp</id>
      <repositories>
        <repository>
          <id>roa-ghp</id>
          <url>https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries</url>
        </repository>
      </repositories>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>roa-ghp</activeProfile>
  </activeProfiles>
</settings>
```

## After Generation

1. **Configure your application**:
   - Open `src/main/resources/config.properties`
   - Set your app's base URL, database connection, etc.

2. **Replace examples with your real code**:
   - API: Update `ExampleEndpoints` with your endpoints
   - UI: Define your elements in `InputFields`, `ButtonFields`, etc.
   - DB: Add your queries to `ExampleDbQueries`

3. **Run the example tests**:
   ```bash
   mvn test
   ```
   They'll fail (since they point to example.com) but show you the framework works.

4. **Start writing your tests**:
   - Use the `GettingStarted*Test` files as templates
   - Create new test classes for your features

## Project Structure Highlights

```
my-tests/
├── src/main/java/
│   ├── api/                      # API test components
│   │   ├── ExampleEndpoints.java
│   │   ├── dto/
│   │   └── authentication/
│   ├── ui/                       # UI test components
│   │   ├── elements/             # Page elements (buttons, inputs, etc.)
│   │   ├── types/                # Element types
│   │   └── authentication/
│   ├── db/                       # Database test components
│   │   ├── Databases.java
│   │   └── queries/
│   └── common/                   # Shared components
│       ├── data/                 # Data factories and cleanup
│       ├── preconditions/        # Reusable setup steps
│       └── service/              # Custom workflows
└── src/test/java/
    ├── api/
    │   ├── GettingStartedApiTestBasic.java
    │   └── GettingStartedApiTestAdvanced.java
    ├── ui/
    └── db/
```

## Tips

- **Start small**: Generate with just one module (API or UI) first
- **Use BASIC features** initially, add ADVANCED later when you need data factories
- **The example tests won't run** against real apps - they're templates showing you the patterns
- **Check the Javadoc** - every class has comments explaining what to do

## Need Help?

- Check the example test files - they show common patterns
- Look at the Javadoc in the generated classes
- All the `Example*` files are meant to be replaced with your own
