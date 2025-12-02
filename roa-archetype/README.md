# ROA Archetype - Project Generation Guide

This archetype generates a ready-to-run ROA test framework project (API/UI/DB) with sample code. Use the steps below to generate from the terminal or IntelliJ.

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
- `baseUrls`: e.g., `https://example.com,https://test.example.com` (aligns by index to environments; blanks allowed).
- `dbType`: one of `H2` (default if blank), `POSTGRES`, `MYSQL`, `ORACLE`, `SQLSERVER`, `MARIADB`.
- `browserType`: e.g., `CHROME` (default if not provided).
- `headless`: `true` or `false` (default true).
- Standard Maven coordinates: `groupId`, `artifactId`, `version`, `package`.
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

## Generate from IntelliJ IDEA (without a catalog)
1) Ensure IDEA/Maven picks up your `settings.xml` (with GitHub Packages credentials).
2) File -> New Project -> Maven Archetype -> click **Add...** (next to Archetype).
   - GroupId: `io.cyborgcode.roa.example`
   - ArtifactId: `roa-archetype`
   - Version: `1.x.x`
   - Repository: `https://maven.pkg.github.com/CyborgCodeSyndicate/roa-libraries`
3) Select the archetype, Next, then fill properties (modules, commonFeatures, environments, dbType, etc.). Finish.

## Common Pitfalls
- 401/404: Repo URL or credentials wrong; ensure the `<server>` id in `settings.xml` matches the repo id.
- Commas in PowerShell: Wrap `-D` values with commas in quotes.
- Catalog not needed: Adding the archetype via coordinates + repo works without a catalog file.
- Environments/baseUrls: They align by index; blanks are allowed (e.g., `dev,,prod`).

## After Generation
- Run `mvn test` in the generated project to verify setup.
- Update `config-*.properties` and `test_data-*.properties` with your real URLs/data.
