plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    id("kotlin-kapt")
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
}
