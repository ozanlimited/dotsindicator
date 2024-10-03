import org.gradle.api.tasks.bundling.Jar
plugins {
    id("com.android.library")
    kotlin("android")
    id("com.vanniktech.maven.publish").version("0.29.0")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    namespace = "com.tbuonomo.viewpagerdotsindicator"
}

kotlin {
    jvmToolchain(18)
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(18)
    }
}
tasks.register<Jar>("androidSourcesJar"){
    println("components")
    println(components.asMap.toString())
//    println("env vars:")
//    println(uri(System.getenv("GITLAB_REPO_URL")).toString())
//    println((System.getenv("GITLAB_DEPLOY_KEY")).toString())
//    println((System.getenv("GITLAB_DEPLOY_TOKEN")).toString())
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
//    from android.sourceSets.main.java.srcDirs
}
//val sourcesJar by tasks.registering(Jar::class) {
//    classifier = "sources"
//    from(sourceSets.main.get().allSource)
//}
/* todo
ext {
    mGroupId = "com.ozanlimited"
    mArtifactId = "dotsindicator"
    mVersionCode = 2
    mVersionName = "1.3.0"

    mLibraryName = "ViewPagerDotsIndicator"
    mLibraryDescription = ""
}*/
tasks.withType<PublishToMavenRepository> {
    dependsOn("assemble")
}
publishing {
    publications {
        register<MavenPublication>("MavenPub") {
            groupId = "com.ozanlimited"
            artifactId = "dotsindicator"
            version = "1.4.0"
//            from(components["kotlin"])

            from(components.asMap["release"])
//            artifact("androidSourcesJar")
//            pom {
//                name = "ViewPagerDotsIndicator"
//                description = "ViewPagerDotsIndicator description"
//            }

        }
    }
    repositories {
        maven {
            url = uri(System.getenv("GITLAB_REPO_URL"))
            credentials(HttpHeaderCredentials::class){
                name = System.getenv("GITLAB_DEPLOY_KEY")
                value = System.getenv("GITLAB_DEPLOY_TOKEN")
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.dynamicanimation:dynamicanimation:1.0.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    val composeBom = platform("androidx.compose:compose-bom:2023.03.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
}