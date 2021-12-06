package com.aplication.techforest.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
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
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController,
    //userInt : Int
) {
    AnimatedNavHost(navController = navController, startDestination = Devices.route){
        composable(HomeScreen.route){ navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId")
            requireNotNull(userId)
            HomeScreen(userId = userId)
            Log.d("Id del user", "$userId")
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


