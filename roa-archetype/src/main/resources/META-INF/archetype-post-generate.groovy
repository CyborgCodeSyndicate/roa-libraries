
def modulesRaw = request.properties['modules']
def rootDir = new File(request.outputDirectory, request.artifactId)
def packagePath = request.properties['package'].replace('.', '/')
def uiComponents = request.properties['uiComponents']

def environmentsRaw = request.properties['environments'] ?: ''
if (environmentsRaw == 'true') environmentsRaw = ''

def baseUrlsRaw = request.properties['baseUrls'] ?: ''
if (baseUrlsRaw == 'true') baseUrlsRaw = ''
def implementationStyleRaw = request.properties['implementationStyle']
if (!implementationStyleRaw || implementationStyleRaw.trim().isEmpty()) {
    implementationStyleRaw = 'ADVANCED'
}

def packageName = request.properties['package']
def resourcesDir = new File(rootDir, "src/main/resources")

def configTemplateFile = new File(resourcesDir, "config-template.properties")

println "=== ROA Archetype Post-Generation Script ==="
println "Input hints -> modules: API,UI,DB (comma-separated). implementationStyle: BASIC, ADVANCED, or AI. uiComponents: BUTTON,INPUT,SELECT."
println "Selected modules: ${modulesRaw}"
println "Root directory: ${rootDir}"
println "Package path: ${packagePath}"
println "Selected UI components: ${uiComponents}"

println "Creating config files for environments: ${environmentsRaw}"
println "Implementation style (set -DimplementationStyle=BASIC, ADVANCED, or AI): ${implementationStyleRaw}"

def allowedModules = ['API','UI','DB'] as Set
def selectedModules = modulesRaw?.split(',')*.trim()*.toUpperCase().findAll { allowedModules.contains(it) } ?: []
if (selectedModules.isEmpty()) {
    println "  WARNING: No valid modules provided. Defaulting to API,UI,DB."
    selectedModules = ['API','UI','DB']
}

def implementationStyle = implementationStyleRaw?.trim()?.toUpperCase()
if (!implementationStyle || !(implementationStyle in ['BASIC', 'ADVANCED', 'AI'])) {
    println "  WARNING: Invalid or missing implementationStyle '${implementationStyleRaw}'. Defaulting to BASIC."
    implementationStyle = 'BASIC'
}
def isBasicCommons = implementationStyle == 'BASIC'
def isAiCommons = implementationStyle == 'AI'

