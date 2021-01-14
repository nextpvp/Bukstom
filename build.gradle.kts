import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    maven

    // Apply the application plugin to add support for building a jar
    java
}

repositories {
    // Use jcenter for resolving dependencies.
    jcenter()

    // Use mavenCentral
    maven(url = "https://repo1.maven.org/maven2/")
    maven(url = "http://repo.spongepowered.org/maven")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://libraries.minecraft.net")
    maven(url = "https://jitpack.io")
    maven(url = "https://jcenter.bintray.com/")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    implementation(kotlin("reflect"))

    // Compile Minestom into project
    implementation("com.github.Minestom:Minestom:-SNAPSHOT")

    // OkHttp
    implementation("com.squareup.okhttp3", "okhttp", "4.9.0")

    // import kotlinx serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    // Get the spigot API
    implementation("org.spigotmc:spigot-api:1.16.3-R0.1-SNAPSHOT")

    // Get commons lib
    implementation("com.google.guava:guava:30.1-jre")
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("commons-collections:commons-collections:3.2.2")
    implementation("org.apache.commons:commons-collections4:4.4")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    named<ShadowJar>("shadowJar") {
        mergeServiceFiles()
    }

    test { useJUnitPlatform() }

    build { dependsOn(shadowJar) }

}