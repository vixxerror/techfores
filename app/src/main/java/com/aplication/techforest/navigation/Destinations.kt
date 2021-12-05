package com.aplication.techforest.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

import androidx.navigation.NavType


import com.aplication.techforest.R
import java.util.Objects.toString


sealed class Destinations(
    val route: String,
    val title: String,
    val email:String,

    @DrawableRes val iconId: Int
) {
    object HomeScreen : Destinations("homeScreen", "Home", "",R.drawable.ic_home_2)
    object Devices : Destinations("devices", "Devices", "email",R.drawable.ic_lambda)
    object Plants : Destinations("plants", "Plants", "email",R.drawable.ic_baseline_local_florist_24)
    object Profile : Destinations("profile", "Profile", "email",R.drawable.ic_profile)
    object Settings : Destinations("settings", "Settings", "email",R.drawable.ic_baseline_settings_24)



}
sealed class Destinations1(
    val route: String
)
{
    object LoginScreen : Destinations1("Login")
    object HomeScreen : Destinations1("Home")
}


sealed class Destinations3(
    val route: String,
    val arguments: List<NamedNavArgument>
){

    object Login: Destinations3("login", emptyList())
    object HomeScreen : Destinations3("Home", emptyList() )
    object Home: Destinations3(
        "home",
        listOf(
            navArgument("email"){ type = NavType.StringType },
            navArgument("password"){ type = NavType.StringType }
        )
    ){
        fun createRoute(email:String)="home/$email"
    }

}
