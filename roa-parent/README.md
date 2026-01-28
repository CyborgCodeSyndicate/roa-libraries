# ROA Parent POM

Maven parent POM for all testing frameworks using Ring of Automation. Centralizes dependency version management, plugin configuration, and default test execution properties.

## Purpose

This parent POM provides:
- **Dependency version management** for all ROA libraries (test-framework, api-interactor, db-interactor, ui-interactor, assertions, adapters)
- **Transitive dependency management** via `io.cyborgcode.utilities:parent-pom`
- **Pre-configured Maven plugins** (compiler, surefire, test-allocator)
- **Default properties** for test execution, logging, and Allure reporting
- **GitHub package repositories** for ROA artifacts

## Usage

Add as parent in your test framework POM:

<details>
<summary>Maven parent</summary>

```xml
<parent>
    <groupId>io.cyborgcode.roa</groupId>
    <artifactId>roa-parent</artifactId>
    <version>${revision}</version>
</parent>
```

</details>

All ROA library versions are automatically managed. Simply declare dependencies without versions:

<details>
<summary>Maven dependency</summary>

```xml
<dependencies>
    <dependency>
        <groupId>io.cyborgcode.roa</groupId>
        <artifactId>test-framework</artifactId>
    </dependency>
    <dependency>
        <groupId>io.cyborgcode.roa</groupId>
        <artifactId>api-interactor-test-framework-adapter</artifactId>
    </dependency>
</dependencies>
```

</details>

## Key Properties

| Property | Default | Description |
|----------|---------|-------------|
| `java.version` | 17 | Java version for compilation |
| `skipTests` | false | Skip test execution |
| `include.tags` | (empty) | JUnit tags to include |
| `exclude.tags` | exclude-from-verify | JUnit tags to exclude |
| `surefire.forkCount` | 1C | Parallel test execution (1 fork per CPU core) |
| `allure.results.dir` | target/allure-results | Allure results directory |
| `framework.config.file` | config | Framework configuration file name |
| `logFileName` | roa.log | Log file name |
| `extended.logging` | false | Enable extended logging |
| `silent.mode` | false | Enable silent mode |

Override any property via `-D` flag or in your POM:

<details>
<summary>Run command</summary>

```bash
mvn clean test -Dinclude.tags=Regression -Dextended.logging=true
```

</details>

## Configured Plugins

### maven-compiler-plugin (3.14.1)
- Java 17 compilation
- UTF-8 encoding
- Parameter names retention
- Fork compilation for performance

### maven-surefire-plugin (3.5.4)
- Parallel test execution (1C fork count)
- JUnit 5 tag filtering
- Allure integration
- Configuration file system properties injection

### test-allocator-maven-plugin (1.0.0-rc-1)
- Test distribution for CI/CD pipelines
- Activated via `execution-setup` profile

## Profiles

### e2e
Executes all tests (clears `exclude.tags`):
<details>
<summary>Run command</summary>

```bash
mvn clean test -Pe2e
```

</details>

### execution-setup
Activates test-allocator-maven-plugin for distributed test execution:
<details>
<summary>Run command</summary>

```bash
mvn clean test -Pexecution-setup
```

</details>

## Managed Dependencies

All ROA library artifacts:
- `assertions`
- `test-framework`
- `api-interactor` / `api-interactor-test-framework-adapter`
- `db-interactor` / `db-interactor-test-framework-adapter`
- `ui-interactor` / `ui-interactor-test-framework-adapter`

Plus transitive dependencies from `io.cyborgcode.utilities:parent-pom`.

## GitHub Repositories

Pre-configured repositories:
- `github-utilities` - Utilities packages
- `github-roa-libraries` - ROA libraries
- `github-roa-plugins` - ROA Maven plugins

**Authentication required:** Configure GitHub token in `~/.m2/settings.xml`:

<details>
<summary>Maven settings.xml</summary>

```xml
<servers>
    <server>
        <id>github-roa-libraries</id>
        <username>YOUR_GITHUB_USERNAME</username>
        <password>YOUR_GITHUB_TOKEN</password>
    </server>
</servers>
```

</details>

## Author
**Cyborg Code Syndicate 💍👨💻**
