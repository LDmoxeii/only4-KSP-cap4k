plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation(platform(libs.springBootDependencies))

    implementation(libs.springAutoConfiguration)
    implementation(libs.springWeb)
    implementation(libs.druid)
    implementation(libs.mysql)

    implementation("com.only4:ddd-core:1.2.3")

    implementation(project(":only4-KSP-cap4k-application"))

    testImplementation(kotlin("test"))
}
