rootProject.name = "quiz_api"
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        // add mavenLocal() if you are using a locally built version of the plugin
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.cloud.tools.appengine-appyaml")) {
                useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            }
        }
    }
}
