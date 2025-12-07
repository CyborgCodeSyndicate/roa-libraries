
def modulesRaw = request.properties['modules']
def rootDir = new File(request.outputDirectory, request.artifactId)
def packagePath = request.properties['package'].replace('.', '/')
def uiComponents = request.properties['uiComponents']

def environmentsRaw = request.properties['environments'] ?: ''
if (environmentsRaw == 'true') environmentsRaw = ''

def baseUrlsRaw = request.properties['baseUrls'] ?: ''
if (baseUrlsRaw == 'true') baseUrlsRaw = ''
def commonFeaturesRaw = request.properties['commonFeatures']
if (!commonFeaturesRaw || commonFeaturesRaw.trim().isEmpty()) {
    commonFeaturesRaw = 'ADVANCED'
}

def packageName = request.properties['package']
def resourcesDir = new File(rootDir, "src/main/resources")

def configTemplateFile = new File(resourcesDir, "config-template.properties")

println "=== ROA Archetype Post-Generation Script ==="
println "Input hints -> modules: API,UI,DB (comma-separated). commonFeatures: BASIC or ADVANCED. uiComponents: BUTTON,INPUT,SELECT."
println "Selected modules: ${modulesRaw}"
println "Root directory: ${rootDir}"
println "Package path: ${packagePath}"
println "Selected UI components: ${uiComponents}"

println "Creating config files for environments: ${environmentsRaw}"
println "Common features level (set -DcommonFeatures=BASIC or ADVANCED): ${commonFeaturesRaw}"

def allowedModules = ['API','UI','DB'] as Set
def selectedModules = modulesRaw?.split(',')*.trim()*.toUpperCase().findAll { allowedModules.contains(it) } ?: []
if (selectedModules.isEmpty()) {
    println "  WARNING: No valid modules provided. Defaulting to API,UI,DB."
    selectedModules = ['API','UI','DB']
}

def commonFeatures = commonFeaturesRaw?.trim()?.toUpperCase()
if (!commonFeatures || !(commonFeatures in ['BASIC', 'ADVANCED'])) {
    println "  WARNING: Invalid or missing commonFeatures '${commonFeaturesRaw}'. Defaulting to BASIC."
    commonFeatures = 'BASIC'
}
def isBasicCommons = commonFeatures == 'BASIC'

def allowedUiComponents = ['BUTTON','INPUT','SELECT'] as Set
def selectedUI = uiComponents?.split(',')*.trim()*.toUpperCase().findAll { allowedUiComponents.contains(it) } ?: []

def testDataTemplateFile = new File(resourcesDir, "test_data-template.properties")

println "Parsed modules: ${selectedModules}"

def keepUI = selectedModules.contains('UI')
def keepAPI = selectedModules.contains('API')
def keepDB = selectedModules.contains('DB')

def commonsMode = isBasicCommons ? 'BASIC' : 'ADVANCED'
println "Keep UI: ${keepUI}"
println "Keep API: ${keepAPI}"
println "Keep DB: ${keepDB}"
println "Commons mode: ${commonsMode}"
if (!keepUI) {
    println "UI module not selected; uiComponents input will be ignored."
    println "UI module not selected; browserType/headless inputs will be ignored."
}
if (!keepDB) {
    println "DB module not selected; dbType input will be ignored."
}

if (keepUI) {
    println "Parsed UI components: ${selectedUI}"
}

