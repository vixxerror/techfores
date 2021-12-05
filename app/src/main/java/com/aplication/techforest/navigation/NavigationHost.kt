package com.aplication.techforest.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations.*

import com.aplication.techforest.presentation.screens.*

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
@ExperimentalAnimationApi
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = HomeScreen.route){
        composable(route =HomeScreen.route ,
            arguments = HomeScreen.arguments
            ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            HomeScreen(email)
            GreetingSection(email = email)
        }

        composable(Devices.route){
            Devices()
        }

        composable(Plants.route){
            Plants()
        }

        composable(Profile.route,

        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""

            Profiles(email=email)
        }

        composable(Settings.route){
            Settings()
        }

    }


}


