plugins {
    id("fabric-loom") version "1.10-SNAPSHOT"
}

group = project.properties["maven_group"] as String
version = project.properties["mod_version"] as String
base.archivesName.set(project.properties["archives_base_name"] as String)

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.6")
    mappings("net.fabricmc:yarn:1.21.6+build.1:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    // add other mod dependencies here as needed
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filteringCharset = "UTF-8"
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }

    val targetJavaVersion = 21
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        withSourcesJar()
    }
}