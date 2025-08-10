plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    implementation("org.yaml:snakeyaml")
    api(libs.spring.actuator)
    api(libs.spring.configuration.processor)
    api(libs.cap4k.console)
    api(project(":only4-KSP-cap4k-adapter"))
    api(project(":only4-KSP-cap4k-application"))
    api(project(":only4-KSP-cap4k-domain"))
}
