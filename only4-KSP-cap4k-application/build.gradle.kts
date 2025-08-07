plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation(project(":only4-KSP-cap4k-domain"))

    testImplementation(kotlin("test"))
}
