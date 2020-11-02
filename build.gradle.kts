plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
    application
}

group = "com.katsite"
version = "0.0.1"

application {
    mainClassName = "com.katsite.MainKt"
}

repositories {
    mavenLocal()
    jcenter()
    maven("https://kotlin.bintray.com/ktor")
    maven("https://kotlin.bintray.com/kotlin-js-wrappers")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:_")
    implementation("io.ktor:ktor-server-netty:_")
    implementation("ch.qos.logback:logback-classic:_")
    implementation("io.ktor:ktor-server-core:_")
    implementation("io.ktor:ktor-html-builder:_")
    implementation("org.jetbrains:kotlin-css-jvm:_")

    testImplementation("io.ktor:ktor-server-tests:_")
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveClassifier.set("")
        archiveVersion.set("")
    }
}
