import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
}
//
//extra["TMDBApiKey"] = getTMDBApiKey()
//extra["lastFmAPiKey"] = getLastFmAPiKey()
//extra["lastFmAPiSecretKey"] = getLastFmAPiSecretKey()
//
//fun getTMDBApiKey(): String {
//    return gradleLocalProperties(rootDir, providers).getProperty("TMDB_API_KEY")
//}
//
//fun getLastFmAPiKey(): String {
//    return gradleLocalProperties(rootDir, providers).getProperty("LASTFM_API_KEY")
//}
//
//fun getLastFmAPiSecretKey(): String {
//    return gradleLocalProperties(rootDir, providers).getProperty("LASTFM_API_KEY_SECRET")
//}


