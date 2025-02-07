import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
	// Apply the Kotlin JVM plugin to add support for Kotlin.
	id("org.jetbrains.kotlin.jvm") version "1.6.0-RC2"
	kotlin("plugin.serialization") version "1.5.31"
	id("com.github.johnrengelman.shadow") version "7.0.0"
	`maven-publish`
	// Apply the application plugin to add support for building a jar
	java
}

repositories {
	// Use jcenter for resolving dependencies.
	//jcenter()

	// Use mavenCentral
	mavenCentral()
	maven(url = "https://repo1.maven.org/maven2/")
	maven(url = "https://repo.spongepowered.org/maven")
	maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
	maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	maven(url = "https://libraries.minecraft.net")
	maven(url = "https://jitpack.io")
	maven(url = "https://jcenter.bintray.com/")
	maven(url = "https://isaiah.fungus-soft.com/maven-repo")
	maven(url = "https://papermc.io/repo/repository/maven-public/")
}

dependencies {
	// Align versions of all Kotlin components
	compileOnly(platform("org.jetbrains.kotlin:kotlin-bom"))

	// Use the Kotlin JDK 8 standard library.
	compileOnly(kotlin("stdlib"))

	// Use the Kotlin reflect library.
	compileOnly(kotlin("reflect"))

	// Compile Minestom into project
	compileOnly("com.github.minestom:minestom:83ab75d1bf")

	// import kotlinx serialization
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
	implementation("com.charleskorn.kaml:kaml:0.36.0")

	// Get the paper API
	implementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

	// Weird bukkit deps
	implementation("net.sf.jopt-simple:jopt-simple:5.0.4")

	// Add KStom
	//compileOnly("com.github.Project-Cepi:KStom:b30449df1c")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

tasks {
	named<ShadowJar>("shadowJar") {
		mergeServiceFiles()
	}

	test { useJUnitPlatform() }

	build { dependsOn(shadowJar) }

}

publishing {
	publications {
		create("maven_public", MavenPublication::class) {
			groupId = "com.github.nextpvp"
			artifactId = "bukstom"
			version = "1.0"
			from(components.getByName("java"))
		}
	}
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "17" }
group = "com.github.nextpvp"
version = "1.0"