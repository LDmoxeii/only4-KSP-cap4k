plugins {
    id("buildsrc.convention.kotlin-jvm")
}

val jimmerVersion = "0.9.106"

dependencies {
    api(libs.spring.data)
    api(libs.spring.web)
    api(libs.druid)
    api(libs.mysql)

    api(libs.blazebit.persistence.hibernate)
    implementation(libs.blazebit.persistence.querydsl)

    api("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    api(project(":only4-KSP-cap4k-application"))
}
