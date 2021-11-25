package com.aplication.techforest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.navigation.Destinations.*
import com.aplication.techforest.navigation.NavigationHost
import com.aplication.techforest.presentation.components.BottomNavigationBar
import com.aplication.techforest.ui.MediaItem


import com.aplication.techforest.ui.theme.TechForestTheme

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechForestTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}


@ExperimentalCoilApi
@ExperimentalFoundationApi

data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)


@Preview
@Composable
fun MediaList() {
    LazyColumn {
        items(3) {
            MediaItem()
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        HomeScreen,
        Devices,
        Plants,
        Profile,
        Settings
    )

    Scaffold(
        bottomBar = { BottomNavigationBar(
            navController = navController,
            items = navigationItems
        )}
    ){
        NavigationHost(navController = navController)
    }
}



