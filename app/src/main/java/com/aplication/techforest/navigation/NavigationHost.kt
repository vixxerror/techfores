package com.aplication.techforest.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations.*

import com.aplication.techforest.presentation.login.LoginScreen
import com.aplication.techforest.presentation.screens.Devices
import com.aplication.techforest.presentation.screens.Plants
import com.aplication.techforest.presentation.screens.Profiles
import com.aplication.techforest.presentation.screens.Settings
import com.aplication.techforest.presentation.screens.HomeScreen

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = HomeScreen.route){
        composable(HomeScreen.route){
            HomeScreen()
        }

        composable(Devices.route){
            Devices()
        }

        composable(Plants.route){
            Plants()
        }

        composable(Profile.route){
            Profiles()
        }

        composable(Settings.route){
            Settings()
        }

    }


}


