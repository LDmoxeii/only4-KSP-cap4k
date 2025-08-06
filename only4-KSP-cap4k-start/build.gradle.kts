plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.yaml:snakeyaml")
    implementation(platform(libs.springBootDependencies))

    implementation(libs.springAutoConfiguration)
    implementation(libs.springWeb)
    implementation(libs.druid)
    implementation(libs.mysql)

    testImplementation(platform(libs.springBootDependencies))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
