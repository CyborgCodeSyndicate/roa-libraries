# AI Compass annotations

This branch replaces pandora-core with the Java annotation SDK from Pandora Optimizer:

```xml
<dependency>
    <groupId>io.cyborgcode.pandora</groupId>
    <artifactId>pandora-annotations</artifactId>
    <version>0.1.0</version>
</dependency>
```

Install the SDK locally from the sibling pandora-optimizer repository before building. It has not been published remotely by this migration. Use JDK 21 for this repository's existing Lombok build configuration.

## API guidance

```java
@AiCompass(
      description = "Describe the API contract and when to use it.",
      tags = {"api"},
      creation = CreationKind.ENUM_CONSTANT
)
@AiCompassOptions(
      implementationsOf = Endpoint.class,
      meta = {@AiCompassOptions.Meta(key = "type", value = "endpoint")}
)
public interface Endpoint<T extends Enum<T>> {
    // The original API contract remains unchanged.
}
```

Import these types from io.cyborgcode.pandora.annotations.

- Put AiCompass on types, methods, constructors, fields and parameters.
- Preserve meaningful descriptions, tags, creation hints and preferences.
- Use enumType = SomeEnum.class for a fixed enum.
- Use implementationsOf = Contract.class for enum implementations supplied by this framework or the consuming application. The scanner follows interface inheritance.
- render = "NAME" produces enum names. render = "QUALIFIED" produces qualified type/constant names.
- AiCompassOptions.Meta preserves the original key/value metadata.
- A parameter description does not suppress its type's option declaration.
- AiLesson is a separate annotation for curated repository examples.

Usage-file references have been removed. No usage examples are required to generate or consume API/options metadata. Optional examples can be added later through the resource-provider system.

## Add library-owned usage JSONs

Usage files may live in this repository, for example under
`pandora/usages/`. Register a file path or raw-JSON HTTPS URL in this
repository's existing config:

```json
"resources": {
  "rest-service-usages": {
    "uri": "pandora/usages/rest-service.json"
  }
}
```

Then add `examples = {"rest-service-usages"}` to the relevant existing
`@AiCompass` annotation. The annotation uses the resource ID; the config holds
its path or URL. Keep the annotation's current descriptions, options and other
fields. Use a raw/download JSON URL rather than a GitHub HTML file page.

The usage JSON has an `items` array. Each entry's `id` selects an exact symbol
ID, qualified member name or signature, and its `usages` array contains example
objects such as `language`, `code`, `description` and `level`. Use exact IDs
from `navigator search` to distinguish overloads.

Scan this library and export it with `navigator bundle --output <fresh-file>`.
Matching JSON entries are copied into the catalog's `usageExamples`. After a
target repository imports that catalog, AI Navigator can read those examples
without access to this checkout or the original JSON URLs. Producer Java
source availability and embedded usage availability are separate.

When usage files change, regenerate/publish the bundle and refresh the target's
catalog. The current annotations still work without usage files; this guide
does not add references to files that have not yet been authored.

## Generate context

From this repository, after building Optimizer:

```powershell
..\pandora-optimizer\pandora.cmd scan
..\pandora-optimizer\pandora.cmd navigator search --query "RestService request"
```

The checked-in config scans the eight production source roots. External JAR references are optional and are currently omitted; unresolved external types remain visible as diagnostics.

From Pandora Optimizer, refresh the example consumer snapshot:

```powershell
python scripts/refresh_roa_context.py --framework ../roa-libraries --consumer ../roa-example-projects
```

The original custom reflection helpers are represented by declarative options and no longer need to ship in the framework. The application dependency remains only the annotation SDK; the CLI stays outside the application's classpath.
