plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation(platform(libs.springBootDependencies))
    implementation(libs.sringDataJpa)

    implementation("com.only4:cap4k-ddd-starter:1.2.3")
//    implementation("com.only4:ddd-core:1.2.3")

//    testImplementation(kotlin("test"))
}
