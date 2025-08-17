plugins {
    id("buildsrc.convention.kotlin-jvm")
}


dependencies {
    api(libs.spring.data)
    api(libs.spring.web)
    api(libs.druid)
    api(libs.mysql)

    api(libs.jimmer.starter)
    api(libs.blazebit.persistence.hibernate)
    implementation(libs.blazebit.persistence.querydsl)

    api(project(":only4-KSP-cap4k-application"))
}
