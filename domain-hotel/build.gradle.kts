plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    //Retrofit region
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    //Retrofit endregion

    testImplementation(libs.junit)
}