if (!keepUI) {
    println "Removing UI module..."

    def uiTestDir = new File(rootDir, "src/test/java/${packagePath}/ui")
    if (uiTestDir.exists()) {
        uiTestDir.deleteDir()
        println "  Deleted: ${uiTestDir}"
    }

    def uiMainDir = new File(rootDir, "src/main/java/${packagePath}/ui")
    if (uiMainDir.exists()) {
        uiMainDir.deleteDir()
        println "  Deleted: ${uiMainDir}"
    }
} else {
    println "Filtering UI components..."

    def componentFolders = [
            "BUTTON": ["button"],
            "INPUT" : ["input"],
            "SELECT": ["select"]
    ]

    def uiBaseMain = new File(rootDir, "src/main/java/${packagePath}/ui")
    def uiBaseElements = new File(rootDir, "src/main/java/${packagePath}/ui/elements")
    def uiBaseTypes = new File(rootDir, "src/main/java/${packagePath}/ui/types")

    componentFolders.each { key, subFolders ->
        if (!selectedUI.contains(key)) {
            println "Removing UI component: ${key}"

            subFolders.each { folder ->
                def compDir = new File(uiBaseMain, "components/${folder}")
                if (compDir.exists()) {
                    compDir.deleteDir()
                    println "  Deleted: ${compDir}"
                }
            }

            subFolders.each { folder ->
                def elementFile = new File(uiBaseElements, "${folder.capitalize()}Fields.java")
                if (elementFile.exists()) {
                    elementFile.delete()
                    println "  Deleted: ${elementFile}"
                }
            }

            def typeFile = new File(uiBaseTypes, "${key.capitalize()}FieldTypes.java")
            if (typeFile.exists()) {
                typeFile.delete()
                println "  Deleted: ${typeFile}"
            }
        }
    }
}

if (!keepAPI) {
    println "Removing API module..."

    def apiTestDir = new File(rootDir, "src/test/java/${packagePath}/api")
    if (apiTestDir.exists()) {
        apiTestDir.deleteDir()
        println "  Deleted: ${apiTestDir}"
    }

    def apiMainDir = new File(rootDir, "src/main/java/${packagePath}/api")
    if (apiMainDir.exists()) {
        apiMainDir.deleteDir()
        println "  Deleted: ${apiMainDir}"
    }
}

if (!keepDB) {
    println "Removing DB module..."

    def dbTestDir = new File(rootDir, "src/test/java/${packagePath}/db")
    if (dbTestDir.exists()) {
        dbTestDir.deleteDir()
        println "  Deleted: ${dbTestDir}"
    }

    def dbMainDir = new File(rootDir, "src/main/java/${packagePath}/db")
    if (dbMainDir.exists()) {
        dbMainDir.deleteDir()
        println "  Deleted: ${dbMainDir}"
    }
}

if (isBasicCommons) {
    println "Pruning advanced commons (Preconditions/DataCreator/DataCleaner) for BASIC mode"

    def preconditionsDir = new File(rootDir, "src/main/java/${packagePath}/common/preconditions")
    if (preconditionsDir.exists()) {
        preconditionsDir.deleteDir()
        println "  Deleted: ${preconditionsDir}"
    }

    def creatorDir = new File(rootDir, "src/main/java/${packagePath}/common/data/creator")
    if (creatorDir.exists()) {
        creatorDir.deleteDir()
        println "  Deleted: ${creatorDir}"
    }

    def cleanerDir = new File(rootDir, "src/main/java/${packagePath}/common/data/cleaner")
    if (cleanerDir.exists()) {
        cleanerDir.deleteDir()
        println "  Deleted: ${cleanerDir}"
    }

    def extractorDir = new File(rootDir, "src/main/java/${packagePath}/common/data/extractor")
    if (extractorDir.exists()) {
        extractorDir.deleteDir()
        println "  Deleted: ${extractorDir}"
    }
}

Closure selectTestVariant = { String baseName, String basicName, String advancedName, String basicClass, String advancedClass, String targetClass ->
    def baseFile = new File(rootDir, baseName)
    def basicFile = new File(rootDir, basicName)
    def advancedFile = new File(rootDir, advancedName)

    if (baseFile.exists()) {
        baseFile.delete()
    }

    if (isBasicCommons) {
        if (advancedFile.exists()) {
            advancedFile.delete()
            println "  Deleted advanced test: ${advancedFile}"
        }
        if (basicFile.exists()) {
            basicFile.renameTo(baseFile)
            def text = baseFile.text.replaceAll(basicClass, targetClass)
            baseFile.text = text
            println "  Selected basic test: ${baseFile}"
        }
    } else {
        if (basicFile.exists()) {
            basicFile.delete()
            println "  Deleted basic test: ${basicFile}"
        }
        if (advancedFile.exists()) {
            advancedFile.renameTo(baseFile)
            def text = baseFile.text.replaceAll(advancedClass, targetClass)
            baseFile.text = text
            println "  Selected advanced test: ${baseFile}"
        }
    }
}

