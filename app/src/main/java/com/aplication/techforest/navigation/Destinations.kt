package com.aplication.techforest.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.aplication.techforest.R

sealed class Destinations(
    val route: String,
    val title: String,
    @DrawableRes val iconId: Int
) {
    object HomeScreen : Destinations("homeScreen", "Home", R.drawable.ic_home_2)
    object Devices : Destinations("devices", "Devices", R.drawable.ic_lambda)
    object Plants : Destinations("plants", "Plants", R.drawable.ic_baseline_local_florist_24)
    object Profile : Destinations("profile", "Profile", R.drawable.ic_profile)
    object Settings : Destinations("settings", "Settings", R.drawable.ic_baseline_settings_24)



}
sealed class Destinations1(
    val route: String
)
{
    object LoginScreen : Destinations1("Login")
    object HomeScreen : Destinations1("Home")
}