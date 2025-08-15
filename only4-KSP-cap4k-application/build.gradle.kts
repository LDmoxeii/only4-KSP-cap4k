plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("com.google.devtools.ksp") version "2.1.20-2.0.0"
}

val jimmerVersion = "0.9.106"

dependencies {
    api(libs.spring.data)
    api(libs.spring.validation)
    api(project(":only4-KSP-cap4k-domain"))

    implementation("org.babyfish.jimmer:jimmer-core-kotlin:${jimmerVersion}")
    compileOnly("org.babyfish.jimmer:jimmer-sql-kotlin:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
