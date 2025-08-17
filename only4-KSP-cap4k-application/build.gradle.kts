plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

dependencies {
    api(libs.spring.data)
    api(libs.spring.validation)
    api(project(":only4-KSP-cap4k-domain"))

    implementation(libs.jimmer.core)
    compileOnly(libs.jimmer.sql)
    ksp(libs.jimmer.ksp)
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
