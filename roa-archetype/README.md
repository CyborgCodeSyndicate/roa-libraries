# ROA Archetype - Project Generation Guide

This archetype generates a ready-to-run ROA test framework project (API/UI/DB) with sample code. 
Use the steps below to generate from the terminal or IntelliJ IDEA.

## Prerequisites
- Maven installed (`mvn -v`).
- Access to the Maven repo hosting the archetype (GitHub Packages).
- `settings.xml` with credentials for that repo, for example:
  ```xml
  <settings>
    <servers>
      <server>
        <id>roa-ghp</id>
        <username>YOUR_GH_USERNAME</username>
        <password>YOUR_GH_PAT</password>
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

## Required Inputs (typical values)
- `modules`: `API,UI,DB` (comma-separated, no spaces). Case-insensitive.
- `commonFeatures`: `BASIC` or `ADVANCED` (default is BASIC if missing).
- `environments`: e.g., `dev,test` (comma-separated). Leave blank for default config/test_data.
- `uiComponents`: e.g., `BUTTON,SELECT,INPUT` (you can only choose one ex. BUTTON or none or combination of them).
- `dbType`: one of `H2` (default if blank), `POSTGRES`, `MYSQL`, `ORACLE`, `SQLSERVER`, `MARIADB`.
- UI components are always included when UI is selected (no uiComponents prompt needed).

## Generate from Terminal (PowerShell)
Use quotes around `-D` values that contain commas, and put a backtick at the end of each line. Replace the repo URL/version if yours differ.
```powershell
mvn archetype:generate `
  "-DarchetypeGroupId=io.cyborgcode.roa.example" `
  "-DarchetypeArtifactId=roa-archetype" `
  "-DarchetypeVersion=1.2.0" `
  "-DarchetypeRepository=https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries" `
  "-DgroupId=com.mycompany.example" `
  "-DartifactId=my-roa-project" `
  "-Dversion=1.0-SNAPSHOT" `
  "-Dpackage=com.mycompany.example" `
  "-Dmodules=API,UI,DB" `
  "-DcommonFeatures=ADVANCED" `
  "-Denvironments=dev,test" `
  "-DbrowserType=CHROME" `
  "-Dheadless=true" `
  "-DbaseUrls=https://example.com" `
  "-DdbType=POSTGRES" `
  -B
```
- `-B` = batch mode; prompts are skipped and defaults are shown. Drop `-B` for interactive prompts (press Enter to accept defaults).
- If Maven cannot authenticate, add `-s path\to\settings.xml` to point at the correct settings file.

## Generate from IntelliJ IDEA (with a remote catalog)
1) Ensure IDEA/Maven picks up your `settings.xml` (with GitHub Packages credentials).
2) File -> New Project -> Maven Archetype -> click **Manage Catalogs...** (next to Catalog).
3) Add new catalog (name it ex. `roa-archetype`) with url: `https://cyborgcodesyndicate.github.io/roa-libraries/test/roa-archetype/archetype-catalog.xml` (Type will be remote) 
4) In the dropdown below select `roa-archetype` (version field will be defaulted of the parent pom version)
5) Properties with default values will be displayed (modules, commonFeatures, environments, dbType, etc.). 
6) Change any of them if needed and finish.
7) Wait for the project to be generated and opened.
