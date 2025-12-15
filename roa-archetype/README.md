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

**API Module** (if selected):
- `ExampleEndpoints.java` - Define your API endpoints (GET /users, POST /orders, etc.)
- `ExampleRequestDto.java` / `ExampleResponseDto.java` - Your request/response models
- `ExampleAuthenticationClient.java` - How to authenticate (token, cookies, etc.)

**UI Module** (if selected):
- `InputFields.java` / `ButtonFields.java` / `SelectFields.java` - Your page elements (login form, search button, etc.)
- `ExampleAppUiLogin.java` - How to log into your app
- `ExampleTableModel.java` - Models for auto-filling forms

**DB Module** (if selected):
- `Databases.java` - Database connection config
- `ExampleDbQueries.java` - Your SQL queries

**Common to All**:
- `DataCreator.java` - Factories for generating test data
- `DataCleaner.java` - Cleanup operations (delete test users, reset data, etc.)
- `Preconditions.java` - Reusable setup steps (create user, seed database, etc.)
- `CustomService.java` - Your custom multi-step operations

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
