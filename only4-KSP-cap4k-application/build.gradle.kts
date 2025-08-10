plugins {
    id("buildsrc.convention.kotlin-jvm")
}

dependencies {
    api(libs.spring.data)
    api(libs.spring.validation)
    api(project(":only4-KSP-cap4k-domain"))
}
