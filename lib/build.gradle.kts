/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.0.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
    id("org.jetbrains.dokka") version "1.5.31"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    signing
}

group = "org.hravemzdy.procezor"
version = "0.0.7"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val spek_version = "2.0.17"


dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.31")

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:30.0-jre")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:$spek_version")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // Reflections library https://github.com/ronmamo/reflections
    api("org.reflections:reflections:0.9.12")

    // legalios library
    api("org.hravemzdy.legalios:kotlin-legalios:0.0.8")

    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.12")

    dokkaJavadocPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.5.31")
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(buildDir.resolve("dokkaJavadoc"))
}

val sourcesJar by tasks.registering(Jar::class) {
    classifier = "sources"
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    classifier = "javadoc"
    from(tasks.dokkaJavadoc.get().outputDirectory)
    dependsOn(tasks.dokkaJavadoc)
}

tasks.jar {
    archiveBaseName.set("kotlin-procezor")

    manifest {
        attributes(mapOf("Implementation-Title" to rootProject.name,
            "Implementation-Version" to project.version))
    }
}

// setup the test task
tasks.test {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven-kotlin") {
            artifactId = "kotlin-procezor"

            from(components["kotlin"])
            artifact(sourcesJar)
            artifact(javadocJar)
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("kotlin-procezor")
                description.set("payroll-procezor Salary, Health, Social, Taxing Properties for years 2011-2022")
                url.set("https://mzdyhrave.github.io/payrolldocs/")
                properties.set(mapOf(
                    "procezor.year.min" to "2011",
                    "procezor.year.max" to "2022",
                    "procezor.country" to "CZ-cz"
                ))
                licenses {
                    license {
                        name.set("The Unlicense")
                        url.set("https://unlicense.org")
                    }
                }
                developers {
                    developer {
                        id.set("ladislavlisy")
                        name.set("Ladislav Lisy")
                        email.set("info@hravemzdy.org")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:mzdyhrave/payrollkt.git")
                    developerConnection.set("scm:git:git@github.com:mzdyhrave/payrollkt.git")
                    url.set("https://mzdyhrave.github.io/payrolldocs/")
                }
            }
        }
    }
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            //val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            //val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            authentication {
                credentials {
                    username = findProperty("ossrhUsername") as String?
                    password = findProperty("ossrhPassword") as String?
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven-kotlin"])
}

