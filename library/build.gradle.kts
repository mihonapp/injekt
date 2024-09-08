plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spotless)
    `maven-publish`
}

dependencies {
    api(libs.injekt)
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/build/**/*.kt")
        ktlint(libs.ktlint.cli.get().version)
            .editorConfigOverride(
                mapOf(
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                    "ktlint_standard_class-signature" to "disabled",
                    "ktlint_standard_discouraged-comment-location" to "disabled",
                    "ktlint_standard_function-expression-body" to "disabled",
                    "ktlint_standard_function-signature" to "disabled",
                ),
            )
        trimTrailingWhitespace()
        endWithNewline()
    }
    format("misc") {
        target("**/.gitignore")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}
