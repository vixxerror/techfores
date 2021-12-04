package com.aplication.techforest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.navigation.Destinations3
import com.aplication.techforest.navigation.NavigationHost
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import com.aplication.techforest.presentation.components.BottomNavigationBar

import com.aplication.techforest.presentation.login.LoginScreen
import com.aplication.techforest.viewmodel.LoginViewModel

import com.aplication.techforest.ui.theme.TechForestTheme





@ExperimentalFoundationApi
@AndroidEntryPoint
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechForestTheme {
                val navController = rememberAnimatedNavController()

                BoxWithConstraints {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Destinations3.Login.route
                    ) {
                        addLogin(navController)

                        addHome()
                    }
                }
            }
        }
    }
}
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    fun NavGraphBuilder.addLogin(
        navController: NavHostController
    ) {
        composable(
            route = Destinations3.Login.route,
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(500)
                )
            },
            exitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = tween(500)
                )
            }
        ) {
            val viewModel: LoginViewModel = hiltViewModel()
            val email = viewModel.state.value.email
            val password = viewModel.state.value.password

            if (viewModel.state.value.successLogin) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(
                        Destinations3.Home.route + "/$email" + "/$password"
                    ) {
                        popUpTo(Destinations3.Login.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                LoginScreen(
                    state = viewModel.state.value,
                    onLogin = viewModel::login,

                    onDismissDialog = viewModel::hideErrorDialog
                )
            }
        }
    }
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addHome() {
    composable(
        route = Destinations3.Home.route + "/{email}" + "/{password}",
        arguments = Destinations3.Home.arguments
    ){ backStackEntry ->

        MainScreen()
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val navigationItems = listOf(
        Destinations.HomeScreen,
        Destinations.Devices,
        Destinations.Plants,
        Destinations.Profile,
        Destinations.Settings
    )
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = navigationItems
            )
        }
    ) {
        NavigationHost(navController = navController)
    }
}