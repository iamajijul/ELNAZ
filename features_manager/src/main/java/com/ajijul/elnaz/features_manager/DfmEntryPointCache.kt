package com.ajijul.elnaz.features_manager

import com.ajijul.elnaz.extension.toPascalCase
import com.ajijul.elnaz.logger.ElnazLogger

object DfmEntryPointCache {
    // A simple map to hold our instantiated classes
    private val cache = mutableMapOf<String, ComposeFeatureModuleEntry>()

    fun get(moduleName: String, tag: String): ComposeFeatureModuleEntry? {
        // 1. If we already found it, return it instantly! (Zero reflection cost)
        cache[moduleName]?.let { return it }

        // 2. If it's not in the cache, use reflection to find it
        return try {
            val clazz = Class.forName("com.ajijul.elnaz.$moduleName.entry.${moduleName.toPascalCase()}Entry")
            val instance = clazz.getDeclaredConstructor().newInstance() as ComposeFeatureModuleEntry

            // 3. Save it to the cache so we never have to use reflection for this module again
            cache[moduleName] = instance
            instance
        } catch (e: Exception) {
            ElnazLogger.e(tag, "Exception during REFLECTION " + e.message)
            null
        }
    }
}