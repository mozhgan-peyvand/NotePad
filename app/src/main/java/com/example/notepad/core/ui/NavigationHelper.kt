package com.example.notepad.core.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import timber.log.Timber

fun safeNavigate(navController: NavController, direction: NavDirections) {
    try {
        navController.navigate(direction)
    } catch (e: Exception) {
        Timber.tag("Navigate").d(e)
    }
}

fun safeNavigate(
    navController: NavController,
    destinationId: Int,
    popupId: Int = 0,
    inclusive: Boolean = true,
    bundle: Bundle? = null
) {
    try {
        if (destinationId.equals(0))
            return

        val bundleDestination = Bundle()
        if (bundle == null) {
            bundleDestination.putInt(
                "destinationId",
                destinationId
            )
        }
        if (!popupId.equals(0)) {
            val naveOption = NavOptions.Builder().setPopUpTo(popupId, inclusive).build()
            navController.navigate(destinationId, bundle ?: bundleDestination, naveOption)
        } else
            navController.navigate(destinationId, bundle ?: bundleDestination)

    } catch (e: Exception) {
        Timber.tag("Navigate").d(e)
    }
}