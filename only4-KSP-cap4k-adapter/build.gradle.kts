plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation(platform(libs.springBootDependencies))

    implementation(libs.springAutoConfiguration)
    implementation(libs.springWeb)
    implementation(libs.sringDataJpa)
    implementation(libs.druid)
    implementation(libs.mysql)

    implementation(project(":only4-KSP-cap4k-application"))

    testImplementation(kotlin("test"))
}
