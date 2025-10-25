dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }

        create("testlibs") {
            from(files("testlibs.versions.toml"))
        }
    }
}

pluginManagement {
    repositories {
        // For the Fabric Loom plugin
        exclusiveContent {
            forRepository {
                maven {
                    name = "FabricMC"
                    url = uri("https://maven.fabricmc.net/")
                }
            }
            filter {
                includeModule("fabric-loom", "fabric-loom.gradle.plugin")
                includeGroupByRegex("net.fabricmc.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "grimac"
include("common")
include("bukkit")
include("fabric")
include(":fabric:mc1161")
include(":fabric:mc1171")
include(":fabric:mc1194")
include(":fabric:mc1205")
include(":fabric:mc12111")

if (file("workspace.gradle.kts").exists()) apply(from = "workspace.gradle.kts")
