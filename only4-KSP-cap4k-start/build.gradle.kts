plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation("org.yaml:snakeyaml")
    implementation(platform(libs.springBootDependencies))

    implementation(libs.springAutoConfiguration)
    implementation(libs.springWeb)
    implementation(libs.druid)
    implementation(libs.mysql)

    implementation(project(":only4-KSP-cap4k-adapter"))

    testImplementation(platform(libs.springBootDependencies))
    testImplementation(kotlin("test"))
}
