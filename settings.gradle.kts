pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ELNAZ"

include(":app")
include(":data")
include(":domain")
include(":core")
include(":di")
include(":logger")
include(":extension")
include(":auth")
include(":user")

include(":feature_barcode")
include(":feature_discount")
include(":feature_inventory")
include(":feature_products")
include(":feature_product_management")
include(":feature_checkout")
include(":feature_order")
include(":feature_category")
include(":feature_customer")
include(":feature_supplier")
include(":features_manager")
