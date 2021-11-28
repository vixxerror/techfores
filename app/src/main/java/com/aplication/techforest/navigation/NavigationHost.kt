package com.aplication.techforest.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations.*
import com.aplication.techforest.presentation.MainScreen
import com.aplication.techforest.presentation.login.LoginScreen
import com.aplication.techforest.presentation.screens.Devices
import com.aplication.techforest.presentation.screens.Plants
import com.aplication.techforest.presentation.screens.Profiles
import com.aplication.techforest.presentation.screens.Settings
import com.aplication.techforest.presentation.screens.HomeScreen

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
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun NavigationHost1() {val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations1.LoginScreen.route){
        composable(Destinations1.LoginScreen.route){
            LoginScreen(
                HomeScreen = {
                    navController.navigate(HomeScreen.route)
                }
            )
        }
        composable(HomeScreen.route){
            MainScreen()
        }
}}