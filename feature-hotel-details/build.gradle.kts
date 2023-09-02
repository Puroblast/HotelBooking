plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {

    namespace = "com.puroblast.feature_hotel_details"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    buildFeatures {
        viewBinding = true
    }

}


dependencies {

    implementation(project(":domain-hotel"))
    implementation(project(":common-recycler"))


    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    //Dagger region
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    //Dagger endregion

    //Circle indicator region
    implementation(libs.circleindicator)
    //Circle indicator endregion

    //ViewBinding region
    implementation(libs.viewbindingpropertydelegate.noreflection)
    //ViewBinding endregion

    //Coil region
    implementation(libs.coil)
    //Coil endregion

    //Coroutines region
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    //Coroutines endregion

    //Lifecycle region
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    //Lifecycle endregion

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

}
