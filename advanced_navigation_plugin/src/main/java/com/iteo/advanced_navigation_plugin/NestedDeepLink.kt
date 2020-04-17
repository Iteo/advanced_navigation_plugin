package com.iteo.advanced_navigation_plugin

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkBuilder

data class NestedDeepLink(
    val mainGraphId: Int,
    val mainEntryDestination: Int,
    val nestedGraphId: Int,
    val nestedDestination: Int,
    val nestedDestinationBundle: Bundle? = null
) {
    fun toIntent(context: Context): Intent {
        val nested = NavDeepLinkBuilder(context)
            .setGraph(nestedGraphId)
            .setDestination(nestedDestination)
            .setArguments(nestedDestinationBundle)
            .createTaskStackBuilder()

        val baseIntent = NavDeepLinkBuilder(context)
            .setGraph(mainGraphId)
            .setDestination(mainEntryDestination)
            .setArguments(bundleOf(
                NESTED_INTENT_KEY to nested.editIntentAt(0)
            ))

        baseIntent.getDeepLinkBuilderIntent().flags = 0

        return baseIntent.getDeepLinkBuilderIntent()
    }

    fun toPendingIntent(
        context: Context,
        requestCode: Int = 0,
        flag: Int = PendingIntent.FLAG_UPDATE_CURRENT
    ): PendingIntent = PendingIntent.getActivity(
        context,
        requestCode,
        toIntent(context),
        flag
    )

    companion object {
        const val NESTED_INTENT_KEY = "nested"
    }
}