def allowedUiComponents = ['BUTTON','INPUT','SELECT','TABLE'] as Set
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
    def uiBaseModel = new File(rootDir, "src/main/java/${packagePath}/ui/model")

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

    if (!selectedUI.contains("TABLE")) {
        println "Removing UI component: TABLE"
        
        def tableModelDir = new File(uiBaseModel, "table")
        if (tableModelDir.exists()) {
            tableModelDir.deleteDir()
            println "  Deleted: ${tableModelDir}"
        }

        def tableExampleFile = new File(uiBaseElements, "Tables.java")
        if (tableExampleFile.exists()) {
            tableExampleFile.delete()
            println "  Deleted: ${tableExampleFile}"
        }
    }

    // Cleanup empty directories (ONLY for standard modes, to keep AI folders)
    if (!isAiCommons) {
        def componentsDir = new File(uiBaseMain, "components")
        if (componentsDir.exists() && (componentsDir.list() == null || componentsDir.list().length == 0)) {
            componentsDir.delete()
            println "  Deleted empty dir: ${componentsDir}"
        }
        if (uiBaseElements.exists() && (uiBaseElements.list() == null || uiBaseElements.list().length == 0)) {
            uiBaseElements.delete()
            println "  Deleted empty dir: ${uiBaseElements}"
        }
        if (uiBaseTypes.exists() && (uiBaseTypes.list() == null || uiBaseTypes.list().length == 0)) {
            uiBaseTypes.delete()
            println "  Deleted empty dir: ${uiBaseTypes}"
        }
        if (uiBaseModel.exists() && (uiBaseModel.list() == null || uiBaseModel.list().length == 0)) {
            uiBaseModel.delete()
            println "  Deleted empty dir: ${uiBaseModel}"
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

if (isAiCommons) {
    println "Applying AI/Skeleton mode..."

    def aiReplacements = [
        "src/main/java/${packagePath}/common/base/Rings.java": "src/main/java/${packagePath}/common/base/RingsAI.java",
        "src/main/java/${packagePath}/common/data/test_data/Data.java": "src/main/java/${packagePath}/common/data/test_data/DataAI.java",
        "src/main/java/${packagePath}/common/data/test_data/DataProperties.java": "src/main/java/${packagePath}/common/data/test_data/DataPropertiesAI.java",
        "src/main/java/${packagePath}/common/service/CustomService.java": "src/main/java/${packagePath}/common/service/CustomServiceAI.java",
        
        "src/main/java/${packagePath}/common/preconditions/Preconditions.java": "src/main/java/${packagePath}/common/preconditions/PreconditionsAI.java",
        "src/main/java/${packagePath}/common/data/creator/DataCreator.java": "src/main/java/${packagePath}/common/data/creator/DataCreatorAI.java",
        "src/main/java/${packagePath}/common/data/cleaner/DataCleaner.java": "src/main/java/${packagePath}/common/data/cleaner/DataCleanerAI.java",
        
        "src/main/java/${packagePath}/api/ExampleEndpoints.java": "src/main/java/${packagePath}/api/EndpointsAI.java",
        "src/main/java/${packagePath}/api/authentication/ExampleAuthenticationClient.java": "src/main/java/${packagePath}/api/authentication/ExampleAuthenticationClientAI.java",
        "src/main/java/${packagePath}/api/authentication/ExampleCredentials.java": "src/main/java/${packagePath}/api/authentication/ExampleCredentialsAI.java",
        "src/main/java/${packagePath}/api/dto/request/ExampleRequestDto.java": "src/main/java/${packagePath}/api/dto/request/ExampleRequestDtoAI.java",
        "src/main/java/${packagePath}/api/dto/response/ExampleResponseDto.java": "src/main/java/${packagePath}/api/dto/response/ExampleResponseDtoAI.java",
        
        "src/main/java/${packagePath}/db/Databases.java": "src/main/java/${packagePath}/db/DatabasesAI.java",
        "src/main/java/${packagePath}/db/queries/ExampleDbQueries.java": "src/main/java/${packagePath}/db/queries/DbQueriesAI.java",
        
        "src/main/java/${packagePath}/ui/components/button/ButtonExampleImpl.java": "src/main/java/${packagePath}/ui/components/button/ButtonExampleImplAI.java",
        "src/main/java/${packagePath}/ui/components/input/InputExampleImpl.java": "src/main/java/${packagePath}/ui/components/input/InputExampleImplAI.java",
        "src/main/java/${packagePath}/ui/components/select/SelectExampleImpl.java": "src/main/java/${packagePath}/ui/components/select/SelectExampleImplAI.java",
        
        "src/main/java/${packagePath}/ui/elements/ButtonFields.java": "src/main/java/${packagePath}/ui/elements/ButtonFieldsAI.java",
        "src/main/java/${packagePath}/ui/elements/InputFields.java": "src/main/java/${packagePath}/ui/elements/InputFieldsAI.java",
        "src/main/java/${packagePath}/ui/elements/SelectFields.java": "src/main/java/${packagePath}/ui/elements/SelectFieldsAI.java",
        "src/main/java/${packagePath}/ui/elements/Tables.java": "src/main/java/${packagePath}/ui/elements/TablesAI.java",
        
        "src/main/java/${packagePath}/ui/types/ButtonFieldTypes.java": "src/main/java/${packagePath}/ui/types/ButtonFieldTypesAI.java",
        "src/main/java/${packagePath}/ui/types/InputFieldTypes.java": "src/main/java/${packagePath}/ui/types/InputFieldTypesAI.java",
        "src/main/java/${packagePath}/ui/types/SelectFieldTypes.java": "src/main/java/${packagePath}/ui/types/SelectFieldTypesAI.java",
        
        "src/main/java/${packagePath}/ui/types/SelectFieldTypes.java": "src/main/java/${packagePath}/ui/types/SelectFieldTypesAI.java",
        
        "src/main/java/${packagePath}/ui/authentication/ExampleAppUiLogin.java": "src/main/java/${packagePath}/ui/authentication/ExampleAppUiLoginAI.java",
        "src/main/java/${packagePath}/ui/authentication/ExampleCredentials.java": "src/main/java/${packagePath}/ui/authentication/ExampleCredentialsAI.java",
        "src/main/java/${packagePath}/ui/interceptor/RequestsInterceptor.java": "src/main/java/${packagePath}/ui/interceptor/RequestsInterceptorAI.java",
        "src/main/java/${packagePath}/ui/model/table/ExampleTableModel.java": "src/main/java/${packagePath}/ui/model/table/ExampleTableModelAI.java",
        "src/main/java/${packagePath}/ui/AppUiService.java": "src/main/java/${packagePath}/ui/AppUiServiceAI.java"
    ]

    aiReplacements.each { originalPath, aiPath ->
        def originalFile = new File(rootDir, originalPath)
        def aiFile = new File(rootDir, aiPath)
        
        if (originalFile.exists()) {
             originalFile.delete()
             
             if (aiFile.exists()) {
                aiFile.renameTo(originalFile)
                
                def text = originalFile.text
                def aiClassName = aiFile.name.replace('.java', '')
                def originalClassName = originalFile.name.replace('.java', '')
                
                text = text.replaceAll(aiClassName, originalClassName)
                
                originalFile.text = text
                println "  Swapped AI skeleton: ${originalFile.name}"
             }
        } else {
             // Original file was deleted by previous logic (e.g. UI feature selection)
             // So we should delete the AI skeleton to completely remove the feature
             if (aiFile.exists()) {
                 aiFile.delete()
                 println "  Deleted zombie AI skeleton: ${aiFile.name}"
             }
        }
    }
    
} else {
    def aiFiles = [
        "src/main/java/${packagePath}/common/base/RingsAI.java",
        "src/main/java/${packagePath}/common/data/test_data/DataAI.java",
        "src/main/java/${packagePath}/common/data/test_data/DataPropertiesAI.java",
        "src/main/java/${packagePath}/common/preconditions/PreconditionsAI.java",
        "src/main/java/${packagePath}/common/data/creator/DataCreatorAI.java",
        "src/main/java/${packagePath}/common/data/cleaner/DataCleanerAI.java",
        "src/main/java/${packagePath}/api/EndpointsAI.java",
        "src/main/java/${packagePath}/db/DatabasesAI.java",
        "src/main/java/${packagePath}/db/queries/DbQueriesAI.java",
        "src/main/java/${packagePath}/ui/elements/ButtonFieldsAI.java",
        "src/main/java/${packagePath}/ui/elements/InputFieldsAI.java",
        "src/main/java/${packagePath}/ui/elements/SelectFieldsAI.java",
        "src/main/java/${packagePath}/ui/elements/TablesAI.java",
        "src/main/java/${packagePath}/ui/types/ButtonFieldTypesAI.java",
        "src/main/java/${packagePath}/ui/types/InputFieldTypesAI.java",
        "src/main/java/${packagePath}/ui/types/SelectFieldTypesAI.java",
        "src/main/java/${packagePath}/ui/interceptor/RequestsInterceptorAI.java",
        "src/main/java/${packagePath}/ui/AppUiServiceAI.java"
    ]
    
    aiFiles.each { aiPath ->
        def aiFile = new File(rootDir, aiPath)
        if (aiFile.exists()) {
            aiFile.delete()
        }
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
        if (advancedFile.exists()) advancedFile.delete()

        if (basicFile.exists()) {
            basicFile.renameTo(baseFile)
            def text = baseFile.text.replaceAll(basicClass, targetClass)
            baseFile.text = text
            println "  Selected basic test: ${baseFile}"
        }
    } else {
        if (basicFile.exists()) basicFile.delete()

        if (advancedFile.exists()) {
            advancedFile.renameTo(baseFile)
            def text = baseFile.text.replaceAll(advancedClass, targetClass)
            baseFile.text = text
            println "  Selected advanced test: ${baseFile}"
        }
    }
}

// For AI mode, delete test class files but keep package structure (empty packages)
if (isAiCommons) {
    println "AI/Skeleton Mode: Removing test class files (keeping empty packages)..."
    
    // Delete specific test files based on selected modules
    if (keepAPI) {
        def apiTestFile = new File(rootDir, "src/test/java/${packagePath}/api/GettingStartedApiTest.java")
        def apiTestBasic = new File(rootDir, "src/test/java/${packagePath}/api/GettingStartedApiTestBasic.java")
        def apiTestAdvanced = new File(rootDir, "src/test/java/${packagePath}/api/GettingStartedApiTestAdvanced.java")
        if (apiTestFile.exists()) apiTestFile.delete()
        if (apiTestBasic.exists()) apiTestBasic.delete()
        if (apiTestAdvanced.exists()) apiTestAdvanced.delete()
        println "  Deleted API test files (package remains)"
    }
    
    if (keepDB) {
        def dbTestFile = new File(rootDir, "src/test/java/${packagePath}/db/GettingStartedDbTest.java")
        def dbTestBasic = new File(rootDir, "src/test/java/${packagePath}/db/GettingStartedDbTestBasic.java")
        def dbTestAdvanced = new File(rootDir, "src/test/java/${packagePath}/db/GettingStartedDbTestAdvanced.java")
        if (dbTestFile.exists()) dbTestFile.delete()
        if (dbTestBasic.exists()) dbTestBasic.delete()
        if (dbTestAdvanced.exists()) dbTestAdvanced.delete()
        println "  Deleted DB test files (package remains)"
    }
    
    if (keepUI) {
        def uiTestFile = new File(rootDir, "src/test/java/${packagePath}/ui/GettingStartedUiTest.java")
        def uiTestBasic = new File(rootDir, "src/test/java/${packagePath}/ui/GettingStartedUiTestBasic.java")
        def uiTestAdvanced = new File(rootDir, "src/test/java/${packagePath}/ui/GettingStartedUiTestAdvanced.java")
        if (uiTestFile.exists()) uiTestFile.delete()
        if (uiTestBasic.exists()) uiTestBasic.delete()
        if (uiTestAdvanced.exists()) uiTestAdvanced.delete()
        println "  Deleted UI test files (package remains)"
    }
} else {
    // Only select test variants for Basic/Advanced modes
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
}

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
        def envLower = env.toLowerCase()
        def baseUrl
        if (index < urlList.size() && urlList[index]) {
            baseUrl = urlList[index]
        } else {
            baseUrl = "https://REPLACE_WITH_${env.toUpperCase()}_URL"
        }

        generateConfigFile(new File(resourcesDir, "config-${envLower}.properties"), baseUrl)
        generateTestDataFile(new File(resourcesDir, "test_data-${envLower}.properties"))
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
    def hasEnvs = envList && envList.size() > 0
    
    def configVal = hasEnvs ? "config-${envList[0].toLowerCase()}" : "config"
    def testDataVal = hasEnvs ? "test_data-${envList[0].toLowerCase()}" : "test_data"
    
    // Update individual properties instead of replacing entire <properties> block
    pomText = pomText.replaceFirst(/(<ui\.config\.file>)[^<]*(<\/ui\.config\.file>)/, "\$1${configVal}\$2")
    pomText = pomText.replaceFirst(/(<api\.config\.file>)[^<]*(<\/api\.config\.file>)/, "\$1${configVal}\$2")
    pomText = pomText.replaceFirst(/(<db\.config\.file>)[^<]*(<\/db\.config\.file>)/, "\$1${configVal}\$2")
    pomText = pomText.replaceFirst(/(<framework\.config\.file>)[^<]*(<\/framework\.config\.file>)/, "\$1${configVal}\$2")
    pomText = pomText.replaceFirst(/(<test\.data\.file>)[^<]*(<\/test\.data\.file>)/, "\$1${testDataVal}\$2")
    
    // Add Profiles if Envs exist
    if (hasEnvs) {
        // Remove existing profiles section if it exists
        pomText = pomText.replaceAll(/(?s)\s*<profiles>.*?<\/profiles>\s*/, "")
        
        def profilesXml = new StringBuilder()
        profilesXml.append("    <profiles>\n")
        envList.each { envName ->
            def envLower = envName.toLowerCase()
            profilesXml.append("""        <profile>
            <id>${envLower}</id>
            <properties>
                <ui.config.file>config-${envLower}</ui.config.file>
                <api.config.file>config-${envLower}</api.config.file>
                <db.config.file>config-${envLower}</db.config.file>
                <framework.config.file>config-${envLower}</framework.config.file>
                <test.data.file>test_data-${envLower}</test.data.file>
            </properties>
        </profile>
""")
        }
        profilesXml.append("    </profiles>")
        
        // Insert after </dependencies> or before </project> if no dependencies
        if (pomText.contains("</dependencies>")) {
            pomText = pomText.replaceFirst("(</dependencies>)", "\$1\n\n${profilesXml.toString()}")
        } else if (pomText.contains("</project>")) {
            pomText = pomText.replaceFirst("</project>", "${profilesXml.toString()}\n</project>")
        }
    }
    
    generatedPom.text = pomText
    println "  Updated pom.xml properties and profiles (Envs: ${hasEnvs})"
}


def systemPropsFile = new File(resourcesDir, "system.properties")
if (systemPropsFile.exists()) {
    def hasEnvs = envList && envList.size() > 0
    def configVal = hasEnvs ? "config-${envList[0].toLowerCase()}" : "config"
    def testDataVal = hasEnvs ? "test_data-${envList[0].toLowerCase()}" : "test_data"
    
    def newSysProps = """project.packages=io.cyborgcode.roa
ui.config.file=${configVal}
api.config.file=${configVal}
db.config.file=${configVal}
framework.config.file=${configVal}
test.data.file=${testDataVal}
logFileName=logs/log.log
log4j2.scriptEnableLanguages=javascript
extended.logging=false
"""
    systemPropsFile.text = newSysProps
    println "  Updated system.properties content"
}

configTemplateFile.delete()
testDataTemplateFile.delete()
println "  Removed template files"