selectTestVariant(
      "src/test/java/${packagePath}/api/GettingStartedApiTest.java",
      "src/test/java/${packagePath}/api/GettingStartedApiTestBasic.java",
      "src/test/java/${packagePath}/api/GettingStartedApiTestAdvanced.java",
      "GettingStartedApiTestBasic",
      "GettingStartedApiTestAdvanced",
      "GettingStartedApiTest"
)
selectTestVariant(
      "src/test/java/${packagePath}/db/GettingStartedDbTest.java",
      "src/test/java/${packagePath}/db/GettingStartedDbTestBasic.java",
      "src/test/java/${packagePath}/db/GettingStartedDbTestAdvanced.java",
      "GettingStartedDbTestBasic",
      "GettingStartedDbTestAdvanced",
      "GettingStartedDbTest"
)
selectTestVariant(
      "src/test/java/${packagePath}/ui/GettingStartedUiTest.java",
      "src/test/java/${packagePath}/ui/GettingStartedUiTestBasic.java",
      "src/test/java/${packagePath}/ui/GettingStartedUiTestAdvanced.java",
      "GettingStartedUiTestBasic",
      "GettingStartedUiTestAdvanced",
      "GettingStartedUiTest"
)

if (!configTemplateFile.exists()) {
    println "ERROR: Template file not found at ${configTemplateFile}"
    return
}
if (!testDataTemplateFile.exists()) {
    println "ERROR: Test data template file not found at ${testDataTemplateFile}"
    return
}

def configTemplateContent = configTemplateFile.text
def testDataTemplateContent = testDataTemplateFile.text

def envList = environmentsRaw.split(',')*.trim().findAll { it }
def urlList = baseUrlsRaw.split(',')*.trim()

def selectedTestDataFile = envList ? "test_data-${envList[0]}" : "test_data"

Closure generateConfigFile = { File targetFile, String baseUrl ->
    def processedTemplate = configTemplateContent
            .replaceFirst(/browser\.type=.*/, "browser.type=${request.properties['browserType'] ?: 'CHROME'}")
            .replaceFirst(/headless=.*/, "headless=${request.properties['headless'] ?: 'true'}")
            .replace('${package}', packageName)
            .replace('${baseUrl}', baseUrl ?: 'https://example.com')
    targetFile.text = processedTemplate
    println "  Created: ${targetFile.name} with baseUrl: ${baseUrl ?: 'https://example.com'}"
}

Closure generateTestDataFile = { File targetFile ->
    targetFile.text = testDataTemplateContent
    println "  Created: ${targetFile.name}"
}

if (envList && envList.size() > 0) {
    envList.eachWithIndex { env, index ->
        def baseUrl
        if (index < urlList.size() && urlList[index]) {
            baseUrl = urlList[index]
        } else {
            baseUrl = "https://REPLACE_WITH_${env.toUpperCase()}_URL"
        }

        generateConfigFile(new File(resourcesDir, "config-${env}.properties"), baseUrl)
        generateTestDataFile(new File(resourcesDir, "test_data-${env}.properties"))
    }

    println "Generated ${envList.size()} environment(s): ${envList.join(', ')}"
} else {
    println "No environments provided. Creating default config.properties and test_data.properties"

    def baseUrl = (urlList && urlList[0]) ? urlList[0] : 'https://REPLACE_WITH_BASE_URL'
    generateConfigFile(new File(resourcesDir, "config.properties"), baseUrl)
    generateTestDataFile(new File(resourcesDir, "test_data.properties"))
}

def generatedPom = new File(rootDir, "pom.xml")
if (generatedPom.exists()) {
    def pomText = generatedPom.text
    def replaced = pomText.replaceAll("(<test.data.file>)([^<]*)(</test.data.file>)", "\$1${selectedTestDataFile}\$3")
    if (pomText != replaced) {
        generatedPom.text = replaced
        println "  Updated pom.xml test.data.file to ${selectedTestDataFile}"
    } else {
        println "  WARNING: Could not update test.data.file in pom.xml; please set it manually."
    }
}

configTemplateFile.delete()
testDataTemplateFile.delete()
println "  Removed template files"
